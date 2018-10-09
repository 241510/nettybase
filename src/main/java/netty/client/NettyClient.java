package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import netty.handler.FirstClientHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoyanwei
 * @date 2018/9/27.
 */
public class NettyClient {

	private static final String IP = "127.0.0.1";
	private static final int PORT = 8000;
	private static final int MAX_RETRY = 5;

	public static void main(String[] args) {

		NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();

		bootstrap.group(workerGroup).channel(NioSocketChannel.class)
				.attr(AttributeKey.newInstance("clientName"),"clientOne")
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1000)
				.handler(new ChannelInitializer<SocketChannel>() {
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				//IO 处理逻辑
				socketChannel.pipeline().addLast(new FirstClientHandler());
			}
		});

		connect(bootstrap, IP, PORT, MAX_RETRY);

	}

	public static void connect(Bootstrap bootstrap, String ip, int port, int retry) {

		bootstrap.connect(ip, port).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("连接成功");
			} else if (retry == 0) {
				System.out.println("重试次数已用完，放弃连接");
			} else {
				//第几次重连
				int order = (MAX_RETRY - retry) + 1;
				//本次重连间隔时间(2的幂)
				int delay = 1 << order;
				System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
				//重新连接
				bootstrap.config().group()
						.schedule(() -> connect(bootstrap, ip, port, retry - 1),delay, TimeUnit.SECONDS);
			}

		});
	}

}


