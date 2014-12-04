package network;

import java.util.ArrayList;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import javax.swing.JOptionPane;

import controller.Controller;
import modelMessage.Message;
import modelMessage.MessageFactory;
import modelMessage.MessageFormat;

public class NI implements CtrlToNI {

    private final int port = 1337;
    private final int destPort = 1337;
    private final String adrBroadcast = "255.255.255.255";
    private ArrayList<TCPSender> tcpSenderArray;
    private ArrayList<TCPReceiver> tcpReceiverArray;
    private TCPServer tcpServer;
    private UDPSender udpSender;
    private final UDPReceiver udpReceiver;
    private Controller controller;
    private MessageFactory messageFactory;
    private Message message;

    public NI(Controller controller, MessageFormat typeMessage) {

        this.controller = controller;
        this.messageFactory = MessageFactory.getFactory(typeMessage);

        this.tcpSenderArray = new ArrayList();
        this.tcpReceiverArray = new ArrayList();
        this.tcpServer = new TCPServer(this.port);
        startTCPServer();

        try {
            this.udpSender = new UDPSender(port, destPort, messageFactory.returnEncoding());
        } catch (SocketException ex) {
            System.err.println("NI : Error creating udpSender");
            JOptionPane.showMessageDialog(null, "(NI) UDP Error : The port " + port + " is already in use", "Chatsystem : Error", JOptionPane.ERROR_MESSAGE);
            System.exit(5);
        }
        this.udpReceiver = new UDPReceiver(udpSender.getDs(), this);
        startUDPReceiver();
    }

    private void startUDPReceiver() {
        this.udpReceiver.start();
    }

    private void startTCPServer() {
        this.tcpServer.start();
    }

    ////////////////////
    // Sending side//
    ///////////////////
    @Override
    public void sendHello(String nickname) {
        message = messageFactory.createMessageFromValues("hello", nickname, "", -1);
        System.out.println("NI/SendHello -> Message to be sent : " + message.toString() + " to " + adrBroadcast);
        try {
            udpSender.sendMessage(InetAddress.getByName(adrBroadcast), message);
        } catch (UnknownHostException ex) {
            System.err.println("NI : Error sending Hello message (unknown host)");
        }
    }

    @Override
    public void sendHelloAck(String local_username, String ip) {
        message = messageFactory.createMessageFromValues("helloAck", local_username, "", -1);
        System.out.println("NI/SendHelloAck -> Message to be sent : " + message.toString() + " to " + ip);
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.err.println("NI : Error sending Hello Ack message (unknown host)");
        }
    }

    @Override
    public void sendGoodbye() {
        message = messageFactory.createMessageFromValues("goodBye", " ", " ", -1);
        System.out.println("NI/sendGoodBye -> Message to be sent : " + message.toString() + " to " + adrBroadcast);
        try {
            udpSender.sendMessage(InetAddress.getByName(adrBroadcast), message);
        } catch (UnknownHostException ex) {
            System.err.println("NI : Error sending Goodbye message (unknown host)");
        }

    }

    @Override
    public void sendMessage(String username, String ip, String txtMessage, int messageNumber) {
        System.out.println("NI : Ready to send \"" + txtMessage + "\" to " + username + " @" + ip);
        message = messageFactory.createMessageFromValues("message", username, txtMessage, messageNumber);
        System.out.println("NI/sendMessage -> Message sent : " + message.toString() + " to " + ip);
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.err.println("NI : Error sending message to " + ip + " (unknown host)");
        }

    }

    @Override
    public void sendMessageAck(String ip, int messageNumber) {
        System.out.println("NI : Ready to ack messageNumber \"" + messageNumber + "\"");
        message = messageFactory.createMessageFromValues("messageAck", " ", " ", messageNumber);
        System.out.println("NI/sendMessageAck -> Message sent : " + message.toString() + " to " + ip);
        try {
            udpSender.sendMessage(InetAddress.getByName(ip), message);
        } catch (UnknownHostException ex) {
            System.err.println("NI : Error sending messageAck to " + ip + " (unknown host)");
        }
    }

    @Override
    public void sendFileTo(String filePath, String remoteIp) {
        System.out.println("NI: Sending : " + filePath + " to " + remoteIp);
        TCPSender newSender = new TCPSender(filePath, remoteIp);
        newSender.start();
    }

    ////////////////////
    // Receiving side//
    ///////////////////   
    public void handlePacketReceived(Object packet) {
        if (packet instanceof DatagramPacket) {
            System.out.println("NI/HandlePacket : DatagramPacket received");
            DatagramPacket dp = (DatagramPacket) packet;
            String ip = dp.getAddress().getHostAddress();

            message = messageFactory.createMessageFromNIObject(packet);

            String type = (String) message.getType();
            String messageData = (String) message.getMessageData();
            String nickname = (String) message.getUserName();
            int messageNumber = (int) message.getMessageNumber();


            if (type.equals("message")) {
                System.out.println("NI/HandlePacket :  Received packet type is message");
                processMessage(ip, messageData);
                sendMessageAck(ip, messageNumber);
            } else if (type.equals("messageAck")) {
                System.out.println("NI : Received packet type is messageAck");
                System.out.println("Ack number is : " + messageNumber);
                processMessageAck(messageNumber);
            } else if (type.equals("hello")) {
                System.out.println("NI/HandlePacket : Received packet type is hello");
                processHello(nickname, ip);
            } else if (type.equals("helloAck")) {
                System.out.println("NI/HandlePacket : Received packet type is helloAck");
                processHelloAck(nickname, dp.getAddress().getHostAddress());
            } else if (type.equals("goodBye")) {
                System.out.println("NI/HandlePacket : Received packet type is goodBye");
                processGoodBye(ip);
            } else {
                System.err.println("NI/HandlePacket : Error with the message (don't recognize Message type)");
            }

        } else {
            System.err.println("NI/HandlePacket : Packet type received not recognized");
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
