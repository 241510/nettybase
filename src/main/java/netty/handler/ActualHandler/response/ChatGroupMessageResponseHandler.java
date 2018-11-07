package netty.handler.ActualHandler.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.packet.response.command.ChatGroupMessageResponsePacket;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ChatGroupMessageResponseHandler extends SimpleChannelInboundHandler<ChatGroupMessageResponsePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ChatGroupMessageResponsePacket responsePacket)
			throws Exception {

		System.out.println(
				"收到群【" + responsePacket.getFromGroupId() + "】中【" + responsePacket.getFromUser().getUserId() + ":"
						+ responsePacket.getFromUser().getUserName() + "】发来的消息：" + responsePacket.getGroupMessage());

	}
}
