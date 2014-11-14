package windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Anthony
 */
public class WindowServer extends JFrame implements ActionListener {

    private final JButton bQuit;
    private final JPanel panel;

    public WindowServer() {
        super("Server Configuration Window");
        bQuit = new JButton("Close the server and quit");
        panel = new JPanel();
        
        initComponent() ;         
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponent() {
        bQuit.addActionListener(this);
        panel.add(bQuit, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createTitledBorder(" Server Controller "));

        this.getContentPane().add(panel);
        this.pack();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
