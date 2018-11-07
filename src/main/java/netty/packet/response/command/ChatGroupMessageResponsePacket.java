package netty.packet.response.command;

import netty.Session;
import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ChatGroupMessageResponsePacket extends Packet {

	private String fromGroupId;

	private String groupMessage;

	private Session fromUser;

	public String getFromGroupId() {
		return fromGroupId;
	}

	public void setFromGroupId(String fromGroupId) {
		this.fromGroupId = fromGroupId;
	}

	public String getGroupMessage() {
		return groupMessage;
	}

	public void setGroupMessage(String groupMessage) {
		this.groupMessage = groupMessage;
	}

	public Session getFromUser() {
		return fromUser;
	}

	public void setFromUser(Session fromUser) {
		this.fromUser = fromUser;
	}

	@Override
	public Byte getCommand() {
		return Command.CHAT_GROUP_MESSAGE_RESPONSE;
	}
}
