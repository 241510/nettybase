package netty.packet.response.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

public class LoginResponsePacket extends Packet{


    private String status;
    private String message;

    @Override
    public Byte getCommand() {

        return Command.LOGIN_RESPONSE;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
