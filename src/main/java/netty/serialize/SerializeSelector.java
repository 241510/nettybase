package netty.serialize;

/**
 * @author gaoyanwei
 * @date 2018/9/30.
 */
public class SerializeSelector {
	/**
	 * 根据序列化算法标识获取相应的序列化算法
	 * @param selectSign
	 * @return
	 */
	public static Serializer selectMatchedSerializer(int selectSign){

		Serializer serializer = null;
		switch (selectSign){
			case 1:
				serializer = new JsonSerializer();
		}

		return serializer;
	}
}
