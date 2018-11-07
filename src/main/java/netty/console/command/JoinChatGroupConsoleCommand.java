package netty.console.command;

import io.netty.channel.Channel;
import netty.packet.request.command.JoinChatGroupRequestPacket;

import java.util.Scanner;

/**
 * @author gaoyanwei
 * @date 2018/10/31.
 */
public class JoinChatGroupConsoleCommand implements ConsoleCommand {
	@Override
	public void exec(Scanner scanner, Channel channel) {
		JoinChatGroupRequestPacket requestPacket = new JoinChatGroupRequestPacket();

		System.out.print("输入groupId,加入群聊:");
		String groupId = scanner.next();
		requestPacket.setGroupId(groupId);
		channel.writeAndFlush(requestPacket);
	}
}
