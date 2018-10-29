package netty.util.selector;

import netty.protocol.serialize.serializeImpl.JsonSerializer;
import netty.protocol.serialize.Serializer;
import netty.protocol.serializeAlgorithm.SerializeAlgorithmSign;

import java.util.concurrent.ConcurrentHashMap;

public class SerializeTypeSelector {

    private static final ConcurrentHashMap<Byte, Serializer> serializeMap = new ConcurrentHashMap<>();

    //定义序列化策略
    private static final JsonSerializer defaultSerializer = new JsonSerializer();

    static {
        serializeMap.put(SerializeAlgorithmSign.json,defaultSerializer);
    }

	public static Serializer selectSerializer(){
    	return selectSerializeStrategy(null);
	}

	public static Serializer selectSerializer(Byte serializeAlgorithmSign){
		return selectSerializeStrategy(serializeAlgorithmSign);
	}

    private static Serializer selectSerializeStrategy(Byte serializeAlgorithmSign){
		if(serializeAlgorithmSign == null){
			return defaultSerializer;
		}
        return serializeMap.get(serializeAlgorithmSign);
    }
}
