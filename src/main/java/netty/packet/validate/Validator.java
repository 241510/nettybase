package netty.packet.validate;

import netty.exception.NettyException;
import netty.packet.Packet;
import netty.packet.request.command.LoginRequestPacket;
import netty.packet.request.command.MessageRequestPacket;
import netty.packet.response.command.LoginResponsePacket;
import netty.packet.response.status.ResponseStatus;
import netty.util.LoginUtil;

public class Validator {


    public static boolean validate(Packet packet) throws Exception{

    	//登录指令请求包验证
        if (packet instanceof LoginRequestPacket){
            //验证逻辑,用户的信息是否正确
			return true;
        }
		//登录指令响应包验证
        if (packet instanceof LoginResponsePacket){
            //验证逻辑
			return true;
        }
		//传递信息报验证
		if (packet instanceof MessageRequestPacket){
			//验证逻辑
			return true;
		}

        throw new NettyException("packet type no correct");
    }

}
