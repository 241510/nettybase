package netty.packet.response.command;

import netty.packet.Packet;
import netty.protocol.command.Command;

public class LoginResponsePacket extends Packet{

	private String userId;
	private String userName;
    private String status;
    private String message;

    @Override
    public Byte getCommand() {

        return Command.LOGIN_RESPONSE;

    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
