package com.acv.cloud.common.handler;

import com.acv.cloud.common.Const;
import com.acv.cloud.common.intf.TspBsHandlerIntf;
import com.acv.cloud.common.packets.TspPacket;
import com.acv.cloud.common.packets.base.BaseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * @Author: leo
 * @Date: 2019/4/16 15:59
 */
public abstract class AbsTspBsHandler<T extends BaseBody> implements TspBsHandlerIntf {

    private static Logger log = LoggerFactory.getLogger(AbsTspBsHandler.class);

    public abstract Class<T> bodyClass();

    public Object handler(TspPacket packet, ChannelContext channelContext) throws Exception{
        String jsonStr = null;
        T bsBody = null;
        if(packet.getBody()!=null){
            jsonStr = new String(packet.getBody(), Const.CHARSET);
            bsBody = Json.toBean(jsonStr,bodyClass());

        }
        return handler(packet,bsBody,channelContext);
    }
    public abstract Object handler(TspPacket packet , T bsBody , ChannelContext channelContext) throws Exception;
}
