package netty.handler.ActualHandler.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.packet.response.command.QuitChatGroupResponsePacket;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class QuitChatGroupResponseHandler extends SimpleChannelInboundHandler<QuitChatGroupResponsePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			QuitChatGroupResponsePacket responsePacket) throws Exception {

		if (responsePacket.isSuccess()){
			System.out.println("退出群聊【"+responsePacket.getGroupId()+"】成功！");
		}else{
			System.out.println("退出群聊【"+responsePacket.getGroupId()+"】失败! 失败原因："+responsePacket.getReason());
		}

	}
}
