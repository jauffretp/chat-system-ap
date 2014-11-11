package communication;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ComunicaTCPServer extends Thread {

    final static int port = 9633;
    private Socket socketClient;
    private Comunica Com;
    private BufferedReader bfin;
    private BufferedWriter bfout;
    private ListenSocket ls ; 
    
    
    public static void main(String[] args) {
    try {
      ServerSocket socketServeur = new ServerSocket(port);
      System.out.println("Server is launched");
      while (true) {
        Socket socketClientAccepted = socketServeur.accept();
        ComunicaTCPServer t = new ComunicaTCPServer(socketClientAccepted);
        t.start();
      }
    } catch (Exception e) {
      JOptionPane errorServerJDialog = new JOptionPane();
      errorServerJDialog.showMessageDialog(null, "Impossible to launch the server : the port is already in use. \n"
                    + "The application is going to close.", "Server : Error", JOptionPane.ERROR_MESSAGE);        
        
    }
  }

  public ComunicaTCPServer(Socket socketClientAccepted) {
    this.socketClient = socketClientAccepted;
  } 
  
    public void run() {
            String message = "";
            try {                
                System.out.println("Connexion accepted by the server with : " + socketClient.getInetAddress());

                bfin = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                bfout = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                ls = new ListenSocket(bfin);
                Com = new Comunica(ls, bfout); 
                
           } catch (IOException e) {
                    JOptionPane errorJDialog = new JOptionPane();
                    errorJDialog.showMessageDialog(null, "Problem getting InputStream", "TCPServer : Error", JOptionPane.ERROR_MESSAGE); 
            }                  
    }
}
