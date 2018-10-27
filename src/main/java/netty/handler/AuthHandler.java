package netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.util.LoginUtil;

/**
 * @author gaoyanwei
 * @date 2018/10/22.
 */
public class AuthHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		if (!LoginUtil.hasMark(ctx.channel())){
			ctx.channel().close();
		}else{
			//将认证处理逻辑移除
			ctx.pipeline().remove(this);
			super.channelRead(ctx,msg);
		}
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){

		if (LoginUtil.hasMark(ctx.channel())){
			System.out.println("认证通过，将该处理逻辑移除");
		}else {
			System.out.println("无登录验证，强制关闭连接");
		}

	}


}
