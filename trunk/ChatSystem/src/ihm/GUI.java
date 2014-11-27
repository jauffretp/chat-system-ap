package ihm;

import controller.Controller;
import java.io.File;
import model.Message;
import model.UserList;

public class GUI implements CtrlToGUI {

    private Controller controller;
    private UserList listUsers;
    private ConnectWindow connectWindow;
    private WindowChatSystem windowChat;

    public GUI(Controller controller) {
        this.controller = controller;
        this.listUsers = UserList.getInstance();
        windowChat = new WindowChatSystem(this) ;
        windowChat.setjListUserList(listUsers.getJlist());
        this.connectWindow = new ConnectWindow(this) ;
        this.connectWindow.setVisible(true);
    }

    // receiving side 
    public void addUser(String username, String ip) {
        listUsers.addUserToList(username, ip); 
        if (windowChat == null) System.out.println("null");
        windowChat.majList(listUsers.toString());
        System.out.println("ho");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void informMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void informFileReceived(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // sending side 
    public void ConnectButtonPushed(String nickname) {
        controller.performConnect(nickname);
        connectWindow.dispose() ;          
        windowChat.setVisible(true);
    }
}
