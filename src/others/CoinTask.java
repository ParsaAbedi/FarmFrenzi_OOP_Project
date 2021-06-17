package others;

public class CoinTask extends Task{

    public CoinTask(String definition , int value) {
        super(definition,value);
        this.type = "Coin";
    }
    @Override
    public boolean isCompleted() {
        if(value <Coin.getCoins())
            return false;
        else
        {
            isDone = true;
            return true;
        }
    }
    @Override
    public String showTask() {
        return super.showTask();
    }
}
