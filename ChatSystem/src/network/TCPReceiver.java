package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

class TCPReceiver extends Thread {

    private BufferedOutputStream out;
    private BufferedInputStream in;
    private File receivedFile = new File("receivefile.txt");
    private final Socket socket;
    private final int sizeMax = 10000000;
    //private final int sizeFragment = 100;

    TCPReceiver(Socket Socket) {
        this.socket = Socket;
    }

    @Override
    public void run() {

        //receiving bytes from the socket
        byte[] receivedBytes = new byte[sizeMax];
        try {
            this.in = new BufferedInputStream(socket.getInputStream());
            in.read(receivedBytes);
        } catch (IOException ex) {
            System.out.println("TCPReceiver : IOException with read");
        }

        //copying the bytes in the file"receivefile.txt"
        try {
            out = new BufferedOutputStream(new FileOutputStream(receivedFile));
            out.write(receivedBytes);
        } catch (FileNotFoundException ex) {
            System.out.println("TCPReceiver : File not found");
        } catch (IOException ex) {
            System.out.println("TCPReceiver : IOException with write");
        }

        System.out.println("TCPReceiver : file succesfully received ");

    }
}
