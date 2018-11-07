package netty.handler.ActualHandler.request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.packet.request.command.ChatGroupMessageRequestPacket;
import netty.packet.response.command.ChatGroupMessageResponsePacket;
import netty.util.SessionUtil;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ChatGroupMessageRequestHandler extends SimpleChannelInboundHandler<ChatGroupMessageRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			ChatGroupMessageRequestPacket requestPacket) throws Exception {

		// 1.拿到 groupId 构造群聊消息的响应
		String groupId = requestPacket.getGroupId();
		ChatGroupMessageResponsePacket responsePacket = new ChatGroupMessageResponsePacket();
		responsePacket.setFromGroupId(groupId);
		responsePacket.setGroupMessage(requestPacket.getGroupMessage());
		responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


		// 2. 拿到群聊对应的 channelGroup，写到每个客户端
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		channelGroup.writeAndFlush(responsePacket);
	}
}
