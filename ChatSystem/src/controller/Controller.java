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

    public Controller()  {  
        listModel = new DefaultListModel();
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
    
    ///////////////////////////////////     

    
    // sending side    
     
     
    public void performConnect(String nickname){
        this.nickname = nickname ; 
        System.out.println("Controller : Send Hello (broadcast) , nickname = " + nickname);
        ni.sendHello(nickname); 
    }

    public void performDisconnect(){}
    
    public void performMessage(String txtMessage, Object[] users){
      System.out.println("Controller : Send Message to users selected");
      for (Object userObj : users){
          User user = (User) userObj ; 
          ni.sendMessage(user.getNickname(), user.getIp(), txtMessage, "14");
      }        
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
    
    public void processMessageReceived(String nickname, String dataMessage){
        gui.setTextLog(nickname + " : " + dataMessage);
    }
    
    
    

}
