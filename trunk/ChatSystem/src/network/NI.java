package network;


import java.io.File;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Message;
import controller.Controller;

public class NI implements CtrlToNI {

    private final int port = 1337;
    private final int destPort = 1337;
    private ArrayList<TCPSender> tcpSenderArray;
    private ArrayList<TCPReceiver> tcpReceiverArray;
    private TCPSender tcpSender;
    private UDPSender udpSender;
    private UDPReceiver udpReceiver;
    private Controller controller;

    public NI(Controller controller) {
        this.tcpSenderArray = new ArrayList();
        this.tcpReceiverArray = new ArrayList();
        this.tcpSender = new TCPSender();
        this.controller = controller;
        
        try {
            this.udpSender = new UDPSender(port, destPort);
        } catch (SocketException ex) {
            System.out.println("Error creating udpSender");
        }
        
        this.udpReceiver = new UDPReceiver(udpSender.getDs(), this);        
}
        
      

    
    ////////////////////
    // Sending side//
    ///////////////////
    
    @Override
    public void sendHello(String nickname) {
        Message message = new Message();
        message.initMessage("hello", nickname, "", "1");
        System.out.println("Message sent : " + message.toString() ) ; 
        try {
            udpSender.sendMessage(InetAddress.getByName("255.255.255.255"), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending message : Unknown host");
        }
    }

    @Override
    public void sendHelloAck(String local_username, String ip) {
        Message message = new Message();
        message.initMessage("helloAck", local_username, "", "1");
        System.out.println("Message sent : " + message.toString() ) ; 
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending message : Unknown host");
        }}

    @Override
    public void sendGoodbye() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMessage(String username, String ip, String message, String messageNumber) {
        System.out.println("NI : Ready to send \"" + message + "\" to " + username + " @" + ip);
    }

    @Override
    public void sendMessageAck(String local_username, int messageNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendFileTo(File file, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    ////////////////////
    // Receiving side//
    ///////////////////   
    
    @Override
    public void processHello(String nickname, String ip) {
        controller.processHelloReceived(nickname, ip) ;
    }

    @Override
    public void processHelloAck(String nickname, String ip) {
        controller.processHelloAckReceived(nickname, ip) ;
    }
    
    @Override
    public void processGoodBye(String nickname) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public void processMessage(String nickname, String dataMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processMessageAck(String nickname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
    
    
    

}
