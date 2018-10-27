package netty.util;

import netty.Session;

import io.netty.channel.Channel;
import netty.attribute.Attributes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaoyanwei
 * @date 2018/10/23.
 */
public class SessionUtil {

	//userId -> channel
	private static final Map<String,Channel> userIdChannelMap = new ConcurrentHashMap<>();

	public static void bindSession(Session session , Channel channel){
		userIdChannelMap.put(session.getUserId(),channel);
		channel.attr(Attributes.SESSION).set(session);
	}

	public static void unbindSession(Channel channel){
		if (hasLogin(channel)){
			userIdChannelMap.remove(getSession(channel).getUserId());
			channel.attr(Attributes.SESSION).set(null);
		}
	}

	public static Session getSession(Channel channel) {
		return channel.attr(Attributes.SESSION).get();
	}

	public static boolean hasLogin(Channel channel) {
		return channel.hasAttr(Attributes.SESSION);
	}

	public static Channel getChannel(String userId){
		return userIdChannelMap.get(userId);
	}
}
