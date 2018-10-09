package netty.protocol.serialize;

import com.alibaba.fastjson.JSON;
import netty.packet.Packet;
import netty.protocol.SerializeAlgorithmSign;

public class JsonSerializer implements Serializer {


    @Override
    public Byte getSerializeAlgorithm() {
        return SerializeAlgorithmSign.json;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<? extends Packet> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
