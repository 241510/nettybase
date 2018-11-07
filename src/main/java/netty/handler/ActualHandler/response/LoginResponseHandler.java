package netty.handler.ActualHandler.response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.Session;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.util.LoginUtil;
import netty.util.SessionUtil;

import java.util.Date;

/**
 * @author gaoyanwei
 * @date 2018/10/17.
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>{

	/*@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
		System.out.println("login handler 添加：channelAdded");
	}
*/
	/*@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		//客户端传递登录的指令
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
		//loginRequestPacket.setUid("001");
		loginRequestPacket.setUsername("username");
		loginRequestPacket.setPassword("password");
		System.out.println("向服务端发送登录请求");
		*//*ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
		ByteBuf messageBuf = BinaryPacketConverter.encode(loginRequestPacket,byteBuf, SerializeAlgorithmSign.json);
		ctx.channel().writeAndFlush(messageBuf);*//*
		ctx.channel().writeAndFlush(loginRequestPacket);
	}*/

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket)
			throws Exception {
		if (loginResponsePacket.getStatus().equals(ResponseStatus.SUCCESS)) {
			System.out.println("客户端登录成功");
			SessionUtil.bindSession(new Session(loginResponsePacket.getUserId(),loginResponsePacket.getUserName()),ctx.channel());
		} else {
			System.out.println(new Date() + "客户端登录失败 , 原因：" + loginResponsePacket.getMessage());
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端连接被关闭");
	}
}
