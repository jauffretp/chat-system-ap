package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import model.Message;
import org.json.JSONException;
import org.json.JSONObject;

class UDPReceiver extends Thread {

    private DatagramSocket ds;
    //private Thread thread;
    private volatile boolean active;
    private final static int size = 1000;
    private NI ni;

    public UDPReceiver(DatagramSocket ds, NI ni){
        super() ; 
        this.ds = ds;
        this.active = true;
        this.ni = ni;
        this.start() ; 
    }

    public void run() {
        while (isActive()) {
            try {
                DatagramPacket dp = new DatagramPacket(new byte[size], size);
                System.out.println("ready to receive");
                ds.receive(dp);
                System.out.println("dp received!");
                
                Message messageReceived = new Message();
                messageReceived.initMessage(new JSONObject(new String(dp.getData(), "UTF-8")));

                String messageData = (String) messageReceived.get("messageData");
                String nickname = (String) messageReceived.get("userName");
                String type = (String) messageReceived.get("type");

                switch (type) {
                    case "message":
                        ni.processMessage(nickname, messageData);
                        break;
                    case "messageAck":
                        ni.processMessageAck(nickname);
                        break;
                    case "hello":
                        ni.processHello(nickname, dp.getAddress().getHostAddress());
                        break;
                    case "helloAck":
                        ni.processHelloAck(nickname, dp.getAddress().getHostAddress());
                        break;
                    case "goodBye":
                        ni.processHelloAck(nickname, dp.getAddress().getHostAddress());
                        break;
                    default:
                        System.out.println("Erreur : type non reconnu !");
                }

            } catch (IOException ex) {
                if (isActive()) {
                    System.out.println("Error reading from the input file " + ex.getMessage());
                }
            } catch (JSONException ex) {
                System.out.println("Error reading from the input file " + ex.getMessage());
            }
        }
        ds.close();
        System.out.println("closed UDPReceiver");
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }
    

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
