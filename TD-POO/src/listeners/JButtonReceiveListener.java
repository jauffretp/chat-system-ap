package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author Anthony
 */
public class JButtonReceiveListener implements ActionListener {

    private BufferedWriter writer;
    private JTextArea textToSend;

    public JButtonReceiveListener(BufferedWriter writer, JTextArea textToSend) {
        this.writer = writer;
        this.textToSend = textToSend;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            writer.write(textToSend.getText());
            writer.newLine();
            writer.flush();
            textToSend.setText("");
        } catch (IOException exception) {
            System.err.println("Erreur ecriture ! ");
            exception.printStackTrace();
        }
    }
}
