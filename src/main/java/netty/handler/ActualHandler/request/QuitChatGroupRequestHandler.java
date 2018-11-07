package netty.handler.ActualHandler.request;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.packet.request.command.QuitChatGroupRequestPacket;
import netty.packet.response.command.QuitChatGroupResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.util.SessionUtil;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class QuitChatGroupRequestHandler extends SimpleChannelInboundHandler<QuitChatGroupRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			QuitChatGroupRequestPacket requestPacket) throws Exception {

		String groupId = requestPacket.getGroupId();
		ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
		channelGroup.remove(ctx.channel());

		QuitChatGroupResponsePacket responsePacket = new QuitChatGroupResponsePacket();
		responsePacket.setStatus(ResponseStatus.SUCCESS);
		responsePacket.setGroupId(groupId);
		ctx.channel().writeAndFlush(responsePacket);

		//群中成员为0，将群组删除
		if (channelGroup.size()==0){
			SessionUtil.removeChannelGroup(groupId);
		}

		for (Channel channel : channelGroup){
			channel.writeAndFlush(requestPacket);
		}
	}
}
