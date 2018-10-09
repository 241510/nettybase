package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.packet.Packet;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.validate.Validator;
import netty.protocol.SerializeAlgorithmSign;
import netty.util.converter.BinaryPacketConverter;

public class FirstClientHandler extends ChannelInboundHandlerAdapter{

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

       ByteBuf recBuf = (ByteBuf)msg;
       //解码
        Packet packet = BinaryPacketConverter.decode(recBuf);
        if (Validator.validate(packet)){
            System.out.println("accept server response success");
        }else{
            System.out.println("accept server response failed");
        }

    }
}
