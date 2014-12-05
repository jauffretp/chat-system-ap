package network;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class TCPReceiver extends Thread {

    NI ni ; 
    private final Socket socket;
    String receiveResult ; 

    TCPReceiver(NI ni, Socket Socket) {
        this.ni = ni ; 
        this.socket = Socket;
        receiveResult = "Receiving not done yet" ; 
    }

    public String getReceiveResult() {
        return receiveResult;
    }
   
    
    @Override
    public void run() {

        try {
            
            InputStream in = socket.getInputStream();
            DataInputStream clientData = new DataInputStream(in);

            // read filename and filesize informations about file to receive
            String fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(fileName);
            long size = clientData.readLong();
             System.out.println("TCPReceiver : Ready to receive " + fileName + " , size : " + size);
            
            // read data  
            int bytesRead;
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }
            this.sleep(10000000);
            
            
            // closing the FileOutputStream handle  
            receiveResult = fileName + " received from " + socket.getInetAddress().toString() ;
            in.close();
            clientData.close();
            output.close();
            System.out.println("TCPReceiver : Tranfert succeded ! Received : " + fileName);
            ni.processFileReceived(receiveResult) ;             
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        

    }
}
