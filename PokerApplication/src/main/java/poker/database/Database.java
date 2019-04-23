package poker.database;

import java.sql.*;

public class Database {

    public Database() throws ClassNotFoundException {
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String adress = "jdbc:sqlite:database.db";
        return DriverManager.getConnection(adress);
    }

    public boolean init() {
        try (Connection conn = getConnection()) {
            Statement statement = conn.createStatement();
            String createTableStatement = "CREATE TABLE IF NOT EXISTS User (username varchar (10) PRIMARY KEY, password varchar(10), credits INTEGER, inserts INTEGER);";
            statement.executeUpdate(createTableStatement);

        } catch (Throwable t) {
            System.out.println(t.getMessage());
            return false;
        }
        return true;
    }

    public boolean userExists(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM User WHERE User.username = ?");
            statement.setString(1, user.getUserName());

            ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (Throwable t) {
            System.out.println(t.getMessage());
            return false;
        }
    }

    public void createUser(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement createNewUserStatement = conn.prepareStatement("INSERT INTO User (username, password, credits, inserts) VALUES (?,?,?,?)");
            createNewUserStatement.setString(1, user.getUserName());
            createNewUserStatement.setString(2, user.getPassword());
            createNewUserStatement.setInt(3, 0);
            createNewUserStatement.setInt(4, 0);
            createNewUserStatement.executeUpdate();

        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    public User logIn(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement getUserDataStatement = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
            getUserDataStatement.setString(1, user.getUserName());
            ResultSet rs = getUserDataStatement.executeQuery();
            String actualPassword = rs.getString("password");
            double actualWinnings = (double) rs.getInt("credits");
            double actualInserts = (double) rs.getInt("inserts");

            if (actualPassword == null) {
                return null;
            }

            if (actualPassword.equals(user.getPassword())) {
                user.setWinnings(actualWinnings);
                user.setMoneyInserted(actualInserts);
                return user;
            } else {
                return null;
            }

        } catch (Throwable t) {
            System.out.println(t.getMessage());
            return null;
        }
    }

    public void updateUser(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement updateUserStatement = conn.prepareStatement("UPDATE User set credits = ?, inserts = ? WHERE username = ?");
            updateUserStatement.setInt(1, (int) user.getWinnings());
            updateUserStatement.setInt(2, (int) user.getMoneyInserted());
            updateUserStatement.setString(3, user.getUserName());
            updateUserStatement.executeUpdate();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

}
