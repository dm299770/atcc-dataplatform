package com.acv.cloud;

import com.acv.cloud.server.NettyServer;
import com.acv.cloud.server.TspServerStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtccDataplatformGatwayApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(AtccDataplatformGatwayApplication.class, args);

        TspServerStarter tspServerStarter = new TspServerStarter();
        tspServerStarter.start();
        //NettyServer.start();
    }

}
