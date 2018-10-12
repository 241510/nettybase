package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.exception.NettyException;
import netty.packet.Packet;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.command.MessageResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.packet.validate.Validator;
import netty.protocol.serializeAlgorithm.SerializeAlgorithmSign;
import netty.util.LoginUtil;
import netty.util.converter.BinaryPacketConverter;

import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		//获取数据
		/*String message = "hello server";
		ByteBuf buffer = ByteBufObtain.getByteBuf(ctx,message);*/

		//客户端传递登录的指令
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
		loginRequestPacket.setUid("001");
		loginRequestPacket.setUsername("username");
		loginRequestPacket.setPassword("password");
		System.out.println("向服务端发送登录请求");
		ByteBuf buffer = BinaryPacketConverter.encode(loginRequestPacket, SerializeAlgorithmSign.json);

		//获取连接ID
		ctx.channel().id();
		//写数据
		ctx.channel().writeAndFlush(buffer);

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       /* ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date()+":客户端接收到服务端传回消息:"+buf.toString(Charset.forName("utf-8")));*/

		ByteBuf recBuf = (ByteBuf) msg;
		//解码
		Packet packet = BinaryPacketConverter.decode(recBuf);
		//传输的指令数据包合法性校验
		Validator.validate(packet);
		if (packet instanceof LoginResponsePacket) {
			LoginResponsePacket responsePacket = (LoginResponsePacket) packet;
			if (responsePacket.getStatus().equals(ResponseStatus.SUCCESS)) {
				LoginUtil.markAsLogin(ctx.channel());
				System.out.println("客户端登录成功");
			} else {
				System.out.println(new Date() + "客户端登录失败 , 原因：" + responsePacket.getMessage());
			}
		}else if (packet instanceof MessageResponsePacket){
			MessageResponsePacket responsePacket = (MessageResponsePacket) packet;
			System.out.println(new Date()+"收到服务端的消息："+responsePacket.getMessage());
		}

	}
}
