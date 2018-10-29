package netty.protocol.serialize;

import netty.packet.Packet;
import netty.protocol.serialize.serializeImpl.JsonSerializer;

public interface Serializer {

	/**
     * 获取序列化算法标识
     */
    Byte getSerializeAlgorithm();

    /**
     * java对象序列化
     */
    byte[] serialize(Object object);

    /**
     * 将二进制数据解码为java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<? extends Packet> clazz , byte[] bytes);
}
