package listeners;

import communication.Comunica;
import communication.ListenSocket;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Anthony
 */
public class JButtonSendListener implements ActionListener {

    private ListenSocket ls;
    private JTextArea textRec;
    private String messageReceived;

    public JButtonSendListener(ListenSocket ls, JTextArea textRec, String messageReceived) {
        this.ls = ls;
        this.textRec = textRec;
        this.messageReceived = messageReceived;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String previousMessage = messageReceived;
        Font font;
        messageReceived = ls.getLastLine();
        if (!(messageReceived.equals(previousMessage) || (messageReceived.equals("")))) {
            font = new Font(Font.SERIF, Font.PLAIN, 14);
            textRec.setText("Message(s) received : " + messageReceived);
        } else {
            messageReceived = previousMessage;
            font = new Font("Serif", Font.ITALIC | Font.BOLD, 12);

            BufferedReader bufderniereligne = new BufferedReader(new StringReader(messageReceived));
            String ligne = "";
            String derniereLigne = "";
            try {
                while ((derniereLigne = bufderniereligne.readLine()) != null) {
                    ligne = derniereLigne;
                }
            } catch (IOException ex) {
                Logger.getLogger(Comunica.class.getName()).log(Level.SEVERE, null, ex);
            }

            textRec.setText("You haven't received any message. \n "
                    + "Last message received : " + ligne);
        }
        textRec.setFont(font);
    }
}
