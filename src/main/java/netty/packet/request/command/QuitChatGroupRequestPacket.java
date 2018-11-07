package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class QuitChatGroupRequestPacket extends Packet
{
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public Byte getCommand() {
		return Command.QUIT_CHAT_GROUP_REQUEST;
	}
}
