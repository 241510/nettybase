package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/10/11.
 */
public class MessageRequestPacket extends Packet{

	private String toUserId;
	private String message;

	public MessageRequestPacket(){

	}

	public MessageRequestPacket(String toUserId,String message){
		this.toUserId = toUserId;
		this.message = message;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

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
