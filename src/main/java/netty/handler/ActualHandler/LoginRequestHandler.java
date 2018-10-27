package netty.handler.ActualHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.Session;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.util.RandomUserIdGenerateUtil;
import netty.util.SessionUtil;

/**
 * @author gaoyanwei
 * @date 2018/10/17.
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
		LoginResponsePacket resp = login(ctx,loginRequestPacket);
		ctx.channel().writeAndFlush(resp);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		SessionUtil.unbindSession(ctx.channel());
	}

	private LoginResponsePacket login(ChannelHandlerContext ctx,LoginRequestPacket loginRequestPacket) throws Exception{
		LoginResponsePacket responsePacket = new LoginResponsePacket();
		if (userValidate(loginRequestPacket)){
			String userId = randomUserId();
			responsePacket.setUserId(userId);
			responsePacket.setUserName(loginRequestPacket.getUsername());
			responsePacket.setStatus(ResponseStatus.SUCCESS);
			responsePacket.setMessage("login success");
			SessionUtil.bindSession(new Session(userId,loginRequestPacket.getUsername()),ctx.channel());
			System.out.println("userId: "+userId+"userName :"+loginRequestPacket.getUsername()+" 登录成功");
		}else {
			responsePacket.setStatus(ResponseStatus.FAILED);
			responsePacket.setMessage("login failed");
			System.out.println("client request handle failed");
		}
		return responsePacket;
	}

	private boolean userValidate(LoginRequestPacket loginRequestPacket) {

		//用户登录验证逻辑
		return true;
	}

	private String randomUserId(){
		return RandomUserIdGenerateUtil.getUserId();
	}
}
