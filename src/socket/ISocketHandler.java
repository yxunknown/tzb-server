package socket;

import java.net.Socket;

/**
 * Created by mevur on 5/26/2017.
 */
public interface ISocketHandler {


    void onReceiveError(Socket socket, int node, int errorCode);
}
