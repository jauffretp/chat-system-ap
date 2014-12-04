package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class TCPSender extends Thread {

    private String filePath;
    private String remoteIp;
    private Socket socket;
    private int destPort;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private File file;

    TCPSender(String filePath, String remoteIp) {
        this.filePath = filePath;
        this.remoteIp = remoteIp;
        this.file = new File(filePath);
        System.out.println("TCPSender : New TCP Sender created");
    }

    @Override
    public void run() {
        System.out.println("TCPSender : TCP Sender running");
        byte[] myArray = new byte[(int) this.file.length()];
        try {
            in = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            System.out.println("TCPSender : file not found");
        }

        try {
            socket = new Socket(InetAddress.getByName(remoteIp), destPort);
        } catch (IOException ex) {
            System.out.println("TCPSender : Creation of the sending socket failed");
        }

        try {
            in.read(myArray);
        } catch (IOException ex) {
            System.out.println("TCP Sender : IO Exception while getting the bytes from the file");
        }

        try {
            out = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("TCP Sender : IO Exception while getting the InputStream from the socket");
        }
        
        try {
            out.write(myArray);
        } catch (IOException ex) {
            System.out.println("TCP Sender : IO Exception while writing the array in the socket");
        }
        
        
        
    }

}
