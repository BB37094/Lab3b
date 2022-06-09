package pl.lublin.wsei.java.cwiczenia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDB {

    private String host;
    private String dbName;
    private Number port;
    private String user;
    private String password;

    public MyDB(String serverName, Number portNumber, String database)
    {
        this.host = serverName;
        this.dbName = database;
        this.port = portNumber;
    }

    public void setUser(String user)
    {
        this.user = user;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    private Connection conn =  null;

    private void connect()
    {
        Properties connectionProps = new Properties();
        connectionProps.put("user",user);
        connectionProps.put("password", password);
        connectionProps.put("serverTimezone", "Europe/Warsaw");

        String jdbcString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        try
        {
            conn = DriverManager.getConnection(jdbcString, connectionProps);
        }
        catch(SQLException e)
        {
            System.out.println("Błąd podłączenia do bazy: " + jdbcString);
            System.out.println("Komunikat błędu: " + e.getMessage());
        }
        System.out.println("Podłączono do bazy danych: " + dbName);
    }

    public Connection getConnection()
    {
        if (conn == null)
            connect();
        return conn;
    }

    public void closeConnection()
    {
        if (conn != null)
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                System.out.println("Błąd przy zamykaniu połączenia z bazą: " + e.getMessage());
            }
        conn = null;
    }
}
