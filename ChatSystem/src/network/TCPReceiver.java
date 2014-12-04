package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class TCPReceiver extends Thread {

    private BufferedOutputStream out;
    private BufferedInputStream in;
    private final File receivedFile = new File("receivefile.txt");
    private final Socket socket;
    private final int sizeMax = 100000000;

    TCPReceiver(Socket Socket) {
        this.socket = Socket;
    }

    @Override
    public void run() {

        //receiving bytes from the socket
        byte[] receivedBytes = new byte[sizeMax];
        
        try {
            this.in = new BufferedInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("TCPReceiver : IO Exception while creating the inputstream");
        }
        
        
        int numberBytesRead = 0;
        int index = 0;

        while (numberBytesRead > -1){
            //receiving bytes from the socket
            try {
                System.out.println(" TCPReceiver : Index : " + index + " Number of bytes read this time : " + numberBytesRead + " space left = " + (receivedBytes.length - index) );
                numberBytesRead = in.read(receivedBytes, index, receivedBytes.length - index);
                index += numberBytesRead;
                System.out.println(numberBytesRead);

            } catch (IOException ex) {
                System.out.println("TCPReceiver : IOException with read");

            }
        } ;

        //copying the bytes in the file"receivefile.txt"
        try {
            System.out.println("TCPReceiver : writing into the file");
            out = new BufferedOutputStream(new FileOutputStream(receivedFile));
            out.write(receivedBytes);
            System.out.println("TCPReceiver : file written");

        } catch (FileNotFoundException ex) {
            System.out.println("TCPReceiver : File not found");
        } catch (IOException ex) {
            System.out.println("TCPReceiver : IOException with write");
        }

        System.out.println("TCPReceiver : file succesfully received ");
        
    }
}

