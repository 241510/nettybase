package netty.handler.ActualHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import netty.util.converter.BinaryPacketConverter;

/**
 * @author gaoyanwei
 * @date 2018/10/22.
 */
public class Spliter extends LengthFieldBasedFrameDecoder{

	//魔数，用于识别
	private static final int MAGIC_NUMBER = 0x12345678;
	private static final int LENGTH_FIELD_OFFSET = 7;
	private static final int LENGTH_FIELD_LENGTH = 4;

	public Spliter(){
		super(Integer.MAX_VALUE,LENGTH_FIELD_OFFSET,LENGTH_FIELD_LENGTH);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

		//屏蔽非本协议客户端
		if (in.getInt(in.readerIndex()) != MAGIC_NUMBER){
			ctx.channel().close();
			return null;
		}
		return super.decode(ctx,in);
	}
}
