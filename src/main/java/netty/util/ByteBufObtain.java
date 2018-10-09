package netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;

public class ByteBufObtain {

    public static ByteBuf getByteBuf(ChannelHandlerContext ctx , String message){

        //获取netty的二进制抽象ByteBuf
        ByteBuf buf = ctx.alloc().buffer();
        //指定字符串的字符集为utf-8
        byte[] bytes = message.getBytes(Charset.forName("utf-8"));
        //填充数据到ByteBuf,并返回
        return buf.writeBytes(bytes);
    }
}
