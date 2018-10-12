package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/10/11.
 */
public class MessageRequestPacket extends Packet{

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Byte getCommand() {
		return Command.MESSAGE_REQUEST;
	}
}
