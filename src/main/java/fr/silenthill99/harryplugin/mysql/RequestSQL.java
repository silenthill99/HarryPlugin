package fr.silenthill99.harryplugin.mysql;

import fr.silenthill99.harryplugin.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RequestSQL {

    public static boolean isBlacklist(UUID uuid) {
        try {
            Connection conn = Main.getManager().getGradeConnection().getConnection();
            PreparedStatement sts = conn.prepareStatement("SELECT * FROM staff_blacklist WHERE uuid = ?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getReason(UUID uuid) {
        try {
            Connection conn = Main.getManager().getGradeConnection().getConnection();
            PreparedStatement sts = conn.prepareStatement("SELECT * FROM staff_blacklist WHERE uuid = ?");
            sts.setString(1, uuid.toString());
            ResultSet rs = sts.executeQuery();
            rs.next();
            return rs.getString("reason");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
