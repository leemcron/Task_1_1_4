package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/task113?useUnicode=true&serverTimezone=UTC&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Connection Error");
        }
        return connection;
    }

}