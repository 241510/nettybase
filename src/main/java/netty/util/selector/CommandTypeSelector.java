package netty.util.selector;

import netty.packet.request.command.LoginRequestPacket;
import netty.packet.Packet;
import netty.packet.request.command.MessageRequestPacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.command.MessageResponsePacket;
import netty.protocol.command.Command;

import java.util.concurrent.ConcurrentHashMap;

public class CommandTypeSelector {

    private static final ConcurrentHashMap<Byte,Packet> commandMap = new ConcurrentHashMap<>();
    private static final LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
    private static final LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
    private static final MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
    private static final MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

    static {
        commandMap.put(Command.LOGIN_REQUEST,loginRequestPacket);
        commandMap.put(Command.LOGIN_RESPONSE,loginResponsePacket);
        commandMap.put(Command.MESSAGE_REQUEST,messageRequestPacket);
        commandMap.put(Command.MESSAGE_RESPONSE,messageResponsePacket);
    }

    public static Packet getPacket(Byte command){
        return commandMap.get(command);
    }
 }
