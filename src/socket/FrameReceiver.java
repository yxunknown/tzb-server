package socket;

import computation.ComputeThread;
import computation.Computor;
import computation.IComputorReciver;
import dao.AlarmAccessLayer;
import model.Alarm;
import model.SensorData;
import dao.SensorDataAcessLayer;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mevur on 5/26/2017.
 */
public class FrameReceiver implements IComputorReciver{

    public static final int ERROR_CODE_PARSE_ERROR = 100;
    public static final int ERROR_CODE_DATABASE_ERROR = 200;
    public static final int ERROR_CODE_FRAME_ERROR = 300;
    public static final int ERROR_CODE_ALARM_ERROR = 400;
    private ISocketHandler handler;
    private Socket socket;
    private int errorCounter;
    private int node;
    private SensorDataAcessLayer acessLayer;

    public FrameReceiver(Socket socket, ISocketHandler handler, int node) {
        this.handler = handler;
        this.socket = socket;
        errorCounter = 0;
        this.node = node;
        acessLayer = new SensorDataAcessLayer();
    }

    public void handleData(SensorData data) {
        System.out.println(data);
        if (data != null && data.getHead1().equals("st:")
                && data.getEnd1().equals("end")) {
            if (acessLayer.insert(data) < 0) {
                handler.onReceiveError(socket, node, FrameReceiver.ERROR_CODE_DATABASE_ERROR);
            }
            double[] datas = data.getData();
            for (int i = 0; i < datas.length - 1; i++) {
                if (Math.abs(datas[i] - datas[i + 1]) >= 1) {
                    long time = System.currentTimeMillis();
                    Date d = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    String st = format.format(d);
                    Computor computor = new Computor(st);
                    new ComputeThread(computor, this).start();
                    break;
                }
            }
        } else {
            errorCounter++;
            if (errorCounter >= 10) {
                handler.onReceiveError(socket, node, FrameReceiver.ERROR_CODE_FRAME_ERROR);
            }
        }
    }

    @Override
    public void handler(boolean isSuccess, Alarm alarm) {
        if (isSuccess) {
            //通知app\接收报警数据
            System.out.println("产生报警数据" + alarm.toString());
        } else {
            handler.onReceiveError(socket, node, FrameReceiver.ERROR_CODE_ALARM_ERROR);
        }
    }
}
