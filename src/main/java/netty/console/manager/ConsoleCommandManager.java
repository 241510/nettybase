package netty.console.manager;

import io.netty.channel.Channel;
import netty.console.command.ConsoleCommand;
import netty.console.command.CreateChatGroupConsoleCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author gaoyanwei
 * @date 2018/10/27.
 */
public class ConsoleCommandManager implements ConsoleCommand{

	private Map<String , ConsoleCommand> consoleCommandMap;

	public ConsoleCommandManager(){
		consoleCommandMap = new HashMap<>();
		//consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
		//consoleCommandMap.put("logout"new LogoutConsoleCommand());
		consoleCommandMap.put("createChatGroup",new CreateChatGroupConsoleCommand());
	}

	@Override
	public void exec(Scanner scanner, Channel channel) {

		//获取指令
		String command = scanner.next();
		ConsoleCommand actualConsoleCommand = consoleCommandMap.get(command);
		if (actualConsoleCommand != null){
			actualConsoleCommand.exec(scanner,channel);
		}else{
			System.out.println("无法识别【"+command+"指令，请重新输入");
		}
	}
}
