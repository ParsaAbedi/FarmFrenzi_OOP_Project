package others;

import java.util.ArrayList;
import java.util.List;

public class Authentication {
    List<User> users = new ArrayList<>();
    public void addUser(User user)
    {
        users.add(user);
    }
    public User findUser(String username)
    {
        for(User i : users)
        {
            if(i.username.equals(username))
                return i;
        }
        return null;
    }

    public boolean checkUsername(String username) {
        for(User i : users)
        {
            if(i.username.equals(username))
                return true;
        }
        return false;

    }

    public boolean checkPassword(String username, String password) {
        for(User i : users)
        {
            if(i.username.equals(username))
            {
                if(i.password.equals(password))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
}
