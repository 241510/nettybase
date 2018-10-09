package netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.packet.Packet;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.packet.validate.Validator;
import netty.protocol.SerializeAlgorithmSign;
import netty.util.converter.BinaryPacketConverter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        //System.out.println(new Date() + ": 服务端接收到的信息:" + byteBuf.toString(Charset.forName("utf-8")));

        //服务端回数据逻辑
        /*System.out.println("server return message--------------");
        String message = "hello client";
        ByteBuf resBuf = ByteBufObtain.getByteBuf(ctx,message);
        ctx.channel().writeAndFlush(resBuf);*/

        //进行解码
        Packet packet = BinaryPacketConverter.decode(byteBuf);
        //拼装登录响应数据包
        LoginResponsePacket responsePacket = new LoginResponsePacket();

        if (Validator.validate(packet)) {
            responsePacket.setStatus(ResponseStatus.SUCCESS);
            responsePacket.setMessage("register success");
            System.out.println("client request handle success");
        } else {
            responsePacket.setStatus(ResponseStatus.FAILED);
            responsePacket.setMessage("register failed");
            System.out.println("client request handle failed");
        }

        //二进制数据包组装
        ByteBuf buf = BinaryPacketConverter.encode(responsePacket, SerializeAlgorithmSign.json);
        //发送客户端
        ctx.channel().writeAndFlush(buf);

    }

}
