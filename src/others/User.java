package others;
public class User {

    String username , password ;
    int maxLevel;
    private Purse purse;
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxLevel=" + maxLevel +
                '}';
    }

    public User(String username, String password, int maxLevel , int coinsNum) {
        this.username = username;
        this.password = password;
        this.maxLevel = maxLevel;
        purse.setCoins(coinsNum);
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.maxLevel = 1;
        maxLevel = 1;
        purse.setCoins(purse.INITIAL_COINS);
    }
}
