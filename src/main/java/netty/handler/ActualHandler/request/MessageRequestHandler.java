package netty.handler.ActualHandler.request;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.Session;
import netty.exception.NettyException;
import netty.packet.request.command.MessageRequestPacket;
import netty.packet.response.command.MessageResponsePacket;
import netty.protocol.serializeAlgorithm.SerializeAlgorithmSign;
import netty.util.SessionUtil;
import netty.util.converter.BinaryPacketConverter;

import java.util.Date;

/**
 * @author gaoyanwei
 * @date 2018/10/17.
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {

		//获取消息发送方的会话信息
		Session session = SessionUtil.getSession(ctx.channel());

		if (session == null){
			throw new NettyException("用户未登录");
		}

		//拼装发送给客户端的信息包
		MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
		messageResponsePacket.setFormUserId(session.getUserId());
		messageResponsePacket.setFromUserName(session.getUserName());
		messageResponsePacket.setMessage(messageRequestPacket.getMessage());

		//获取消息接收方的channel
		Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

		if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
			toUserChannel.writeAndFlush(messageResponsePacket);
		}else{
			System.out.println("["+messageRequestPacket.getToUserId()+"]  不在线，发送失败!");
		}

	}

	/*private MessageResponsePacket receive(MessageRequestPacket messageRequestPacket) {

		System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

		MessageResponsePacket responsePacket = new MessageResponsePacket();
		responsePacket.setMessage("服务端回复【"+messageRequestPacket.getMessage()+"】");
		return responsePacket;
	}*/
}
