package others;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {
    private String filePath = "logger.txt";
    private PrintWriter pw = null;
    private User user;
    Date date = new Date();

    public Logger(User user) {
        this.user = user;
    }
    public boolean writeError(String message)
    {

        try {
            Files.write(Paths.get(filePath), ("[Info],"+date.toString()+","+message+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public boolean writeInfo(String message)
    {

        try {
            Files.write(Paths.get(filePath), ("[Info],"+date.toString()+","+message+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
