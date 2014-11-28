package ihm;

import controller.Controller;
import java.io.File;
import model.Message;
import model.User;

public class GUI implements CtrlToGUI {

    private Controller controller;
    private ConnectWindow connectWindow;
    private WindowChatSystem windowChat;

    public GUI(Controller controller) {
        this.controller = controller;
        windowChat = new WindowChatSystem(this, controller) ;
        windowChat.setLog("Welcome to the ChatSystem !");
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
    public void connectButtonPushed(String nickname) {
        controller.performConnect(nickname);
        connectWindow.dispose() ;          
        windowChat.setVisible(true);
    }
    
    public void sendButtonPushed(Object[] users, String txtMessage ) {        
        controller.performMessage(txtMessage, users);
    }
    
}
