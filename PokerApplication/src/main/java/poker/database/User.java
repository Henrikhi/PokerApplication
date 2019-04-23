package poker.database;

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

    public User() {
        this.userName = "";
        this.password = "";
        this.winnings = 0;
        this.moneyInserted = 0;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public double getWinnings() {
        return winnings;
    }

    public double getMoneyInserted() {
        return moneyInserted;
    }

    public void setWinnings(double winnings) {
        this.winnings = winnings;
    }

    public void setMoneyInserted(double moneyInserted) {
        this.moneyInserted = moneyInserted;
    }
    

}
