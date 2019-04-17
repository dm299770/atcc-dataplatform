package com.acv.cloud.server.handler;

import com.acv.cloud.common.TspSessionContext;
import com.acv.cloud.common.Type;
import com.acv.cloud.common.handler.AbsTspBsHandler;
import com.acv.cloud.common.packets.LoginReqBody;
import com.acv.cloud.common.packets.LoginRespBody;
import com.acv.cloud.common.packets.TspPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.json.Json;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: leo
 * @Date: 2019/4/16 18:15
 */
public class LoginReqHandler extends AbsTspBsHandler<LoginReqBody> {
    private static Logger log = LoggerFactory.getLogger(LoginReqHandler.class);
    java.util.concurrent.atomic.AtomicLong tokenSeq = new AtomicLong();

    @Override
    public Class<LoginReqBody> bodyClass() {
        return LoginReqBody.class;
    }

    @Override
    public Object handler(TspPacket packet, LoginReqBody bsBody, ChannelContext channelContext) throws Exception {
        log.info("收到登录请求消息:{}", Json.toJson(bsBody));
        LoginRespBody loginRespBody = new LoginRespBody();
        //loginRespBody.setCode(JoinGroupRespBody.Code.SUCCESS);
        loginRespBody.setToken(newToken());

        String vin = bsBody.getVin();
        Tio.bindUser(channelContext, vin);

        TspSessionContext tspSessionContext = (TspSessionContext) channelContext.getAttribute();
        tspSessionContext.setVin(vin);

        TspPacket respPacket = new TspPacket();
        respPacket.setType(Type.LOGIN_RESP);
        respPacket.setBody(Json.toJson(loginRespBody).getBytes(TspPacket.CHARSET));
        Tio.send(channelContext, respPacket);

        return null;
    }

    private String newToken() {
        return System.currentTimeMillis() + "_" + tokenSeq.incrementAndGet();
    }
}
