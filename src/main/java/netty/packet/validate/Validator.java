package netty.packet.validate;

import netty.exception.NettyException;
import netty.packet.Packet;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.response.command.LoginResponsePacket;

public class Validator {


    public static boolean validate(Packet packet){

        if (packet instanceof LoginRequestPacket){
            //验证逻辑
            return true;
        }

        if (packet instanceof LoginResponsePacket){
            //验证逻辑
            return true;
        }

        throw new NettyException("packet type no correct");
    }

}
