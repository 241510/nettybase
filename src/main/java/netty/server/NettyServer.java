package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.handler.ActualHandler.*;
import netty.handler.ActualHandler.request.*;
import netty.handler.ActualHandler.response.ListChatGroupMembersResponseHandler;

public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                    	//拆包器
                    	ch.pipeline().addLast(new Spliter());
                    	//数据包解码处理器
                        ch.pipeline().addLast(new PacketDecodeHandler());
                        //登录请求处理器
                        ch.pipeline().addLast(new LoginRequestHandler());
                        //创建聊天组请求处理器
						ch.pipeline().addLast(new CreateChatGroupRequestHandler());
						//加入群聊请求处理器
						ch.pipeline().addLast(new JoinChatGroupRequestHandler());
						//退出群聊请求处理器
						ch.pipeline().addLast(new QuitChatGroupRequestHandler());
						//聊天群中成员列表请求处理器
						ch.pipeline().addLast(new ListChatGroupMembersResponseHandler());
						//群聊消息请求处理器
						ch.pipeline().addLast(new ChatGroupMessageRequestHandler());
						//聊天消息发送请求处理器
                        ch.pipeline().addLast(new MessageRequestHandler());
                        //数据包编码处理器
                        ch.pipeline().addLast(new PacketEncodeHandler());
                    }
                });

        bind(serverBootstrap, 8000);
    }

    public static void bind(final ServerBootstrap serverBootstrap,final int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("bind port succeed");
            } else {
                System.out.println("bind port failed");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
