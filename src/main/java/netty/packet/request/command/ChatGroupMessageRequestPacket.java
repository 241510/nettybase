package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ChatGroupMessageRequestPacket extends Packet {

	private String groupId;

	private String groupMessage;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupMessage() {
		return groupMessage;
	}

	public void setGroupMessage(String groupMessage) {
		this.groupMessage = groupMessage;
	}

	@Override
	public Byte getCommand() {
		return Command.CHAT_GROUP_MESSAGE_REQUEST;
	}
}
