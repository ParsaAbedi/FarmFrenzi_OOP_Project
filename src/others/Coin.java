package others;

public class Coin {
    static private int coins ;

    public static int getCoins() {
        return coins;
    }

    public static boolean setCoins(int coins) {
        if(Coin.coins>= coins)
        {
            Coin.coins += coins;
            return true;
        }
        else
            return false;
    }
}
