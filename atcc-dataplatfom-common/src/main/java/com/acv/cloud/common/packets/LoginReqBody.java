package com.acv.cloud.common.packets;

import com.acv.cloud.common.packets.base.BaseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: leo
 * @Date: 2019/4/16 18:16
 */
public class LoginReqBody extends BaseBody {

    private static Logger log = LoggerFactory.getLogger(LoginReqBody.class);

    private String vin ;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
