package controller;

import javax.swing.DefaultListModel;

import ihm.CtrlToGUI;
import network.CtrlToNI;
import modelUser.User;


public class Controller {

    private CtrlToNI ni;
    private CtrlToGUI gui;
    private String nickname;
    private final DefaultListModel listModel;
    private int messageNumberCpt ; 
    
    
    public Controller() {
        listModel = new DefaultListModel();
        messageNumberCpt = 0 ;
    }

    public void setNi(CtrlToNI ni) {
        this.ni = ni;
    }

    public void setGui(CtrlToGUI gui) {
        this.gui = gui;
    }

    
    
    /// Handling the model ///////////
    public DefaultListModel getListModel() {
        return listModel;
    }

    public String getUsername(String ip) {
        String username = "";
        for (int index = 0; index < listModel.size(); index++) {
            Object user = listModel.get(index);
            if (((User) user).getIp().equals(ip)) {
                username = ((User) user).getNickname();
            }
        }
        return username;
    }

    public User getUser(String ip) {
        User userResult = null;
        for (int index = 0; index < listModel.size(); index++) {
            Object user = listModel.get(index);
            if (((User) user).getIp().equals(ip)) {
                userResult = ((User) user);
            }
        }
        return userResult;
    }
    ///////////////////////////////////  
    
    
    // sending side /////////////////////////    
    public void performConnect(String nickname) {
        this.nickname = nickname;
        System.out.println("Controller : Send Hello (broadcast) , nickname = " + nickname);
        ni.sendHello(nickname);
    }

    public void performDisconnect() {
        System.out.println("Controller : Send Goodbye (broadcast) , nickname = " + nickname);
        ni.sendGoodbye();
    }

    public void performMessage(String txtMessage, Object[] users) {
            System.out.println("Controller : Send Message to users selected");
            for (Object userObj : users) {
                User user = (User) userObj;
                gui.setTextLog("You (to " + user.getNickname() + ")" + " : " + txtMessage);
                ni.sendMessage(nickname, user.getIp(), txtMessage, messageNumberCpt);
                messageNumberCpt++ ; 
            }
        }
    /////////////////////////////////// 

    // receiving side /////////////////
    public void processHelloReceived(String username, String ip) {
        System.out.println("Controller : HelloReceived from " + username + "@" + ip);
        //if(!username.equals(nickname)) {   
        System.out.println("Controller : We add ");
        User newUser = new User(username, ip);
        listModel.addElement(newUser);
        gui.setTextLog(newUser + " is connected to the ChatSystem");
        //}
        ni.sendHelloAck(nickname, ip);
    }

    public void processHelloAckReceived(String username, String ip) {
        System.out.println("Controller : HelloAckReceived from " + username + "@" + ip);

        if (!username.equals(nickname)) {
            System.out.println("Controller : We add " + nickname);
            User newUser = new User(username, ip);
            listModel.addElement(newUser);
            gui.setTextLog(newUser + " is connected to the ChatSystem");
        }
    }

    public void processGoodbyeReceived(String ip) {
        User userDisconnected = getUser(ip);
        listModel.removeElement(userDisconnected);
        gui.setTextLog(userDisconnected + " is now disconnected from the Chatsystem !");
    }

    public void processMessageReceived(String ip, String dataMessage) {
        String username = getUsername(ip);
        System.out.println("UDPReceiver : " + username);
        gui.setTextLog(username + " : " + dataMessage);
    }

    public void processMessageAckReceived(int messageNumber) {
        gui.setAckLog("[ACK] Message " + messageNumber );
    }

}
