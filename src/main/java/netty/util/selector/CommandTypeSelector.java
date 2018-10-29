package netty.util.selector;

import netty.packet.request.command.CreateChatGroupRequestPacket;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.Packet;
import netty.packet.request.command.MessageRequestPacket;
import netty.packet.response.command.CreateChatGroupResponsePacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.command.MessageResponsePacket;
import netty.protocol.command.Command;

import java.util.concurrent.ConcurrentHashMap;

public class CommandTypeSelector {

    private static final ConcurrentHashMap<Byte,Class<? extends Packet>> commandMap = new ConcurrentHashMap<>();

    static {
        commandMap.put(Command.LOGIN_REQUEST,LoginRequestPacket.class);
        commandMap.put(Command.LOGIN_RESPONSE,LoginResponsePacket.class);
        commandMap.put(Command.MESSAGE_REQUEST,MessageRequestPacket.class);
        commandMap.put(Command.MESSAGE_RESPONSE,MessageResponsePacket.class);
        commandMap.put(Command.CREATE_CHAT_GROUP_REQUEST,CreateChatGroupRequestPacket.class);
        commandMap.put(Command.CREATE_CHAT_GROUP_RESPONSE, CreateChatGroupResponsePacket.class);
    }

    public static Class<? extends Packet> getPacket(Byte command){
        return commandMap.get(command);
    }
 }
