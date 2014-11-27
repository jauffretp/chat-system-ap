package chatsystem;

import controller.Controller;
import ihm.CtrlToGUI;
import ihm.GUI;
import network.CtrlToNI;
import network.NI;


public class ChatSystem {

    /**
     * @param args the command line arguments
     */
    
    private CtrlToNI ni ; 
    private CtrlToGUI gui ;  
    private Controller ctrl ; 
    
    public ChatSystem() {
        this.ctrl = new Controller() ; 
        this.ni = new NI(ctrl) ; 
        this.gui = new GUI(ctrl) ;         
        this.ctrl.setGui(gui);
        this.ctrl.setNi(ni);
    }

    public static void main(String[] args) {
        ChatSystem cs = new ChatSystem() ; 
    }
    
}
