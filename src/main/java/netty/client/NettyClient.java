package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.handler.ActualHandler.*;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.request.command.MessageRequestPacket;
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
						ch.pipeline().addLast(new Spliter());
						ch.pipeline().addLast(new PacketDecodeHandler());
						ch.pipeline().addLast(new LoginResponseHandler());
						ch.pipeline().addLast(new MessageResponseHandler());
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

		Scanner scanner = new Scanner(System.in);
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

		new Thread(() -> {
			while (!Thread.interrupted()) {
				if (!SessionUtil.hasLogin(channel)) {
					System.out.println("输入用户名登录");
					String userName = scanner.nextLine();

					loginRequestPacket.setUsername(userName);
					loginRequestPacket.setPassword("pwd");//默认

					channel.writeAndFlush(loginRequestPacket);
					//等待服务端认证登录信息
					waitForLoginResponse();
				}else{
					String toUserId = scanner.next();
					String message = scanner.next();
					channel.writeAndFlush(new MessageRequestPacket(toUserId,message));
				}
			}
		}).start();
	}

	private static void waitForLoginResponse() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}
