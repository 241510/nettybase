package netty.handler.ActualHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.packet.Packet;
import netty.util.converter.BinaryPacketConverter;

/**
 * @author gaoyanwei
 * @date 2018/10/17.
 */
public class PacketEncodeHandler extends MessageToByteEncoder<Packet>{
	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf)
			throws Exception {
		BinaryPacketConverter.encode(packet,byteBuf,(byte)1);
	}
}
