package netty.handler.ActualHandler.request;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import netty.attribute.Attributes;
import netty.packet.request.command.CreateChatGroupRequestPacket;
import netty.packet.response.command.CreateChatGroupResponsePacket;
import netty.util.RandomGIdGenerateUtil;
import netty.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyanwei
 * @date 2018/10/29.
 */
public class CreateChatGroupRequestHandler extends SimpleChannelInboundHandler<CreateChatGroupRequestPacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			CreateChatGroupRequestPacket requestPacket) throws Exception {

		List<String> userIdList = requestPacket.getUserIdList();
		String userIdOfChannel = ctx.channel().attr(Attributes.SESSION).get().getUserId();
		userIdList.add(userIdOfChannel);

		List<String> userNameList = new ArrayList<>();
		ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
		for (String userId : userIdList){
			Channel channel = SessionUtil.getChannel(userId);
			if (channel != null){
				channelGroup.add(channel);
				userNameList.add(SessionUtil.getSession(channel).getUserName());
			}
		}


		CreateChatGroupResponsePacket responsePacket = new CreateChatGroupResponsePacket();
		String groupId = RandomGIdGenerateUtil.getGId();
		responsePacket.setHandleStatus(true);
		responsePacket.setGroupId(groupId);
		responsePacket.setUserNameList(userNameList);
		channelGroup.writeAndFlush(responsePacket);

		//将群ID与群相映射
		SessionUtil.putChannelGroup(groupId,channelGroup);

		System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
		System.out.println("群里面有：" + responsePacket.getUserNameList());

	}
}
