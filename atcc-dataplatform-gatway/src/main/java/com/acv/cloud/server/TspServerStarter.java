package com.acv.cloud.server;

import com.acv.cloud.server.handler.TspServerAioHandler;
import com.acv.cloud.server.listener.TspServerAioListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;

/**
 * @Author: leo
 * @Date: 2019/4/16 17:45
 */
public class TspServerStarter {

    private static Logger log = LoggerFactory.getLogger(TspServerStarter.class);
    TspServerAioHandler tspServerAioHandler = new TspServerAioHandler();
    TspServerAioListener tspServerAioListener = new TspServerAioListener();
    ServerGroupContext serverGroupContext = new ServerGroupContext(tspServerAioHandler, tspServerAioListener);

    TioServer tioServer = new TioServer(serverGroupContext);

    public void start() throws Exception{
        tioServer.start("127.0.0.1",8888);
    }


    public TspServerAioHandler getTspServerAioHandler() {
        return tspServerAioHandler;
    }

    public void setTspServerAioHandler(TspServerAioHandler tspServerAioHandler) {
        this.tspServerAioHandler = tspServerAioHandler;
    }

    public TspServerAioListener getTspServerAioListener() {
        return tspServerAioListener;
    }

    public void setTspServerAioListener(TspServerAioListener tspServerAioListener) {
        this.tspServerAioListener = tspServerAioListener;
    }

}
