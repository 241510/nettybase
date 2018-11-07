package netty.util.selector;

import netty.packet.Packet;
import netty.packet.request.command.*;
import netty.packet.response.command.*;
import netty.protocol.command.Command;

import java.util.concurrent.ConcurrentHashMap;

public class CommandTypeSelector {

    private static final ConcurrentHashMap<Byte,Class<? extends Packet>> commandTypeMap = new ConcurrentHashMap<>();

    static {
        commandTypeMap.put(Command.LOGIN_REQUEST,LoginRequestPacket.class);
        commandTypeMap.put(Command.LOGIN_RESPONSE,LoginResponsePacket.class);
        commandTypeMap.put(Command.MESSAGE_REQUEST,MessageRequestPacket.class);
        commandTypeMap.put(Command.MESSAGE_RESPONSE,MessageResponsePacket.class);
        commandTypeMap.put(Command.CREATE_CHAT_GROUP_REQUEST,CreateChatGroupRequestPacket.class);
        commandTypeMap.put(Command.CREATE_CHAT_GROUP_RESPONSE, CreateChatGroupResponsePacket.class);
        commandTypeMap.put(Command.JOIN_CHAT_GROUP_REQUEST, JoinChatGroupRequestPacket.class);
        commandTypeMap.put(Command.JOIN_CHAT_GROUP_RESPONSE, JoinChatGroupResponsePacket.class);
        commandTypeMap.put(Command.QUIT_CHAT_GROUP_REQUEST, QuitChatGroupRequestPacket.class);
        commandTypeMap.put(Command.QUIT_CHAT_GROUP_RESPONSE, QuitChatGroupResponsePacket.class);
        commandTypeMap.put(Command.LIST_CHAT_GROUP_MEMBERS_REQUEST,ListChatGroupMembersRequestPacket.class);
        commandTypeMap.put(Command.LIST_CHAT_GROUP_MEMBERS_RESPONSE,ListChatGroupMembersResponsePacket.class);
		commandTypeMap.put(Command.CHAT_GROUP_MESSAGE_REQUEST,ChatGroupMessageRequestPacket.class);
		commandTypeMap.put(Command.CHAT_GROUP_MESSAGE_RESPONSE,ChatGroupMessageResponsePacket.class);
    }

    public static Class<? extends Packet> getPacket(Byte command){
        return commandTypeMap.get(command);
    }
 }
