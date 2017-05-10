package socket;

import daoimpl.DatabaseInit;

import java.io.IOException;
import java.sql.Connection;

/**
 * Created by mevur on 5/6/2017.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.listen();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
