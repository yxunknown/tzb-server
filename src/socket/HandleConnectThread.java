package socket;

import dao.SensorDataAcessLayer;
import model.SensorData;

import java.io.*;
import java.net.Socket;
import java.sql.Timestamp;

/**
 * Created by mevur on 5/6/2017.
 */


public class HandleConnectThread extends Thread implements ISocketHandler {
    private Socket socket;
    private SensorDataAcessLayer help;
    private Command command;
    private FrameReceiver receiver;

    public HandleConnectThread(Socket socket) {
        this.socket = socket;
        help = new SensorDataAcessLayer();
        System.out.println(this.socket.toString());
        command = new Command();
        try {
            String cmd1 = command.sendCommand(1, Command.COMMAND_RESTORE);
            String cmd2 = command.sendCommand(2, Command.COMMAND_RESTORE);
            String cmd3 = command.sendCommand(3, Command.COMMAND_RESTORE);
            SocketClient client = new SocketClient("127.0.0.1", 9999);
            client.sendMsg(cmd1);
            client.sendMsg(cmd2);
            client.sendMsg(cmd3);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String line = "";
            while (reader.read(buff) > 0) {
                SensorData data = FrameParse.parseToSensorData(buff.toString());
                if (data != null) {
                    receiver = new FrameReceiver(socket, this, data.getNodeNO());
                    receiver.handleData(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onReceiveError(Socket socket, int node, int errorCode) {
        if (errorCode == FrameReceiver.ERROR_CODE_DATABASE_ERROR) {
            System.out.println("data base error");
        } else if (errorCode == FrameReceiver.ERROR_CODE_FRAME_ERROR) {
            //
            try {
                //发送下行控制指令
                String cmd = command.sendCommand(node, Command.COMMAND_RESTART);
                SocketClient client = new SocketClient("host", 7777);
                client.sendMsg(cmd);
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (errorCode == FrameReceiver.ERROR_CODE_ALARM_ERROR) {
            System.out.println("报警数据异常");
        }
    }
}
