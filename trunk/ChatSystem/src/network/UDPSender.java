package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import modelMessage.Message;

class UDPSender {

    private final DatagramSocket ds;
    private final int destPort;
    private final static int size = 1000;
    private final  String encoding ;

    public UDPSender(int port, int destPort, String encoding) throws SocketException {
        this.destPort = destPort;
        this.ds = new DatagramSocket(port);
        this.encoding = encoding ; 
        System.out.println("UDPSender : UDP Socket opened on port " + port);
    }

    public DatagramSocket getDs() {
        return ds;
    }

    public void sendMessage(InetAddress destination, Message message) {
        try {
            String messageString = new String(message.toString().getBytes(), encoding);
            byte[] data = messageString.getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, destination, destPort);
            ds.send(dp);
            System.out.println("UDPSender : Packet sent through the port " + destPort);
            System.out.println("UDPSender : Packet = " + dp);
        } catch (IOException ex) {
            System.out.println("UDPSender : Error sending datagram !" + '\n' + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
