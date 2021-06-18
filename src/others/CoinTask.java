package others;

public class CoinTask extends Task{

    public CoinTask(String definition , int value) {
        super(definition,value);
        this.type = "Purse";
    }
    public boolean isCompleted(Purse purse) {
        if(value < purse.getCoins())
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
