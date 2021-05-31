package others;

import java.util.Random;

public class FarmPosition {
    public static final int COLUMNS = 6;
    public static final int ROWS =6 ;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x, y;
    private Random random = new Random();

    public int getX() {
        return x;
    }

    public void setRandomX() {
         this.x = random.nextInt(6) ;
    }

    public int getY() {
        return y;
    }

    public void setRandomY() {

        this.y = random.nextInt(6);
    }
}
