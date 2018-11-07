package netty.packet.response.command;

import netty.packet.Packet;
import netty.packet.response.status.ResponseStatus;
import netty.protocol.command.Command;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class JoinChatGroupResponsePacket extends Packet {

	/**
	 * 响应状态
	 */
	private String status;

	/**
	 * 群ID
	 */
	private String groupId;

	/**
	 * 失败原因（成功为空）
	 */
	private String reason = "";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public Byte getCommand() {
		return Command.JOIN_CHAT_GROUP_RESPONSE;
	}

	public boolean isSuccess(){
		return status.equals(ResponseStatus.SUCCESS);
	}
}
