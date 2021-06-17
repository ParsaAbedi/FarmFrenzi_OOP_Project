package others;

public class Task {
    String definition ;
    boolean isDone = false;
    int value ;
    String type;
    public Task(String definition , int value) {
        this.definition = definition;
        this.value = value;
    }
    public boolean isCompleted()
    {
        return true;
    }
    public String showTask()
    {
        return definition;
    }

    @Override
    public String toString() {
        return "Task definitio = " +
                definition +
                "\nTask isDone = " +
                isDone +
                "\nTask value = " +
                value +
                "\nTask type = " +
                type;
    }
}
