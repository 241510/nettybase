package netty.packet;

public abstract class Packet {

    //版本
    private Byte version = 1;

    /**
     * 获取指令
     * @return
     */
    public abstract Byte getCommand();

    /**
     * 获取版本号
     * @return
     */
    public Byte getVersion(){
        return this.version;
    }

}
