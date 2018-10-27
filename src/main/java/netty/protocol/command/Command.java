package netty.protocol.command;

public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

	Byte MESSAGE_REQUEST = 3;

	Byte MESSAGE_RESPONSE = 4;

	Byte CREATE_CHAT_GROUP_REQUEST = 5;

	Byte CREATE_CHAT_GROUP_RESPONSE = 6;

}
