package netty.protocol;

/**
 * @author gaoyanwei
 * @date 2018/9/29.
 */
public abstract class Packet {

	private Byte vserion = 1;

	public abstract int getCommand();

}
