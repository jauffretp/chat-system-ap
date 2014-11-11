package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Anthony
 */
public class JButtonSendListener implements ActionListener {

    private BufferedWriter writer;
    private JTextArea textToSend;

    public JButtonSendListener(BufferedWriter writer, JTextArea textToSend) {
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
            JOptionPane errorJDialog = new JOptionPane();
            errorJDialog.showMessageDialog(null, "Impossible to send : the destination seems disconnected."
                    , "Send : Error", JOptionPane.ERROR_MESSAGE);        
        }
    }
}
