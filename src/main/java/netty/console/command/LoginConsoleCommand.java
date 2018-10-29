package netty.console.command;

import io.netty.channel.Channel;
import netty.packet.request.command.LoginRequestPacket;

import java.util.Scanner;

/**
 * @author gaoyanwei
 * @date 2018/10/29.
 */
public class LoginConsoleCommand implements ConsoleCommand {
	@Override
	public void exec(Scanner scanner, Channel channel) {
		LoginRequestPacket requestPacket = new LoginRequestPacket();
		System.out.println("输入用户名登录:");
		String userName = scanner.next();

		requestPacket.setUsername(userName);
		requestPacket.setPassword("pwd");//默认

		channel.writeAndFlush(requestPacket);
		//等待服务端认证登录信息
		waitForLoginResponse();

	}

	private static void waitForLoginResponse() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}
