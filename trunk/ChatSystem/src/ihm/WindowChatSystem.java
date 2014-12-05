package ihm;

import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class WindowChatSystem extends javax.swing.JFrame {

    private final GUI gui;

    public WindowChatSystem(GUI gui) {
        this.gui = gui;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneList = new javax.swing.JScrollPane();
        jListUserList = new javax.swing.JList();
        jScrollPaneLog = new javax.swing.JScrollPane();
        zoneLog = new javax.swing.JTextArea();
        jPanelSendFile = new javax.swing.JPanel();
        jFileChooser = new javax.swing.JFileChooser();
        jPanelSendMessage = new javax.swing.JPanel();
        jButtonSend = new javax.swing.JButton();
        jButtonClearTextField = new javax.swing.JButton();
        jScrollPaneEditor = new javax.swing.JScrollPane();
        jEditorPaneMessage = new javax.swing.JEditorPane();
        jCheckBoxEnterToSend = new javax.swing.JCheckBox();
        jButtonQuit = new javax.swing.JButton();
        jButtonClearLog = new javax.swing.JButton();
        jLabelSelectUsers = new javax.swing.JLabel();
        jScrollPaneAckLog = new javax.swing.JScrollPane();
        zoneAck = new javax.swing.JTextArea();
        acknowledgmentLabel = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuMenu = new javax.swing.JMenu();
        jMenuItemQuit = new javax.swing.JMenuItem();
        jMenuQuestion = new javax.swing.JMenu();
        jMenuItemInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatSystem v0.1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setName("mainFrame"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jListUserList.setModel(gui.getListModel());
        jScrollPaneList.setViewportView(jListUserList);

        zoneLog.setEditable(false);
        zoneLog.setColumns(20);
        zoneLog.setRows(5);
        zoneLog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zoneLog.setLineWrap(true);
        zoneLog.setWrapStyleWord(true);
        jScrollPaneLog.setViewportView(zoneLog);

        jPanelSendFile.setBorder(javax.swing.BorderFactory.createTitledBorder("Send a file"));

        jFileChooser.setAutoscrolls(true);
        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSendFileLayout = new javax.swing.GroupLayout(jPanelSendFile);
        jPanelSendFile.setLayout(jPanelSendFileLayout);
        jPanelSendFileLayout.setHorizontalGroup(
            jPanelSendFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelSendFileLayout.setVerticalGroup(
            jPanelSendFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSendFileLayout.createSequentialGroup()
                .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanelSendMessage.setBorder(javax.swing.BorderFactory.createTitledBorder("Send a message"));

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jButtonClearTextField.setText("Clear");
        jButtonClearTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearTextFieldActionPerformed(evt);
            }
        });

        jEditorPaneMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jEditorPaneMessageKeyPressed(evt);
            }
        });
        jScrollPaneEditor.setViewportView(jEditorPaneMessage);

        jCheckBoxEnterToSend.setText("Press Enter to send ");

        javax.swing.GroupLayout jPanelSendMessageLayout = new javax.swing.GroupLayout(jPanelSendMessage);
        jPanelSendMessage.setLayout(jPanelSendMessageLayout);
        jPanelSendMessageLayout.setHorizontalGroup(
            jPanelSendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSendMessageLayout.createSequentialGroup()
                .addGroup(jPanelSendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonClearTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSend, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxEnterToSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelSendMessageLayout.setVerticalGroup(
            jPanelSendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSendMessageLayout.createSequentialGroup()
                .addComponent(jScrollPaneEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jCheckBoxEnterToSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jButtonClearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonQuit.setText("Quit");
        jButtonQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });

        jButtonClearLog.setText("Clear logs");
        jButtonClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearLogActionPerformed(evt);
            }
        });

        jLabelSelectUsers.setText("<html>      Select the user(s) you want  <br>   to communicate with : </html>");

        zoneAck.setEditable(false);
        zoneAck.setColumns(20);
        zoneAck.setRows(5);
        jScrollPaneAckLog.setViewportView(zoneAck);

        acknowledgmentLabel.setText("Acknowledgments : ");

        jMenuMenu.setText("Menu");

        jMenuItemQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemQuit.setText("Quit");
        jMenuItemQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitActionPerformed(evt);
            }
        });
        jMenuMenu.add(jMenuItemQuit);

        jMenuBar.add(jMenuMenu);

        jMenuQuestion.setText("?");

        jMenuItemInfo.setText("About");
        jMenuItemInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInfoActionPerformed(evt);
            }
        });
        jMenuQuestion.add(jMenuItemInfo);

        jMenuBar.add(jMenuQuestion);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneLog, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPaneList, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPaneAckLog, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jLabelSelectUsers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                    .addComponent(jButtonClearLog, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(acknowledgmentLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonClearLog, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acknowledgmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneAckLog, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneLog, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSelectUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneList, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelSendFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        gui.actionDisconnect();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitActionPerformed
        gui.actionDisconnect();
        System.exit(0);
    }//GEN-LAST:event_jButtonQuitActionPerformed

    private void jMenuItemQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitActionPerformed
        gui.actionDisconnect();
        System.exit(0);
    }//GEN-LAST:event_jMenuItemQuitActionPerformed

    private void jMenuItemInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInfoActionPerformed
        JOptionPane.showMessageDialog(null, "Programmers : JAUFFRET Pascal & GOURRAUD Anthony", "Informations", WIDTH);
    }//GEN-LAST:event_jMenuItemInfoActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        Object[] users;
        users = jListUserList.getSelectedValues();
        if (users.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select an user", "ChatSystem  : Sending", JOptionPane.INFORMATION_MESSAGE);
        } else {
            gui.sendButtonPushed(users, jEditorPaneMessage.getText());
            jEditorPaneMessage.setText("");
        }
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonClearTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearTextFieldActionPerformed
        jEditorPaneMessage.setText("");
    }//GEN-LAST:event_jButtonClearTextFieldActionPerformed

    private void jButtonClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearLogActionPerformed
        zoneLog.setText("");
        zoneAck.setText("");
    }//GEN-LAST:event_jButtonClearLogActionPerformed

    private void jEditorPaneMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEditorPaneMessageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && jCheckBoxEnterToSend.isSelected()) {
            jEditorPaneMessage.setText(jEditorPaneMessage.getText().replace('\n', ' '));
            jButtonSendActionPerformed(null);
        }
    }//GEN-LAST:event_jEditorPaneMessageKeyPressed

    private void jFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooserActionPerformed


        if (evt.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            Object[] users;
            users = jListUserList.getSelectedValues();
            String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
            if (users.length == 0) {
                JOptionPane.showMessageDialog(null, "Please select users to send your file", "ChatSystem  : Sending", JOptionPane.INFORMATION_MESSAGE);
            } else {
                
                gui.sendFileButtonPushed(filePath,users);
            }
        }
    }//GEN-LAST:event_jFileChooserActionPerformed

    void setLog(String text) {
        zoneLog.append(text + "\n");
        zoneLog.setCaretPosition(zoneLog.getDocument().getLength());
    }

    void setAckLog(String text) {
        zoneAck.append(text + "\n");
        zoneAck.setCaretPosition(zoneAck.getDocument().getLength());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acknowledgmentLabel;
    private javax.swing.JButton jButtonClearLog;
    private javax.swing.JButton jButtonClearTextField;
    private javax.swing.JButton jButtonQuit;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JCheckBox jCheckBoxEnterToSend;
    private javax.swing.JEditorPane jEditorPaneMessage;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabelSelectUsers;
    private javax.swing.JList jListUserList;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemInfo;
    private javax.swing.JMenuItem jMenuItemQuit;
    private javax.swing.JMenu jMenuMenu;
    private javax.swing.JMenu jMenuQuestion;
    private javax.swing.JPanel jPanelSendFile;
    private javax.swing.JPanel jPanelSendMessage;
    private javax.swing.JScrollPane jScrollPaneAckLog;
    private javax.swing.JScrollPane jScrollPaneEditor;
    private javax.swing.JScrollPane jScrollPaneList;
    private javax.swing.JScrollPane jScrollPaneLog;
    private javax.swing.JTextArea zoneAck;
    private javax.swing.JTextArea zoneLog;
    // End of variables declaration//GEN-END:variables
}