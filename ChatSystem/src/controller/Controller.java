package controller;

import ihm.CtrlToGUI;
import javax.swing.DefaultListModel;
import model.User;
import network.CtrlToNI;

public class Controller {

    private CtrlToNI ni;
    private CtrlToGUI gui;
    private String nickname ; 
    private  DefaultListModel listModel ;

    public DefaultListModel getListModel() {
        return listModel;
    }
    
    
    public Controller()  {  
        listModel = new DefaultListModel();
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
        System.out.println("Controller : HelloReceived from "+ username + "@" + ip);
        if(!username.equals(nickname)) {   
            System.out.println("Controller : We add ");
            User newUser = new User(username, ip);
            listModel.addElement(newUser);
            gui.setTextLog(newUser + " is connected to the ChatSystem");
        }
        ni.sendHelloAck(nickname, ip);
    }
    
    public void processHelloAckReceived(String username, String ip){
        System.out.println("Controller : HelloAckReceived from "+ username + "@" + ip);   
        
        if(!username.equals(nickname)) {   
            System.out.println("Controller : We add ");
            User newUser = new User(username, ip);
            listModel.addElement(newUser);
            gui.setTextLog(newUser + " is connected to the ChatSystem");
        }
    }
    
    
    
    
    

}
