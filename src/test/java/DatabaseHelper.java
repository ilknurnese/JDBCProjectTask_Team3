import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseHelper {
    public static Connection connection;
    public static Statement statement;

    @BeforeClass
    public static void DBConnectionOpen() {
        String hostUrl = "jdbc:mysql://demo.mersys.io:33906/employees";
        String username = "student";
        String password = "DEkzTd3#pzPm";

        try {
            connection = DriverManager.getConnection(hostUrl, username, password);
            statement = connection.createStatement();
        } catch (SQLException exception) {
            System.out.println("Error during connection: " + exception.getMessage());
        }
    }

    @AfterClass
    public static void DBConnectionClose() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException exception) {
            System.out.println("Error during disconnection: " + exception.getMessage());
        }
    }


}
