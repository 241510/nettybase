package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ListChatGroupMembersRequestPacket extends Packet{

	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public Byte getCommand() {
		return Command.LIST_CHAT_GROUP_MEMBERS_REQUEST;
	}
}
