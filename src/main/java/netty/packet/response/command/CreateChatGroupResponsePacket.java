package netty.packet.response.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

import java.util.List;

/**
 * @author gaoyanwei
 * @date 2018/10/29.
 */
public class CreateChatGroupResponsePacket extends Packet{

	/**
	 * 处理状态
	 */
	private boolean handleStatus;

	/**
	 * 聊天组ID
	 */
	private String groupId;

	/**
	 * 组内成员
	 */
	private List<String> userNameList;

	public boolean isHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(boolean handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

	@Override
	public Byte getCommand() {
		return Command.CREATE_CHAT_GROUP_RESPONSE;
	}
}
