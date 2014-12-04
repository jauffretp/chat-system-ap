
package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

class TCPServer extends Thread {
    
    
    private ServerSocket serverSocket ;
    private int SOCKET_PORT = 1337;
    private volatile boolean active;

    TCPServer(int port) {
        this.SOCKET_PORT = port;
        try {
            System.out.println("TCPServer : Creating server socket on port " + SOCKET_PORT);
            serverSocket = new ServerSocket(SOCKET_PORT);
            this.active = true;
        } catch (IOException ex) {
            System.out.println("TCPServer : Error creating the server socket, port might be already in use");
        }    
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }
    
    @Override
    public void run(){
        while(isActive()){
            System.out.println("TCP Server : Waiting for a TCP request");
            try {
                TCPReceiver TCPReceiver = new TCPReceiver(serverSocket.accept());
                System.out.println("TCP Server : Created a new TCP Receiver");
                TCPReceiver.start();
            } catch (IOException ex) {
                System.out.println("TCP Server : Error accepting the connection");
            }
        }
        
    }
    
}
