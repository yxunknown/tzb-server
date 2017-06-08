package computation;

import dao.AlarmAccessLayer;
import model.Alarm;

/**
 * Created by mevur on 6/7/2017.
 */
public class ComputeThread extends Thread {
    private IComputorReciver reciver;
    private Computor computor;
    public ComputeThread(Computor computor, IComputorReciver reciver) {
        this.computor = computor;
        this.reciver = reciver;
    }
    @Override
    public void run() {
        try {
            sleep(1000);
            Alarm alarm = computor.compute();
            AlarmAccessLayer a = new AlarmAccessLayer();
            long re = a.insert(alarm);
            if (re > 0) {
                reciver.handler(true, alarm);
            } else {
                reciver.handler(false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
