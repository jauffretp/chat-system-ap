package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JList;

public class UserList {

    private HashMap<String, String> user_list;
    private static UserList instance;
    private JList jlist ; 
    
    private UserList() {
        user_list = new HashMap<String, String>();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public JList getJlist() {
        return jlist;
    }

   
    
    public void addUserToList(String username, String ip) {
        user_list.put(username, ip);
        System.out.println(this.toString(username)+ " added to the UsersList"); 
    }

    @Override
    public String toString() {
        String retour = new String("");  
            
        for (Map.Entry<String, String> entry : user_list.entrySet()) {
            String user = entry.getKey();
            String ip = user_list.get(user);
            retour = retour + user + "@" + ip + '\n';
        }
        return retour;
    }
    
    public String toString(String username) {
        return username + "@" + user_list.get(username) ; 
    }
    
}
