package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.handler.FirstServerHandler;

/**
 * @author gaoyanwei
 * @date 2018/9/27.
 */
public class NettyServer {

	public static void main(String[] args) {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					protected void initChannel(NioSocketChannel ch) {
						ch.pipeline().addLast(new FirstServerHandler());
					}
				});

		bind(serverBootstrap, 8000);
	}

	/**
	 * 绑定端口
	 * @param serverBootstrap
	 * @param port
	 */
	public static void bind(final ServerBootstrap serverBootstrap, final int port) {
		serverBootstrap.bind(port).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("绑定端口成功");
			} else {
				System.out.println("端口绑定失败");
				bind(serverBootstrap, port + 1);
			}
		});
	}
}
