package com.acv.cloud.common.packets;
import org.tio.core.intf.Packet;

/**
 * @Author: leo
 * @Date: 2019/4/16 16:02
 */
public class TspPacket extends Packet {

    private static final long serialVersionUID = -5481926483435771100L;
    public static final int HEADER_LENGHT = 5;//消息头的长度 1+4
    public static final String CHARSET = "utf-8";

    /**
     * 消息类型，其值在org.tio.examples.showcase.common.Type中定义
     */
    private byte type;

    private byte[] body;

    public TspPacket(){

    }

    public TspPacket(byte type, byte[] body) {
        this.type = type;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
