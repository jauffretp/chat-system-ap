package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

class TCPSender extends Thread {

    private final String filePath;
    private final InetAddress remoteIp;
    private Socket socket;
    private final int destPort;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private final File file;

    TCPSender(String filePath, InetAddress remoteIp, int destPort) {
        this.destPort = destPort;
        this.filePath = filePath;
        this.remoteIp = remoteIp;
        this.file = new File(filePath);
        System.out.println("TCPSender : New TCP Sender created");
    }

    @Override
    public void run() {
        
        //creation of the socket
        try {
            System.out.println("TCPSender : creating socket with remoteIP = " + remoteIp.toString() + " port : " + destPort);
            socket = new Socket(remoteIp, destPort);
        } catch (IOException ex) {
            System.out.println("TCPSender : Creation of the sending socket failed");
        }

        
        //sending of the file
        
        //getting the bytes of the file in an Array
        System.out.println("TCPSender : TCP Sender running");
        byte[] myArray = new byte[(int) this.file.length()];
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            in.read(myArray);
        } catch (FileNotFoundException ex) {
            System.out.println("TCPSender : file not found");
        } catch (IOException ex) {
            System.out.println("TCP Sender : IO Exception while getting the bytes from the file");
        }

        //sending the byte array through the socket
        try {
            out = new BufferedOutputStream(socket.getOutputStream());
            out.write(myArray);
        } catch (IOException ex) {
            System.out.println("TCP Sender : IO Exception while sending the bytes through the socket");
        }
       
        //closing the stream so the receiver knows the file is finished
        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("IO Exception while closing the stream");
          }
        System.out.println("TCP Sender : File : " + filePath + " succesfully sent");
    }

}
