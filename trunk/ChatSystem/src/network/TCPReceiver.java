package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

class TCPReceiver extends Thread {

    private DataInputStream in;
    private final Socket socket;
    private final int sizeMax = 10000000;

    TCPReceiver(Socket Socket) {
        this.socket = Socket;
    }

    @Override
    public void run() {
        byte[] mybytearray = new byte[sizeMax];
        int numberofBytes;
        File receivedFile;
        DataOutputStream out;
        
        try {
            this.in = new DataInputStream(socket.getInputStream());
            numberofBytes = in.read(mybytearray);
        } catch (IOException ex) {
            System.out.println("TCPReceiver : IOException with read");
        }    
        
        
        receivedFile = new File("fichier1");
        
        try {
            out = new DataOutputStream(new FileOutputStream(receivedFile));
            out.write(mybytearray);
        } catch (FileNotFoundException ex) {
            System.out.println("TCPReceiver : File not found");
        } catch (IOException ex) {
            System.out.println("TCPReceiver : IOException with write");
        }
        }
    
    //code non testé, en théorie ça marche, en pratique surement pas
    // il faudra rajouter un close à la fin, et faire des tests
    }
