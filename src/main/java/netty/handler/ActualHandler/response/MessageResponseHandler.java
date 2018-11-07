package netty.handler.ActualHandler.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.packet.response.command.MessageResponsePacket;

import java.util.Date;

/**
 * @author gaoyanwei
 * @date 2018/10/17.
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket>{
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext,
			MessageResponsePacket messageResponsePacket) throws Exception {
		String fromUserId = messageResponsePacket.getFormUserId();
		String fromUserName = messageResponsePacket.getFromUserName();
		System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket .getMessage());
	}
}
