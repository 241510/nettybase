package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.handler.FirstClientHandler;

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
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        // 4.建立连接(支持重连机制)
        connect(bootstrap, IP, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String ip, int port, int retry) {

        bootstrap.connect(ip, port).addListener(future -> {

            if (future.isSuccess()) {
                System.out.println("connect is succeed");
            } else if (retry == 0) {
                System.out.println("retry num is zero , abandon build connect");
            } else {
                //第几次连接
                int order = (MAX_RETRY - retry) + 1;
                System.out.println("retry build connect! how many times is "+order);
                //以指数退避的方式重试来连接
                int delay = 1 << order;
                //按一定时间间隔重试
                bootstrap.config().group().schedule(() -> connect(bootstrap, ip, port, retry - 1), delay, TimeUnit.SECONDS);
            }

        });

    }
}
