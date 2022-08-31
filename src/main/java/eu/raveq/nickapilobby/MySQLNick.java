package eu.raveq.nickapilobby;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLNick {

    Player p;

    public MySQLNick(Player p) {
        this.p = p;
    }

    public static boolean isUserExists(String uuid) {


        try {
            PreparedStatement ps = (PreparedStatement) SQL.con.prepareStatement("SELECT nickname FROM nickname WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid)
    {
        if (!isUserExists(uuid)) {
            SQL.update("INSERT INTO nickname (UUID, nickname) VALUES ('" + uuid + "', 'none');");
        }
    }

    public static void nick(String uuid, String nickname) {

        try {
            PreparedStatement ps = (PreparedStatement) SQL.con.prepareStatement("UPDATE nickname SET nickname = ? WHERE UUID = ?");
            ps.setString(1, nickname);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public static void unnick(String uuid) {

        try {
            PreparedStatement ps = (PreparedStatement) SQL.con.prepareStatement("UPDATE nickname SET nickname = ? WHERE UUID = ?");
            ps.setString(1, "none");
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();


        }
    }

    public static String getNickname(String uuid) {

        try {
            PreparedStatement ps = (PreparedStatement) SQL.con.prepareStatement("SELECT nickname FROM nickname WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return "none";

    }

}
