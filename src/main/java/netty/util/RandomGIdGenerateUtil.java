package netty.util;

import java.util.Random;

/**
 * @author gaoyanwei
 * @date 2018/10/29.
 */
public class RandomGIdGenerateUtil {

	private static final Random random = new Random();

	public static String getGId(){
		return "gid-"+(random.nextInt(9000)+1000);
	}
}
