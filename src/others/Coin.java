package others;

public class Coin {
    public final static int INITIAL_COINS  = 1000;
    public static void setCoins(int coins) {
        Coin.coins = coins;
    }

    static private int coins ;

    public static int getCoins() {
        return coins;
    }

    public static boolean buy(int coins) {
        if(Coin.coins>= coins)
        {
            Coin.coins += coins;
            return true;
        }
        else
            return false;
    }

    public static void addCoins(int coins) {
        Coin.coins += coins;
    }
}
