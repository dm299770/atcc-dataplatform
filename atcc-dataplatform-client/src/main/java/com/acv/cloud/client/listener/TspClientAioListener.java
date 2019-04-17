package com.acv.cloud.client.listener;

import com.acv.cloud.common.TspSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.utils.json.Json;

/**
 * @Author: leo
 * @Date: 2019/4/16 18:09
 */
public class TspClientAioListener implements ClientAioListener {
    private static Logger log = LoggerFactory.getLogger(TspClientAioListener.class);

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1) throws Exception {
        log.info("onAfterConnected channelContext:{}, isConnected:{}, isReconnect:{}", channelContext, b, b1);
        //连接后，需要把连接会话对象设置给channelContext
        channelContext.setAttribute(new TspSessionContext());
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int i) throws Exception {

    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int i) throws Exception {

    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b) throws Exception {
        log.info("onAfterSent channelContext:{}, packet:{}, isSentSuccess:{}", channelContext, Json.toJson(packet), b);
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long l) throws Exception {

    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) throws Exception {

    }
}
