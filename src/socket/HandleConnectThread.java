package socket;

import dao.SensorData;
import dao.SensorDataAcessLayer;

import java.beans.Encoder;
import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by mevur on 5/6/2017.
 */


public class HandleConnectThread extends Thread {
    private Socket socket;
    private SensorDataAcessLayer help;

    public HandleConnectThread(Socket socket) {
        this.socket = socket;
        help = new SensorDataAcessLayer();
        System.out.println(this.socket.toString());
    }

    public String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            char[] buff = new char[100];
            StringBuilder sb = new StringBuilder();
            while (true) {
                reader.read(buff);
                System.out.println(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
