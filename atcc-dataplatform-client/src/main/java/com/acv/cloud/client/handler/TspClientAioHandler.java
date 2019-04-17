package com.acv.cloud.client.handler;

import com.acv.cloud.common.Type;
import com.acv.cloud.common.handler.AbsTspBsHandler;
import com.acv.cloud.common.handler.TspAbsAioHandler;
import com.acv.cloud.common.packets.TspPacket;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: leo
 * @Date: 2019/4/16 18:05
 */
public class TspClientAioHandler extends TspAbsAioHandler implements ClientAioHandler {

    private static Map<Byte, AbsTspBsHandler<?>> handlerMap = new HashMap<>();
    static {
        handlerMap.put(Type.LOGIN_RESP, new LoginRespHandler());

    }
    private static TspPacket heartbeatPacket = new TspPacket(Type.HEART_BEAT_REQ, null);

    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        TspPacket tspPacket = (TspPacket) packet;
        Byte type = tspPacket.getType();
        AbsTspBsHandler<?> tspBsHandler = handlerMap.get(type);
        tspBsHandler.handler(tspPacket, channelContext);
        return;
    }

    /**
     * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
     */
    @Override
    public TspPacket heartbeatPacket() {
        return heartbeatPacket;
    }
}
