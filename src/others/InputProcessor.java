package others;
import java.util.regex.Pattern;
import java.util.Scanner;
public class InputProcessor {
    private Scanner sc = new Scanner(System.in);
    private Manager manager = new Manager();
    String command;
    boolean quit = false;
    void run()
    {
        while(!quit)
        {
            command = sc.nextLine();
            quit = true;
        }
    }

}
