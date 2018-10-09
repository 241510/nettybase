package netty.exception;

public class NettyException extends RuntimeException {

    private String message;
    private int code;

    public NettyException() {
        super();
    }

    public NettyException(String message) {
        super(message);
    }

    public NettyException(String message,int code) {
        super(message);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
