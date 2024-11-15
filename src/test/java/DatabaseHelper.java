import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    public static List<List<String>> getDataList(String sql) {

        DBConnectionOpen();

        List<List<String>> dataList = new ArrayList<>();

        try {
            ResultSet resultTable = statement.executeQuery(sql);
            ResultSetMetaData resultTableMetaData = resultTable.getMetaData();

            while (resultTable.next()) {
                List<String> rowList = new ArrayList<>();
                for (int i = 1; i <= resultTableMetaData.getColumnCount(); i++) {
                    rowList.add(resultTable.getString(i));
                }
                dataList.add(rowList);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        DBConnectionClose();
        return dataList;



    }


    public static List<List<String>> getDataListWithHeaders(String sql) {

        DBConnectionOpen();
        List<List<String>> dataList = new ArrayList<>();
        try {
            ResultSet resultTable = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultTable.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column headers as the first row
            List<String> headers = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                headers.add(metaData.getColumnLabel(i));
            }
            dataList.add(headers);  // Adding headers as the first row

            // Add rows of data
            while (resultTable.next()) {
                List<String> rowList = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowList.add(resultTable.getString(i));
                }
                dataList.add(rowList);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        DBConnectionClose();
        return dataList;


    }



}