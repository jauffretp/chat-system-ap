package network;

import java.io.File;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import controller.Controller;
import javax.swing.JOptionPane;
import modelMessage.Message;
import modelMessage.MessageFactory.MessageFormat;
import static modelMessage.MessageFactory.getMessageObject;


public class NI implements CtrlToNI {

    private final int port = 1337;
    private final int destPort = 1337;
    private final String adrBroadcast = "255.255.255.255"  ; 
     
    
    private ArrayList<TCPSender> tcpSenderArray;
    private ArrayList<TCPReceiver> tcpReceiverArray;
    private TCPSender tcpSender;
    private UDPSender udpSender;
    
    private final UDPReceiver udpReceiver;
    private Controller controller;
    private MessageFormat typeMessage ; 

    public NI(Controller controller, MessageFormat typeMessage) {
        
        this.controller = controller;
        this.typeMessage = typeMessage ; 
        
        this.tcpSenderArray = new ArrayList();
        this.tcpReceiverArray = new ArrayList();
        this.tcpSender = new TCPSender();
                
        try {
            this.udpSender = new UDPSender(port, destPort);
        } catch (SocketException ex) {
            System.out.println("Error creating udpSender");
            JOptionPane.showMessageDialog(null, "UDP Error : The port " + port + " is already in use", "Chatsystem : Error", JOptionPane.ERROR_MESSAGE);
            System.exit(5) ; 
        }
        this.udpReceiver = new UDPReceiver(udpSender.getDs(), this);
    }

    ////////////////////
    // Sending side//
    ///////////////////
    @Override
    public void sendHello(String nickname) {
        Message message = getMessageObject(typeMessage) ; 
        message.initMessage("hello", nickname, "", -1);
        System.out.println("Message sent : " + message.toString());
        try {
            udpSender.sendMessage(InetAddress.getByName(adrBroadcast), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending message : Unknown host");
        }
    }

    @Override
    public void sendHelloAck(String local_username, String ip) {
        Message message = getMessageObject(typeMessage) ; 
        message.initMessage("helloAck", local_username, "", -1); 
        System.out.println("Message sent : " + message.toString());
        
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending message : Unknown host");
        }
    }

    @Override
    public void sendGoodbye() {
        Message message = getMessageObject(typeMessage) ;
        message.initMessage("goodBye", " ", " ", -1);
        try {
            udpSender.sendMessage(InetAddress.getByName("255.255.255.255"), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending message : Unknown host");
        }

    }

    @Override
    public void sendMessage(String username, String ip, String txtMessage, int messageNumber) {
        System.out.println("NI : Ready to send \"" + txtMessage + "\" to " + username + " @" + ip);
        Message message = getMessageObject(typeMessage) ;
        message.initMessage("message", username, txtMessage, messageNumber);
        System.out.println("Message sent : " + message.toString());
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending message to " + ip + " : Unknown host");
        }

    }

    @Override
    public void sendMessageAck(String ip, int messageNumber) {
        System.out.println("NI : Ready to ack messageNumber\"" + messageNumber);
        Message message = getMessageObject(typeMessage) ;
        message.initMessage("messageAck", " ", " ", messageNumber);
        System.out.println("Message sent : " + message.toString());
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.out.println("Error sending messageAck to " + ip + " : Unknown host");
        }
    }

    @Override
    public void sendFileTo(File file, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ////////////////////
    // Receiving side//
    ///////////////////   
    public void handlePacketReceived(Object packet) {
        if (packet instanceof DatagramPacket) {
            try {
                DatagramPacket dp = (DatagramPacket) packet;

                Message messageReceived = getMessageObject(typeMessage) ;
                messageReceived.initMessage(new JSONObject(new String(dp.getData(), "UTF-8")));

                String ip = dp.getAddress().getHostAddress();

                String messageData = (String) messageReceived.getMessageData();
                int messageNumber = (int) messageReceived.getMessageNumber();
                String nickname = (String) messageReceived.getUserName();
                String type = (String) messageReceived.getType();

                if (type.equals("message")) {
                    System.out.println("NI Type = message");
                    System.out.println("UDPReceiver : " + nickname);
                    processMessage(ip, messageData);
                    sendMessageAck(ip, messageNumber);
                } else if (type.equals("messageAck")) {
                    System.out.println("NI Type = messageAck");
                    System.out.println(messageNumber);
                    processMessageAck(messageNumber);
                } else if (type.equals("hello")) {
                    System.out.println("NI Type = hello");
                    processHello(nickname, ip);
                } else if (type.equals("helloAck")) {
                    System.out.println("NI Type = helloAck");
                    processHelloAck(nickname, dp.getAddress().getHostAddress());
                } else if (type.equals("goodBye")) {
                    System.out.println("NI Type = goodBye"); 
                    processGoodBye(ip);
                } else {
                    System.out.println("UDPReceiver : Error with the message (don't recognize type)");
                }
            } catch (JSONException ex) {
                System.out.println("NI : Can't create Message Object");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(NI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("NI : Packet type received not recognized");
        }
    }

    @Override
    public void processHello(String nickname, String ip) {
        controller.processHelloReceived(nickname, ip);
    }

    @Override
    public void processHelloAck(String nickname, String ip) {
        controller.processHelloAckReceived(nickname, ip);
    }

    @Override
    public void processGoodBye(String ip) {
        controller.processGoodbyeReceived(ip);
    }

    @Override
    public void processMessage(String ip, String dataMessage) {
        controller.processMessageReceived(ip, dataMessage);
    }

    @Override
    public void processMessageAck(int messageNumber) {
        controller.processMessageAckReceived(messageNumber);
    }

}
