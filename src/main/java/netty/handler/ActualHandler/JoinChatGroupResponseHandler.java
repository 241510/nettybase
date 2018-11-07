package netty.handler.ActualHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.packet.response.command.JoinChatGroupResponsePacket;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class JoinChatGroupResponseHandler extends SimpleChannelInboundHandler<JoinChatGroupResponsePacket> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			JoinChatGroupResponsePacket responsePacket) throws Exception {

		if (responsePacket.isSuccess()){
			System.out.println("加入群[" + responsePacket.getGroupId() + "]成功!");
		} else {
			System.err.println("加入群[" + responsePacket.getGroupId() + "]失败，原因为：" + responsePacket.getReason());
		}

	}
}
