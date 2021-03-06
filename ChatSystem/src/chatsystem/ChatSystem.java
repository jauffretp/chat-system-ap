package chatsystem;

import controller.Controller;
import ihm.CtrlToGUI;
import ihm.GUI;
import network.CtrlToNI;
import network.NI;
import modelMessage.MessageFormat;

public class ChatSystem {

    private final CtrlToNI ni;
    private final CtrlToGUI gui;
    private final Controller ctrl;

    public ChatSystem() {
        this.ctrl = new Controller();
        this.ni = new NI(ctrl, MessageFormat.JSON);
        this.gui = new GUI(ctrl);
        this.ctrl.setGui(gui);
        this.ctrl.setNi(ni);
    }

    public static void main(String[] args) {
        ChatSystem cs = new ChatSystem();
    }

}
