package com.acv.cloud.common.packets.base;

/**
 * @Author: leo
 * @Date: 2019/4/16 16:00
 */
public class BaseBody {

    /**
     * 消息发送时间
     */
    private Long time = System.currentTimeMillis();

    /**
     * @return the currTime
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param currTime the currTime to set
     */
    public void setTime(Long time) {
        this.time = time;
    }
}
