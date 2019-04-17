package com.acv.cloud.server.handler;

import com.acv.cloud.common.Type;
import com.acv.cloud.common.handler.AbsTspBsHandler;
import com.acv.cloud.common.handler.TspAbsAioHandler;
import com.acv.cloud.common.packets.TspPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: leo
 * @Date: 2019/4/16 17:34
 */
public class TspServerAioHandler extends TspAbsAioHandler implements ServerAioHandler {

    private static Logger log = LoggerFactory.getLogger(TspServerAioHandler.class);
    private static Map<Byte, AbsTspBsHandler<?>> handlerMap = new HashMap<>();
    static{
        handlerMap.put(Type.LOGIN_REQ, new LoginReqHandler());
        handlerMap.put(Type.HEART_BEAT_REQ,new HeartbeatReqHandler());
    }
    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        TspPacket tspPacket = (TspPacket) packet;
        Byte type = tspPacket.getType();
        AbsTspBsHandler<?> tspBsHandler = handlerMap.get(type);
        if (tspBsHandler == null) {
            log.error("{}, 找不到处理类，type:{}", channelContext, type);
            return;
        }
        tspBsHandler.handler(tspPacket, channelContext);
        return;
    }
}
