package poker.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private final Database database;

    public UserDao(Database db) {
        this.database = db;
    }

    public boolean addUser(User newUser) throws SQLException {
        boolean succes;

        try (Connection con = database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement("INSERT INTO User (name, pswd) VALUES (?,?)");
            stmnt.setString(1, newUser.getUserName());
            stmnt.setString(2, newUser.getPassword());

            stmnt.execute();

            stmnt.close();
            con.close();

            succes = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            succes = false;
        }

        return succes;
    }
}
