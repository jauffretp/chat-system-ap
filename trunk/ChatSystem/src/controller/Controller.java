package controller;

import ihm.CtrlToGUI;
import network.CtrlToNI;
import modelUser.UserList;

public class Controller {

    private CtrlToNI ni;
    private CtrlToGUI gui;
    private String nickname;
    private final UserList userList;
    private int messageNumberCpt;

    public Controller() {
        userList = new UserList();
        messageNumberCpt = 0;
    }

    public void setNi(CtrlToNI ni) {
        this.ni = ni;
    }

    public void setGui(CtrlToGUI gui) {
        this.gui = gui;
    }

    public UserList getListModel() {
        return userList.getUserList();
    }

    // sending side /////////////////////////    
    public void performConnect(String nickname) {
        this.nickname = nickname;
        System.out.println("Controller : Send Hello (broadcast) with nickname = " + nickname);
        ni.sendHello(nickname);
    }

    public void performDisconnect() {
        System.out.println("Controller : Send Goodbye (broadcast) with nickname = " + nickname);
        ni.sendGoodbye();
    }

    public void performMessage(String txtMessage, Object[] users) {
        System.out.println("Controller : Send Message to users selected");
        for (Object userObj : users) {
            String remoteUser = userList.getUsername(userObj);
            String remoteIp = userList.getIp(userObj);
            gui.setTextLog("You (to " + remoteUser + ")" + " : " + txtMessage);
            ni.sendMessage(nickname, remoteIp, txtMessage, messageNumberCpt);
            messageNumberCpt++;
        }
    }

    public void performFile(String filePath, Object[] users) {
        System.out.println("Controller : Send File to users selected");
        for (Object userObj : users) {
            String remoteUser = userList.getUsername(userObj);
            String remoteIp = userList.getIp(userObj);
            gui.setTextLog("You are sending file : " + filePath + " to " + remoteUser);
            ni.sendFileTo(filePath,remoteIp);
        }
    }

    // receiving side /////////////////
    public void processHelloReceived(String username, String ip) {
        System.out.println("Controller : HelloReceived from " + username + "@" + ip);

        // We want to add ourselves
        //if(!username.equals(nickname)) {       
        System.out.println("Controller : We add " + username + "@" + ip + " to the user list");
        userList.addToUserList(username, ip);
        gui.setTextLog(username + "@" + ip + " is connected to the ChatSystem");
        //}
        ni.sendHelloAck(nickname, ip);
    }

    public void processHelloAckReceived(String username, String ip) {
        System.out.println("Controller : HelloAckReceived from " + username + "@" + ip);

        if (!username.equals(nickname)) {
            System.out.println("Controller : We add " + username + "@" + ip + " to the user list");
            userList.addToUserList(username, ip);
            gui.setTextLog(username + "@" + ip + " is connected to the ChatSystem");
        }
    }

    public void processGoodbyeReceived(String ip) {
        System.out.println("Controller : GoodBye received from" + userList.getUsername(ip) + "@" + ip);
        gui.setTextLog(userList.getUsername(ip) + " is now disconnected from the Chatsystem !");
        userList.removeToUserList(ip);
    }

    public void processMessageReceived(String ip, String dataMessage) {
        String username = userList.getUsername(ip);
        System.out.println("Controller : Message received from " + username);
        gui.setTextLog(username + " : " + dataMessage);
    }

    public void processMessageAckReceived(int messageNumber) {
        System.out.println("Controller : Ack n° " + messageNumber + " received");
        gui.setAckLog("[ACK] Message " + messageNumber);
    }

    public void fileReveivedNotification(String result) {
        System.out.println("Controller : Inform GUI file received");
        gui.setTextLog("[FileReceived] " + result );
    }
    
    
    // feedback 
    public void processFileTranfertResult(String result) {
    System.out.println("Controller : File Tranfert Result received, sending to GUI");
    gui.setAckLog("[FileTranfert] : " + result );    
    }
}
