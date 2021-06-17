package others;

public class Coin {
    public final static int INITIAL_COINS  = 1000;
    private static int coins ;

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        Coin.coins = coins;
    }

    public boolean buy(int coin) {
        if(coins>= coin)
        {
            coins += coin;
            return true;
        }
        else
            return false;
    }

    public void addCoins(int coin) {
        coins += coin;
    }
}
