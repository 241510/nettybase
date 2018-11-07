package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/10/31.
 */
public class JoinChatGroupRequestPacket extends Packet {

	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public Byte getCommand() {
		return Command.JOIN_CHAT_GROUP_REQUEST;
	}
}
