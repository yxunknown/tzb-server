package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mevur on 5/6/2017.
 */
public class Server {
    private int count;
    private ServerSocket server;

    public Server() throws IOException{
        count = 0;
        server = new ServerSocket(6666);
    }

    public void listen() {
        Socket socket = null;
        try {
            System.out.println("server is running........");
            while (true) {
                socket = server.accept();
               // System.out.println("新的连接来自:" + socket.toString());
                new HandleConnectThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
