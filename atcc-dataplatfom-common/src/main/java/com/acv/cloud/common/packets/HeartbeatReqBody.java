package com.acv.cloud.common.packets;

import com.acv.cloud.common.packets.base.BaseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: leo
 * @Date: 2019/4/17 13:40
 */
public class HeartbeatReqBody extends BaseBody {

    private static Logger log = LoggerFactory.getLogger(HeartbeatReqBody.class);

    //连接状态
    private int status;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
