package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonRefreshListener extends Thread implements ActionListener {

    private final ListenSocket ls;

    public JButtonRefreshListener(ListenSocket ls) {
        this.ls = ls;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ls.empty();
    }

}
