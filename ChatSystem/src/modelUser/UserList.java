package modelUser;

import javax.swing.DefaultListModel;

public class UserList extends DefaultListModel {

    public UserList() {
        super();
    }

    public UserList getUserList() {
        return this;
    }

    public User getUser(String ip) {
        User userResult = null;
        for (int index = 0; index < this.size(); index++) {
            Object user = this.get(index);
            if (((User) user).getIp().equals(ip)) {
                userResult = ((User) user);
            }
        }
        return userResult;
    }

    public void addToUserList(String username, String ip) {
        User newUser = new User(username, ip);
        this.addElement(newUser);
    }

    public void removeToUserList(String ip) {
        removeElement(getUser(ip));
    }

    public String getUsername(String ip) {
        String username = "";
        for (int index = 0; index < this.size(); index++) {
            Object user = this.get(index);
            if (((User) user).getIp().equals(ip)) {
                username = ((User) user).getNickname();
            }
        }
        return username;
    }

    public String getUsername(Object userObj) {
        return ((User) userObj).getNickname();
    }

    public String getIp(Object userObj) {
        return ((User) userObj).getIp();
    }

}
