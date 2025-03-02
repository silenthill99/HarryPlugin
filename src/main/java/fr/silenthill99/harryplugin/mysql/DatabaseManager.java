package fr.silenthill99.harryplugin.mysql;

import java.sql.SQLException;

public class DatabaseManager {

    private final DbConnection gradeConnection;

    public DatabaseManager() {
        this.gradeConnection = new DbConnection(
                new DbCredentials("minecraft118.omgserv.com", "minecraft_235640", "Mylene.10",
                        "minecraft_235640", 3306));
    }

    public DbConnection getGradeConnection() {
        return gradeConnection;
    }

    public void close() {
        try {
            this.gradeConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
