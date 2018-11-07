package netty.handler.ActualHandler.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.packet.response.command.ListChatGroupMembersResponsePacket;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ListChatGroupMembersResponseHandler
		extends SimpleChannelInboundHandler<ListChatGroupMembersResponsePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ListChatGroupMembersResponsePacket responsePacket)
			throws Exception {

		System.out.println("聊天群【"+responsePacket.getGroupId()+"】中的成员列表："+responsePacket.getSessionList());

	}
}
