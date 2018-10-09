package netty.serialize;

import netty.protocol.serializeAlgorithm.SerializeAalgorithm;

/**
 * @author gaoyanwei
 * @date 2018/9/30.
 */
public class JsonSerializer implements Serializer {

	@Override
	public Byte serializeAlgorithm() {
		return SerializeAalgorithm.json;
	}

	@Override
	/**
	 * 使用Json来进行序列化
	 */
	public byte[] serialize() {
		return new byte[0];
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {
		return null;
	}
}
