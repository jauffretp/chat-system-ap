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
        windowChat = new WindowChatSystem(this, controller) ;
        this.connectWindow = new ConnectWindow(this) ;
        this.connectWindow.setVisible(true);
    }

    // receiving side 
    
    @Override
    public void setTextLog(String text) {
        System.out.println("GUI : setTextLog");
        windowChat.setLog(text) ;  
    }
    

    // sending side 
    public void ConnectButtonPushed(String nickname) {
        controller.performConnect(nickname);
        connectWindow.dispose() ;          
        windowChat.setVisible(true);
    }
}
