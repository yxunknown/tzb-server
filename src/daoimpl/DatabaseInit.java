package daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mevur on 5/8/2017.
 */
public class DatabaseInit {
    //singleton pattern
    private static DatabaseInit instance;
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/tzb";
    private static final String USERNAME = "tzb_manage";
    private static final String PASSWORD = "123456";

    //a private constructor
    private DatabaseInit() {
        init();
    }
    public static synchronized DatabaseInit getInstance() {
        if (instance == null) {
            instance = new DatabaseInit();
        }
        return instance;
    }
    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }
}
