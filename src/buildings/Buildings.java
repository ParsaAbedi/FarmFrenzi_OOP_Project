package buildings;

import others.FarmPosition;

public class Buildings {
    private int  level,loadingTime , cost;
    private FarmPosition position;
    private int timeLeft;
    public Buildings( int loadingTime, int cost) {
        this.level = 1;
        this.loadingTime = loadingTime;
        this.cost = cost;
        //TODO this.position = position;
    }
}
