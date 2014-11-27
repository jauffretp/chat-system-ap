
package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import model.Message;
import org.json.JSONObject;


class UDPSender{

       private DatagramSocket ds;
    private final int destPort;
    private final static int size = 1000;

    public UDPSender(int port, int destPort) throws SocketException {
        this.destPort = destPort;
        this.ds = new DatagramSocket(port) ; 
    }
    
     public DatagramSocket getDs() {
        return ds;
    }
    
    public void sendMessage(InetAddress destination, Message message) {
        try {           
            String messageString = new String (message.toString().getBytes(), "UTF-8") ; 
            byte[] data = messageString.getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, destination, destPort);
            ds.send(dp);
            System.out.println("dp sent!");
        } catch (IOException ex) {
            System.out.println("Error sending datagram " + ex.getMessage());
            ex.printStackTrace();
        }
    }    
}
