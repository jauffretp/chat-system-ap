package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anthony
 */
public class ListenSocket extends Thread {

    private BufferedReader reader;
    private String lastLine;
    private String lastLineRead;

    public ListenSocket(BufferedReader reader) {
        this.reader = reader;
        lastLine = "";
        lastLineRead = "";
        this.start();
    }

    public String getLastLine() {
        String returnString = lastLineRead;
        lastLineRead = "";
        return returnString;
    }

    public void run() {
        while (true) {
            try {
                lastLine = reader.readLine();
                lastLineRead = lastLineRead + '\n' + lastLine;
            } catch (IOException ex) {
                Logger.getLogger(ListenSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
