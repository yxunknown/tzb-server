package socket;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by mevur on 5/6/2017.
 */
public class SocketClient {
    private String host;
    private int port;
    private Socket socket;
    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendMsg(String msg) {
        if (socket == null) {
            return false;
        } else {
            try {
                OutputStream os = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(os, true);
                char[] chars = msg.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    writer.println(chars[i]);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    public void close() {
        try {
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SocketClient s = new SocketClient("127.0.0.1", 7777);
        try {
            s.sendMsg(new Command().sendCommand(1, Command.COMMAND_RESTORE));
            s.sendMsg(new Command().sendCommand(2, Command.COMMAND_RESTORE));
            s.sendMsg(new Command().sendCommand(3, Command.COMMAND_RESTORE));
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
