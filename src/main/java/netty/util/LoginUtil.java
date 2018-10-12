package netty.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import netty.attribute.Attributes;

import java.nio.charset.Charset;

/**
 * @author gaoyanwei
 * @date 2018/10/11.
 */
public class LoginUtil {

	public static void markAsLogin(Channel channel){
		channel.attr(Attributes.LOGIN).set(true);
	}

	public static boolean hasMark(Channel channel){
		Attribute<Boolean> login = channel.attr(Attributes.LOGIN);
		return login.get()!= null;
	}
}
