package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class UDPReceiver extends Thread {

    private final DatagramSocket ds;
    private volatile boolean active;
    private final static int size = 1000;
    private final NI ni;

    public UDPReceiver(DatagramSocket ds, NI ni) {
        super();
        this.ds = ds;
        this.active = true;
        this.ni = ni;
        this.start();
    }

    @Override
    public void run() {
        while (isActive()) {
            try {
                DatagramPacket dp = new DatagramPacket(new byte[size], size);
                System.out.println("ready to receive");
                ds.receive(dp);
                System.out.println("dp received!");

                ni.handlePacketReceived(dp);

            } catch (IOException ex) {
                if (isActive()) {
                    System.out.println("Error reading from the input file " + ex.getMessage());
                }
            }
        }
        ds.close();
        System.out.println("closed UDPReceiver");
    }

    /**
     * @return true if the thread is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active set to false stop the thread
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
