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
    private static String filePath = "logger.txt";
    private static String mapPath = "map.txt";
    private PrintWriter pw = null;
    private User user;
    static Date date = new Date();

    public Logger(User user) {
        this.user = user;
    }
    public static boolean writeError(String message)
    {

        try {
            Files.write(Paths.get(filePath), ("[Error],"+date.toString()+","+message+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public static boolean writeInfo(String message)
    {

        try {
            Files.write(Paths.get(filePath), ("[Info],"+date.toString()+","+message+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean drawMap(String message)
    {

        try {
            Files.write(Paths.get(mapPath), (message).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean start()
    {
        String message = "-" ;
        for(int i=0 ; i<19 ; i++)
            message+="-";
        try {
            Files.write(Paths.get(filePath), (message+"\n *PROGRAM STARTS* \n"+message+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
