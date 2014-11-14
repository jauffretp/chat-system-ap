package listeners;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Anthony
 */
public class ListenSocket extends Thread {

    private final BufferedReader reader;
    private String lastLine;
    private String linesRead;
    private Boolean stop;
    private JTextArea textRec;

    public ListenSocket(BufferedReader reader) {
        this.reader = reader;
        lastLine = "";
        linesRead = "";
        this.stop = false;
        this.textRec = null;
    }

    @Override
    public void run() {
        while (stop == false) {
            try {
                lastLine = reader.readLine();
                if (lastLine == null) {
                    stop = true;
                } else {
                    linesRead = linesRead + '\n' + lastLine;
                    textRec.setText("Messages received : " + linesRead);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "The destination seems to be disconnected. \n", "Listen socket : Error", JOptionPane.ERROR_MESSAGE);
                Font font = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
                textRec.setFont(font);
                textRec.setText("The destination is disconnected. ");
                stop = true;
            }
        }
    }

    public void setArea(JTextArea textRec) {
        this.textRec = textRec;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop() {
        stop = true;
    }

    public void empty() {
        textRec.setText("(Previous messages has been flushed)");
        this.linesRead = "";
        
    }

}
