package netty.console.command;

import io.netty.channel.Channel;
import netty.packet.request.command.CreateChatGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author gaoyanwei
 * @date 2018/10/27.
 */
public class CreateChatGroupConsoleCommand implements ConsoleCommand{

	private static final String USER_ID_SEPARATOR = ",";

	@Override
	public void exec(Scanner scanner, Channel channel) {

		CreateChatGroupRequestPacket requestPacket = new CreateChatGroupRequestPacket();
		//不使用println是为了防止出现视觉上的混乱
		System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
		String userIds = scanner.next();
		requestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SEPARATOR)));
		channel.writeAndFlush(requestPacket);

	}
}
