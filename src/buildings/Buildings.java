package buildings;

import others.FarmPosition;

public abstract class Buildings {
    private int  level,loadingTime , cost;
    private FarmPosition position;
    private int timeLeft;
    public Buildings( int loadingTime, int cost) {
        this.level = 1;
        this.loadingTime = loadingTime;
        this.cost = cost;
        this.position = position;
    }
}
