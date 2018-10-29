package netty.util;

import java.util.Random;

/**
 * @author gaoyanwei
 * @date 2018/10/23.
 */
public class RandomUserIdGenerateUtil {

	private static final Random random = new Random();

	public static String getUserId(){
		return "uid-"+(random.nextInt(9000)+1000);
	}
}
