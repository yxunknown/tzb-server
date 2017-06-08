package computation;

import model.Alarm;

/**
 * Created by mevur on 6/4/2017.
 */
public interface IComputorReciver {
    void handler(boolean isSuccess, Alarm alarm);
}
