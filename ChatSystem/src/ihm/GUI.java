package ihm;

import controller.Controller;
import modelUser.UserList;

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
        System.out.println("GUI : setTextLog ( " + text + " )");
        windowChat.setLog(text);
    }

    @Override
    public void setAckLog(String text) {
        System.out.println("GUI : setAckLog( " + text + " )");
        windowChat.setAckLog(text);
    }

    ////////////////
    // sending side 
    public void connectButtonPushed(String nickname) {
        System.out.println("GUI : connectButtonPushed");
        controller.performConnect(nickname.replace('\n', ' '));
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

    UserList getListModel() {
        return controller.getListModel();
    }

    void sendFileButtonPushed(String filePath, Object[] users) {
        System.out.println("GUI : Send File action request");
        controller.performFile(filePath,users);
    }

}
