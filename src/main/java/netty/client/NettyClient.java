package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.console.command.LoginConsoleCommand;
import netty.console.manager.ConsoleCommandManager;
import netty.handler.ActualHandler.*;
import netty.handler.ActualHandler.response.*;
import netty.util.SessionUtil;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoyanwei
 * @date
 */
public class NettyClient {

	private static final String IP = "127.0.0.1";
	private static final int PORT = 8000;
	private static final int MAX_RETRY = 5;

	public static void main(String[] args) {

		NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();
		bootstrap
				// 1.指定线程模型
				.group(workerGroup).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.TCP_NODELAY, true)
				// 2.指定 IO 类型为 NIO
				.channel(NioSocketChannel.class)
				// 3.IO 处理逻辑
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) {
						//拆包器
						ch.pipeline().addLast(new Spliter());
						//数据包解码处理器
						ch.pipeline().addLast(new PacketDecodeHandler());
						//登录响应处理器
						ch.pipeline().addLast(new LoginResponseHandler());
						//创建群聊响应处理器
						ch.pipeline().addLast(new CreateChatGroupResponseHandler());
						//加入聊天群响应处理器
						ch.pipeline().addLast(new JoinChatGroupResponseHandler());
						//退出群聊响应处理器
						ch.pipeline().addLast(new QuitChatGroupResponseHandler());
						//获取群聊中的成员列表处理器
						ch.pipeline().addLast(new ListChatGroupMembersResponseHandler());
						//群聊消息响应处理器
						ch.pipeline().addLast(new ChatGroupMessageResponseHandler());
						//消息接收处理器
						ch.pipeline().addLast(new MessageResponseHandler());
						//数据包编码处理器
						ch.pipeline().addLast(new PacketEncodeHandler());
					}
				});
		// 4.建立连接(支持重连机制)
		connect(bootstrap, IP, PORT, MAX_RETRY);
	}

	private static void connect(Bootstrap bootstrap, String ip, int port, int retry) {

		bootstrap.connect(ip, port).addListener(future -> {

			if (future.isSuccess()) {

				System.out.println("connect is succeed");

				Channel channel = ((ChannelFuture) future).channel();
				//打开控制台线程
				startConsoleThread(channel);

			} else if (retry == 0) {
				System.out.println("retry num is zero , abandon build connect");
			} else {
				//第几次连接
				int order = (MAX_RETRY - retry) + 1;
				System.out.println("retry build connect! how many times is " + order);
				//以指数退避的方式重试来连接
				int delay = 1 << order;
				//按一定时间间隔重试
				bootstrap.config().group()
						.schedule(() -> connect(bootstrap, ip, port, retry - 1), delay, TimeUnit.SECONDS);
			}

		});

	}

	private static void startConsoleThread(Channel channel) {

		ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
		LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
		Scanner scanner = new Scanner(System.in);

		new Thread(() -> {
			while (!Thread.interrupted()) {
				if (!SessionUtil.hasLogin(channel)) {
					loginConsoleCommand.exec(scanner,channel);
				}else{
					consoleCommandManager.exec(scanner,channel);
				}
			}
		}).start();
	}
}
