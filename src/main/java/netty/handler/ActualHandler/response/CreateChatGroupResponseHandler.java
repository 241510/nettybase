package netty.handler.ActualHandler.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.attribute.Attributes;
import netty.packet.response.command.CreateChatGroupResponsePacket;

import java.util.List;

/**
 * @author gaoyanwei
 * @date 2018/10/29.
 */
public class CreateChatGroupResponseHandler extends SimpleChannelInboundHandler<CreateChatGroupResponsePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			CreateChatGroupResponsePacket responsePacket) throws Exception {

		String oneSelfUserName = ctx.channel().attr(Attributes.SESSION).get().getUserName();
		List<String> userNameList = responsePacket.getUserNameList();
		for (String userName : userNameList){
			if (userName.equals(oneSelfUserName)){
				userNameList.remove(oneSelfUserName);
			}
		}

		System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
		System.out.println("群里面有：" + responsePacket.getUserNameList());
	}
}
