package controller;

import ihm.CtrlToGUI;
import model.User;
import network.CtrlToNI;

public class Controller {

    private CtrlToNI ni;
    private CtrlToGUI gui;
    private String nickname ; 
    
    public Controller()  {  
    }

    public void performDisconnect(){}

    public void setNi(CtrlToNI ni) {
        this.ni = ni;
    }

    public void setGui(CtrlToGUI gui) {
        this.gui = gui;
    }
        
    
    // sending side 
    
    public void performConnect(String nickname){
        this.nickname = nickname ; 
        System.out.println("Controller : Send Hello (broadcast) , nickname = " + nickname);
        ni.sendHello(nickname); 
    }

    
    // receiving side 
    
    public void processHelloReceived(String username, String ip){
        gui.addUser(username, ip);
        ni.sendHelloAck(nickname, ip);
    }
    
    public void processHelloAckReceived(String username, String ip){
        System.out.println("Controller : HelloAckReceived from "+ username + "@" + ip);
        gui.addUser(username, ip);        
    }
    
    
    
    

}
