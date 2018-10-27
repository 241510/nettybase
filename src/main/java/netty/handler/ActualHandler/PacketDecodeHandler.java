package netty.handler.ActualHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import netty.util.converter.BinaryPacketConverter;

import java.util.List;

/**
 * @author gaoyanwei
 * @date 2018/10/17.
 */
public class PacketDecodeHandler extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
			throws Exception {
		list.add(BinaryPacketConverter.decode(byteBuf));
	}
}
