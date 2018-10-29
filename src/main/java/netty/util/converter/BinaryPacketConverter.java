package netty.util.converter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.exception.NettyException;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.Packet;
import netty.packet.request.command.MessageRequestPacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.command.MessageResponsePacket;
import netty.util.selector.CommandTypeSelector;
import netty.util.selector.SerializeTypeSelector;
import netty.protocol.serialize.Serializer;

public class BinaryPacketConverter {

    //魔数，用于识别
    private static final int MAGIC_NUMBER = 0x12345678;

    public static ByteBuf encode(Packet packet,ByteBuf buf, Byte serializeAlgorithmSign) {

        //获取二进制抽象结构（io类型的ByteBuf）
        //ByteBuf buf = ByteBufAllocator.DEFAULT.ioBuffer();

        //将要传输的主体数据做序列化
        byte[] bytes = SerializeTypeSelector.selectSerializer(serializeAlgorithmSign).serialize(packet);

        //拼装二进制协议数据包内容
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(serializeAlgorithmSign);
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        return buf;
    }

    public static Packet decode(ByteBuf buf) {

        //跳过魔数
        buf.skipBytes(4);
        //跳过协议
        buf.skipBytes(1);
        //获取协议中序列化算法表标识
        Byte serializeAlgorithmSign = buf.readByte();
        //获取协议中指令标识
        Byte command = buf.readByte();
        //获取二进制数据长度
        int length = buf.readInt();
        //获取二进制数据
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);

        Class<? extends Packet> packetType = getPacketType(command);
        Serializer serializer = getSerializer(serializeAlgorithmSign);

        if (packetType == null || serializer == null) {
            throw new NettyException("packetType and serializer must not be null", 9999);
        }

        return serializer.deserialize(packetType, bytes);
    }

    /**
     * 获取序列化方式
     * @param serializeAlgorithmSign
     * @return
     */
    private static Serializer getSerializer(Byte serializeAlgorithmSign) {
        return SerializeTypeSelector.selectSerializer(serializeAlgorithmSign);
    }

    /**
     * 获取指令数据包的Class类型
     * @param command
     * @return
     */
    private static Class<? extends Packet> getPacketType(Byte command) {
        Class clazz= CommandTypeSelector.getPacket(command);
        if (clazz == null){
			throw new NettyException("packet type not match");
		}
		return clazz;

    }
}
