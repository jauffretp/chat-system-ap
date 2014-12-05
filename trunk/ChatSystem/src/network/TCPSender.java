package network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class TCPSender extends Thread {

    private NI ni;
    private final String filePath;
    private final InetAddress remoteIp;
    private final int destPort;
    private Socket socket;
    String tranferResult;

    TCPSender(NI ni, String filePath, InetAddress remoteIp, int destPort) {
        this.ni = ni;
        this.destPort = destPort;
        this.filePath = filePath;
        this.remoteIp = remoteIp;
        this.tranferResult = "Tranfert not executed yet";

        //creates the socket
        try {
            System.out.println("TCPSender : creating socket with remoteIP = " + remoteIp.toString() + " port : " + destPort);
            socket = new Socket(remoteIp, destPort);
            System.out.println("TCPSender : success. Ready to send file.");
        } catch (IOException ex) {
            System.err.println("TCPSender : Creation of the sending socket failed");
        }
    }

    public String getTranferResult() {
        return tranferResult;
    }

    @Override
    // Send the file
    public void run() {

        FileInputStream fileInputStream = null;

        try {

            File fileToSend = new File(filePath);
            System.out.println("TCPSender : File to be sent : " + fileToSend.getName());
            byte[] fileBytes = new byte[(int) fileToSend.length()];
            fileInputStream = new FileInputStream(fileToSend);
            System.out.println("TCPSender : FileInputStream Succeed");

            // getting informations about the file to send 
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(fileBytes, 0, fileBytes.length);
            System.out.println("TCPSender : Informations such as filename and filesize collected");

            // ready to send through the socket
            OutputStream socketOutput = socket.getOutputStream();

            // sending file name and file size 
            DataOutputStream dos = new DataOutputStream(socketOutput);
            dos.writeUTF(fileToSend.getName());
            dos.writeLong(fileBytes.length);
            dos.write(fileBytes, 0, fileBytes.length);
            dos.flush();
            System.out.println("TCPSender : Filename and filesize sent");

            // sending file data 
            socketOutput.write(fileBytes, 0, fileBytes.length);
            System.out.println("TCPSender : write ok");
            socketOutput.flush();
            System.out.println("TCPSender : File data sent");
                       
            
            // closing sockets and IOs objects
            socketOutput.close();
            dos.close();
            socket.close();
            System.out.println("TCPSender : Transfert file has succeeded ! Close socket.");
            tranferResult = "The file has been sent to " + remoteIp;

        } catch (FileNotFoundException ex) {
            System.err.println("TCPSender : File " + filePath + "not found");
            tranferResult = "The file doesn't exist";
        } catch (IOException ex) {
            System.err.println("TCPSender : IOException");
            ex.printStackTrace();
            tranferResult = "The file can't be sent (IOException)";
        }  finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                System.err.println("TCPSender : Can't close FileInputStream");
                tranferResult = "TCPSender : FileInputStream fails to close";
            }
            ni.processTranfertResult(tranferResult);
        }

    }
}
