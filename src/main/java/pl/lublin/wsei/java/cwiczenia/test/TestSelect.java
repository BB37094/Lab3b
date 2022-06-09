package pl.lublin.wsei.java.cwiczenia.test;

import pl.lublin.wsei.java.cwiczenia.MyDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) throws SQLException {
        MyDB db = new MyDB("localhost", 3306, "mydb");
        db.setUser("root");
        db.setPassword("1qazXSW@");
        Connection conn = db.getConnection();
        if (conn != null)
        {
            Statement polecenie = conn.createStatement();
            ResultSet res = polecenie.executeQuery("SELECT * FROM coffee_houses");
            System.out.printf("%10s%20s%7s%7s%7s\n", "STORE_ID", "CITY", "COFFEE", "MERCH", "TOTAL");
            while(res.next())
            {
                System.out.printf("%10s%20s%7s%7s%7s\n", res.getInt(1), res.getString("CITY"), res.getInt(3), res.getInt(4), res.getInt(5));
            }
        }
        db.closeConnection();
        System.out.println("Połączenie z bazą zakończone");
    }
}
