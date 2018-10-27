package netty.packet.response.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @daet 2018/10/12.
 */
public class MessageResponsePacket extends Packet{

	private String formUserId;
	private String fromUserName;
	private String message;

	public String getFormUserId() {
		return formUserId;
	}

	public void setFormUserId(String formUserId) {
		this.formUserId = formUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

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
