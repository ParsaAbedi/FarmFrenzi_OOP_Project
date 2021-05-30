package others;

public class Coin {
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
