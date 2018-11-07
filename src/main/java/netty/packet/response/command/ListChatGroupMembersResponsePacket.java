package netty.packet.response.command;

import netty.Session;
import netty.packet.Packet;
import netty.protocol.command.Command;

import java.util.List;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class ListChatGroupMembersResponsePacket extends Packet {

	private String groupId;

	private List<Session> sessionList;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
	}

	@Override
	public Byte getCommand() {
		return Command.LIST_CHAT_GROUP_MEMBERS_RESPONSE;
	}
}
