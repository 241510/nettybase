package netty.util;

import io.netty.channel.group.ChannelGroup;
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
	//groupId -> channelGroup
	private static final Map<String,ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

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

	public static void putChannelGroup(String groupId,ChannelGroup channelGroup){
		groupIdChannelGroupMap.put(groupId,channelGroup);
	}
	public static ChannelGroup getChannelGroup(String groupId){
		return groupIdChannelGroupMap.get(groupId);
	}

	public static void removeChannelGroup(String groupId){
		groupIdChannelGroupMap.remove(groupId);
	}
}
