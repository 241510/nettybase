package netty.handler.ActualHandler.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.packet.request.command.JoinChatGroupRequestPacket;
import netty.packet.response.command.JoinChatGroupResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.util.SessionUtil;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class JoinChatGroupRequestHandler extends SimpleChannelInboundHandler<JoinChatGroupRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinChatGroupRequestPacket request) throws Exception {

		String groupId = request.getGroupId();
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		channelGroup.add(ctx.channel());

		JoinChatGroupResponsePacket responsePacket = new JoinChatGroupResponsePacket();
		responsePacket.setStatus(ResponseStatus.SUCCESS);
		responsePacket.setGroupId(groupId);
		channelGroup.writeAndFlush(responsePacket);
	}
}
