package poker.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public Database() throws ClassNotFoundException {
    }

    public Connection getConnection() throws SQLException {
        File file = new File("src/main/resources/db", "poker.db");
        String path = ("jdbc:sqlite:" + file.getAbsolutePath());
        return DriverManager.getConnection(path);
    }

}
