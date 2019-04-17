package com.acv.cloud.common.intf;

import com.acv.cloud.common.packets.TspPacket;
import org.tio.core.ChannelContext;

/**
 * @Author: leo
 * @Date: 2019/4/16 16:01
 */
public interface TspBsHandlerIntf {
    /**
     *
     * @param packet
     * @param channelContext
     * @return
     * @throws Exception
     */
    public Object handler(TspPacket packet, ChannelContext channelContext) throws Exception;

}
