package communication;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anthony
 */
public class ListenSocket extends Thread {

    private BufferedReader reader;
    private String lastLine;
    private String lastLineRead;
    private Boolean stop ;  

    public ListenSocket(BufferedReader reader) { 
        this.reader = reader;
        lastLine = "";
        lastLineRead = "";
        this.stop = false ; 
        this.start() ; 
    }

    
    public Boolean getStop() {
        return stop;
    }
      
    public String getLastLine() {
        String returnString = lastLineRead;
        lastLineRead = "";
        return returnString;
    }    
    
    
    public void run() {
        while (!stop) {
            try {
                lastLine = reader.readLine();
                lastLineRead = lastLineRead + '\n' + lastLine;
            } catch (IOException ex) {
            JOptionPane errorJDialog = new JOptionPane();
            errorJDialog.showMessageDialog(null, "The destination seems to be disconnected. \n"
                    , "Listen socket : Error", JOptionPane.ERROR_MESSAGE);
            stop = true ;             
            }
        } 
    }
}
