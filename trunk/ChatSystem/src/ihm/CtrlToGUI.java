package ihm;

import java.io.File;
import model.Message;
import model.User;

public interface CtrlToGUI {    
   
    public void addUser(String username, String ip) ; 
    public void deleteUser(String username) ; 
    public void informMessage(Message message) ; 
    public void informFileReceived(File file) ; 
    
}
