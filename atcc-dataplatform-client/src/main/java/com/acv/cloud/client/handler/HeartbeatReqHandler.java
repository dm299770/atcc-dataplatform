package com.acv.cloud.client.handler;

import com.acv.cloud.common.handler.AbsTspBsHandler;
import com.acv.cloud.common.packets.HeartbeatReqBody;
import com.acv.cloud.common.packets.TspPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * 心跳处理
 * @Author: leo
 * @Date: 2019/4/17 13:39
 */
public class HeartbeatReqHandler extends AbsTspBsHandler<HeartbeatReqBody> {

    private static Logger log = LoggerFactory.getLogger(HeartbeatReqHandler.class);

    @Override
    public Class<HeartbeatReqBody> bodyClass() {
        return HeartbeatReqBody.class;
    }
    @Override
    public Object handler(TspPacket packet, HeartbeatReqBody bsBody, ChannelContext channelContext) throws Exception {
        //心跳消息,啥也不用做
        return null;
    }
}
