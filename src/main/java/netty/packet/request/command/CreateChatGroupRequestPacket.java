package netty.packet.request.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

import java.util.List;

/**
 * @author gaoyanwei
 * @date 2018/10/27.
 */
public class CreateChatGroupRequestPacket extends Packet{

	private List<String> userIdList;

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	@Override
	public Byte getCommand() {

		return Command.CREATE_CHAT_GROUP_REQUEST;
	}
}
