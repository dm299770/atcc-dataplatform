package com.acv.cloud.client;

import com.acv.cloud.client.handler.TspClientAioHandler;
import com.acv.cloud.client.listener.TspClientAioListener;
import com.acv.cloud.common.Const;

import com.acv.cloud.common.Type;
import com.acv.cloud.common.packets.LoginReqBody;
import com.acv.cloud.common.packets.TspPacket;
import org.apache.commons.lang3.StringUtils;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;
import org.tio.utils.json.Json;

/**
 * @Author: leo
 * @Date: 2019/4/17 10:15
 */
public class TspClientStarter {
    static String serverIp = "127.0.0.1";
    static int serverPort = Const.PORT;
    static Node serverNode = new Node(serverIp,serverPort);

    //用来自动连接的，不想自动连接请设为null
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    private static ClientAioHandler tioClientHandler = new TspClientAioHandler();
    private static ClientAioListener tioClientAioListener = new TspClientAioListener();
    private static ClientGroupContext clientGroupContext = new ClientGroupContext(tioClientHandler,tioClientAioListener,reconnConf);

    private static TioClient tioClient = null;

    static ClientChannelContext clientChannelContext ;

    public static void command() throws Exception {
        @SuppressWarnings("resource")
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("使用指南:\r\n");
        sb.append(i++ + "、需要帮助，输入 '?'.\r\n");
        sb.append(i++ + "、登录，输入 'login vin'.\r\n");
        //sb.append(i++ + "、进入群组，输入 'join group1'.\r\n");
        //sb.append(i++ + "、群聊，输入 'groupMsg group1 text'.\r\n");
        //sb.append(i++ + "、点对点聊天，输入 'p2pMsg loginname text'.\r\n");

        sb.append(i++ + "、退出程序，输入 'exit'.\r\n");

        System.out.println(sb);

        String line = sc.nextLine(); // 这个就是用户输入的数据
        while (true) {
            if ("exit".equalsIgnoreCase(line)) {
                System.out.println("Thanks for using! bye bye.");
                break;
            } else if ("?".equals(line)) {
                System.out.print(sb);
            }

            processCommand(line);

            line = sc.nextLine(); // 这个就是用户输入的数据
        }

        tioClient.stop();
        System.exit(0);
    }

    public static void processCommand(String line) throws Exception {
        if (StringUtils.isBlank(line)) {
            return;
        }

        String[] args = StringUtils.split(line, " ");
        if(args.length <= 0 ){
            System.out.println("命令行格式不正确,输入'?'获取帮助");
        }
        String command = args[0];

        if ("login".equalsIgnoreCase(command)) {
            String vin = args[1];
            //String password = args[2];

            LoginReqBody loginReqBody = new LoginReqBody();
            loginReqBody.setVin(vin);
            //loginReqBody.setPassword(password);

            TspPacket reqPacket = new TspPacket();
            reqPacket.setType(Type.LOGIN_REQ);
            reqPacket.setBody(Json.toJson(loginReqBody).getBytes(TspPacket.CHARSET));

            Tio.send(clientChannelContext, reqPacket);

        }else if(!"?".equals(command)){
            System.out.println("不支持该命令");
        }

    }

    public static void main(String[] args) throws Exception {
        tioClient = new TioClient(clientGroupContext);
        clientChannelContext = tioClient.connect(serverNode);
        command();
    }
}
