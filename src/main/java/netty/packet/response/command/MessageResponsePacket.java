package netty.packet.response.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @daet 2018/10/12.
 */
public class MessageResponsePacket extends Packet{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public Byte getCommand() {
		return Command.MESSAGE_RESPONSE;
	}
}
