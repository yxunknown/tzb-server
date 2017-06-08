package socket;

import sun.security.provider.PolicySpiFile;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mevur on 5/26/2017.
 */
public class Command {
    public static final int COMMAND_RESTART = 1;
    public static final int COMMAND_SLEEP = 2;
    public static final int COMMAND_RESTORE = 3;
    PrintWriter writer;
    private Socket socket;
    public Command() {
    }
    public String sendCommand(int node, int command) throws IOException{
        String commandStr = "st:"
                       + node
                       + ""
                       + command
                       + ""
                       + (node + command)
                       + "end";
        return  commandStr;
    }
}
