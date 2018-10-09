package netty.util.selector;

import netty.protocol.serialize.JsonSerializer;
import netty.protocol.serialize.Serializer;
import netty.protocol.SerializeAlgorithmSign;

import java.util.concurrent.ConcurrentHashMap;

public class SerializeTypeSelector {

    private static final ConcurrentHashMap<Byte, Serializer> serializeMap = new ConcurrentHashMap<>();

    //定义序列化策略
    private static final JsonSerializer jsonSerialize = new JsonSerializer();
    static {
        serializeMap.put(SerializeAlgorithmSign.json,jsonSerialize);
    }

    public static Serializer selectSerializeStrategy(Byte serializeAlgorithmSign){
        return serializeMap.get(serializeAlgorithmSign);
    }
}
