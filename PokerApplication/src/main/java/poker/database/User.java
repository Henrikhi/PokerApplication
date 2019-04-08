package poker.database;

public class User {

    Integer id;
    String username;
    String password;
    Double winnings;
    Double coinsInserted;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUserName() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

}
