package com.acv.cloud.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  一般生产项目中，都需要定义一个这样的SessionContext，用于保存连接的会话数据
 * @Author: leo
 * @Date: 2019/4/16 16:13
 */
public class TspSessionContext {
    private static Logger log = LoggerFactory.getLogger(TspSessionContext.class);

    private String token ;

    private String vin;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
