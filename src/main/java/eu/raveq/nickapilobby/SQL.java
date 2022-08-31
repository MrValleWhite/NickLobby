package eu.raveq.nickapilobby;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL {

    public static String host = "135.125.194.35";
    public static int port = 3306;
    public static String database = "RaveQMinecraft";
    public static String username = "AltV";
    public static String password = "RaveQ$MySQL$123";
    public static Connection con;

    public static void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?autoReconnect=true", username, password);
                Bukkit.getConsoleSender().sendMessage("§eMySQL §8- §7Verbindung §aerfolgreich §7hergestellt");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§eMySQL §8- §7Konnte §ckeine §7Verbindung aufbauen");
                e.printStackTrace();
            }
        }

    }

    public static void disconnect() {
        if(isConnected()) {
            try {
                con.close();
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§eMySQL §8- §7Die Verbindung wurde §cgeschlossen");
                e.printStackTrace();
            }
        }

    }

    public static boolean isConnected() {
        return (con == null ? false : true);

    }

    public static Connection getConnection() {
        return con;
    }

    public static void update(String qry)
    {
        if (isConnected()) {
            try
            {
                con.createStatement().executeUpdate(qry);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


}
