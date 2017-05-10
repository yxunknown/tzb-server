package socket;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by mevur on 5/6/2017.
 */
public class SocketServer {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 6666);
            if (socket.isConnected()) {
                System.out.println(socket.toString());
            }
            InputStream is = socket.getInputStream();
            BufferedReader response = new BufferedReader(new InputStreamReader(is));
            OutputStream os = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                writer.println(s);
                //System.out.println("server:" + response.readLine());
                if (s.equals("end")) {
                    break;
                }
            }
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
