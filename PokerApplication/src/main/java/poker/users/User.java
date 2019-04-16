package poker.users;

public class User {

    private String userName;
    private String password;
    private double winnings;
    private double moneyInserted;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.winnings = 0;
        this.moneyInserted = 0;
    }

}
