package netty.serialize;

/**
 * @author gaoyanwei
 * @date 2018/9/29.
 */
public interface Serializer {

	//获取序列化算法标识
	Byte serializeAlgorithm();

	//序列化借（编码）
	byte[] serialize();

	//序列化解码
	<T> T deserialize(Class<T> clazz , byte[] bytes);

}
