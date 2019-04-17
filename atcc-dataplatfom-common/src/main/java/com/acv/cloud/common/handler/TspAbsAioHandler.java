package com.acv.cloud.common.handler;

import com.acv.cloud.common.packets.TspPacket;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * @Author: leo
 * @Date: 2019/4/16 16:16
 */
public abstract class TspAbsAioHandler implements AioHandler {

    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 消息头：type + bodyLength
     * 消息体：byte[]
     */
    @Override
    public TspPacket decode(ByteBuffer buffer , int limit , int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        //可读数据，小于头部的固定长度，直接返回null，这样tio框架会自动把本次收到的数据暂存起来，并和下次收到的数据组合起来
        if (readableLength < TspPacket.HEADER_LENGHT) {
            return null;
        }

        //position的值不一定是0，但是
        //消息类型
        byte type = buffer.get();

        int bodyLength = buffer.getInt();

        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }

        int neededLength = TspPacket.HEADER_LENGHT + bodyLength;
        int test = readableLength - neededLength;
        if (test < 0) // 不够消息体长度(剩下的buffe组不了消息体)
        {
            return null;
        } else {
            TspPacket imPacket = new TspPacket();
            imPacket.setType(type);
            if (bodyLength > 0) {
                byte[] dst = new byte[bodyLength];
                buffer.get(dst);
                imPacket.setBody(dst);
            }
            return imPacket;
        }
    }

    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 消息头：type + bodyLength
     * 消息体：byte[]
     */
    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        TspPacket showcasePacket = (TspPacket) packet;
        byte[] body = showcasePacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }

        //总长度是消息头的长度+消息体的长度
        int allLen = TspPacket.HEADER_LENGHT + bodyLen;

        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        buffer.order(groupContext.getByteOrder());

        //写入消息类型
        buffer.put(showcasePacket.getType());
        //写入消息体长度
        buffer.putInt(bodyLen);

        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }
}