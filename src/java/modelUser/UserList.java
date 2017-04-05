package modelUser;

import java.util.ArrayList;

public class UserList {

    public String dbError = "";
    private ArrayList<User> userList = new ArrayList();

    public UserList() {
    }

    public void addOption(User option) {
        this.userList.add(option);
    }
}