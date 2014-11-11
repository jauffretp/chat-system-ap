package listeners;


import communication.ListenSocket;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Anthony
 */
public class JButtonReceiveListener implements ActionListener {

    private final ListenSocket ls;
    private final JTextArea textRec;
    private String messageReceived;

    public JButtonReceiveListener(ListenSocket ls, JTextArea textRec) {
        this.ls = ls;
        this.textRec = textRec;
        this.messageReceived = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String previousMessage = messageReceived;
        Font font;
        if (ls.getStop()) {
            JOptionPane errorJDialog = new JOptionPane();
            errorJDialog.showMessageDialog(null, "Impossible to receive : the destination seems disconnected."
                    , "Receive : Error", JOptionPane.ERROR_MESSAGE);              
        }
        else {
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
                JOptionPane errorJDialog = new JOptionPane();
                 errorJDialog.showMessageDialog(null, "Problème avec StreamReader. \n",
                                           "Receive : Error", JOptionPane.ERROR_MESSAGE);   
             }

            textRec.setText("You haven't received any message. \n "
                    + "Last message received : " + ligne);
        }
        textRec.setFont(font);
    }
    }
}
