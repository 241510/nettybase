package netty.protocol;

/**
 * @author gaoyanwei
 * @date 2018/9/29.
 */
public class loginRequestPacket extends Packet{

	private String uid;
	private String userName;
	private String password;

	@Override
	public int getCommand() {
		return Command.LOGIN_REQUEST;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
