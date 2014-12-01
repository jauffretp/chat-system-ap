package ihm;

import javax.swing.ListModel;

import controller.Controller;


public class GUI implements CtrlToGUI {

    private final Controller controller;
    private final ConnectWindow connectWindow;
    private final WindowChatSystem windowChat;

    public GUI(Controller controller) {
        this.controller = controller;
        windowChat = new WindowChatSystem(this);
        windowChat.setLog("Welcome to the ChatSystem !");
        this.connectWindow = new ConnectWindow(this);
        this.connectWindow.setVisible(true);
    }

    // receiving side 
    @Override
    public void setTextLog(String text) {
        System.out.println("GUI : setTextLog ( " + text +" )");
        windowChat.setLog(text);
    }
    
    @Override
    public void setAckLog(String text) {
        System.out.println("GUI : setAckLog( " + text +" )");
        windowChat.setAckLog(text);
    }
    
    ////////////////

    // sending side 
    public void connectButtonPushed(String nickname) {
        System.out.println("GUI : connectButtonPushed");
        controller.performConnect(nickname);
        connectWindow.dispose();
        windowChat.setVisible(true);
    }

    public void sendButtonPushed(Object[] users, String txtMessage) {
        System.out.println("GUI : sendButtonPushed");
        controller.performMessage(txtMessage, users);
    }

    void actionDisconnect() {
        System.out.println("GUI : actionDisconnect");
        controller.performDisconnect();
    }
    ///////////////

    ListModel getListModel() {
        return controller.getListModel() ; 
    }

    
}
