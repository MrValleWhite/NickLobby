package eu.raveq.nickapilobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class NickAPILobby extends JavaPlugin {

    public static String pr = "§8| §bNick §8» ";
    public static String noperm = pr + "§cDazu hast Du keine Rechte!";

    public static NickAPILobby instance;

    public static NickAPILobby getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        System.out.println("[NickAPI] Das Plugin wurde aktiviert");
        instance = this;

        SQL.connect();

        try {
            PreparedStatement ps = (PreparedStatement) SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS nickname (UUID VARCHAR(100), nickname VARCHAR(100))");
            ps.executeUpdate();
            Bukkit.getConsoleSender().sendMessage("Table created");
        } catch (SQLException e) {

        }


    }

    @Override
    public void onDisable() {
        System.out.println("[NickAPI] Das Plugin wurde deaktiviert");
    }
}
