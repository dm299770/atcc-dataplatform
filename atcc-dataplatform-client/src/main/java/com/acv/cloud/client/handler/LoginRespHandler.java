package com.acv.cloud.client.handler;

import com.acv.cloud.common.TspSessionContext;
import com.acv.cloud.common.handler.AbsTspBsHandler;
import com.acv.cloud.common.packets.LoginRespBody;
import com.acv.cloud.common.packets.TspPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * @Author: leo
 * @Date: 2019/4/16 18:01
 */
public class LoginRespHandler extends AbsTspBsHandler<LoginRespBody> {
    private static Logger log = LoggerFactory.getLogger(LoginRespHandler.class);

    @Override
    public Class<LoginRespBody> bodyClass() {
        return LoginRespBody.class;
    }

    @Override
    public Object handler(TspPacket packet, LoginRespBody bsBody, ChannelContext channelContext) throws Exception {
        System.out.println("收到登录响应消息:" + Json.toJson(bsBody));
        if (LoginRespBody.Code.SUCCESS.equals(bsBody.getCode())) {
            TspSessionContext tspSessionContext = (TspSessionContext) channelContext.getAttribute();
            tspSessionContext.setToken(bsBody.getToken());
            System.out.println("登录成功，token是:" + bsBody.getToken());
        }

        return null;
    }
}
