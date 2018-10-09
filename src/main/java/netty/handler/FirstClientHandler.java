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
public class FirstClientHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println(new Date()+"客户端写数据。。。");

		//获取数据
		ByteBuf buffer = getByteBuffer(ctx);

		//写数据到客户端连接通道
		ctx.channel().writeAndFlush(buffer);


	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(new Date()+"客户端收到服务端信息："+((ByteBuf) msg).toString(Charset.forName("utf-8")));
	}

	private ByteBuf getByteBuffer(ChannelHandlerContext ctx) {

		//获取二进制抽象 byteBuf
		ByteBuf buffer = ctx.alloc().buffer();

		//准备数据，字符串的编码格式为utf-8
		String message = "你好，世界!";
		byte[] bytes = message.getBytes(Charset.forName("utf-8"));

		//填充数据到byteBuf
		buffer.writeBytes(bytes);
		return buffer;
	}
}
