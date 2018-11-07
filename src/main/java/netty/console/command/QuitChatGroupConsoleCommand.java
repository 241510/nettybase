package netty.console.command;

import io.netty.channel.Channel;
import netty.packet.request.command.QuitChatGroupRequestPacket;

import java.util.Scanner;

/**
 * @author gaoyanwei
 * @date 2018/11/7.
 */
public class QuitChatGroupConsoleCommand implements ConsoleCommand {
	@Override
	public void exec(Scanner scanner, Channel channel) {

		QuitChatGroupRequestPacket requestPacket = new QuitChatGroupRequestPacket();
		System.out.print("请输入想要退出的群聊ID：");
		String groupId = scanner.next();
		requestPacket.setGroupId(groupId);
		channel.writeAndFlush(requestPacket);

	}
}
