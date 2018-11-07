package netty.handler.ActualHandler.request;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.Session;
import netty.packet.request.command.ListChatGroupMembersRequestPacket;
import netty.packet.response.command.ListChatGroupMembersResponsePacket;
import netty.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyanwei\
 * @date 2018/11/7.
 */
public class ListChatGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListChatGroupMembersRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			ListChatGroupMembersRequestPacket requestPacket) throws Exception {

		String groupId = requestPacket.getGroupId();
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

		List<Session> sessionList = new ArrayList<>();
		for (Channel channel : channelGroup){
			Session session = SessionUtil.getSession(channel);
			sessionList.add(session);
		}

		ListChatGroupMembersResponsePacket responsePacket = new ListChatGroupMembersResponsePacket();
		responsePacket.setGroupId(groupId);
		responsePacket.setSessionList(sessionList);
		ctx.channel().writeAndFlush(responsePacket);
	}
}
