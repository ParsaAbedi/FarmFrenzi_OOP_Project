package others;

public class Purse {
    public final int INITIAL_COINS  = 1000;
    private  int coins ;

    public  int getCoins() {
        return coins;
    }

    public void setCoins(int value) {
        coins = value;
    }

    public boolean buy(int coin) {
        if(coins>= coin)
        {
            return true;
        }
        else
            return false;
    }

    public void addCoins(int value) {
        coins+=value;

    }
}
