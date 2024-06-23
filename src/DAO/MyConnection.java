package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private Connection conn = null;

    static final String url = "jdbc:mysql://localhost:3306/Kho";
    static final String nameUser = "root";
    static final String pass = "quannguyen27";

    public Connection openConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, nameUser, pass);
            System.out.println("Connected to database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnectDB() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        MyConnection cn = new MyConnection();
        if (cn.openConnectDB() != null) {
            cn.closeConnectDB();
        }
    }
}
