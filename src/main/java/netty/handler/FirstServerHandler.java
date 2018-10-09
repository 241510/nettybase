package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author gaoyanwei
 * @date 2018/9/27.
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		System.out.println(new Date() + "服务端接收数据："+buf.toString(Charset.forName("utf-8")));

		System.out.println(new Date()+"服务端写数据...");

		ByteBuf byteBuf = getByteBuf(ctx);

		ctx.channel().writeAndFlush(byteBuf);
	}

	private ByteBuf getByteBuf(ChannelHandlerContext ctx) {

		String message = "我收到你的消息了。。。。。。";

		byte[] bytes = message.getBytes(Charset.forName("utf-8"));

		ByteBuf byteBuf = ctx.alloc().buffer();

		byteBuf.writeBytes(bytes);

		return byteBuf;
	}
}
