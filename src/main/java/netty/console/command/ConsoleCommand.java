package netty.console.command;

import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author gaoyanwei
 * @date 2018/10/27.
 */
public interface ConsoleCommand {

	void exec(Scanner scanner , Channel channel);
}
