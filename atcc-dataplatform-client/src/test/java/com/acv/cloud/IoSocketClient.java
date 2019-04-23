package com.acv.cloud;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: leo
 * @Date: 2019/4/19 14:39
 */
public class IoSocketClient {
    static int socketCount;
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8888);
                socket.getOutputStream().write(("login socket:"+socketCount).getBytes());
                socket.getOutputStream().flush();
//                while (true) {
//                    try {
//                        socket.getOutputStream().write(("login socket:"+socketCount).getBytes());
//                        socket.getOutputStream().flush();
//                        socketCount++;
//                    } catch (Exception e) {
//                    }
//                }
            } catch (IOException e) {

            }
        }).start();
    }


}
