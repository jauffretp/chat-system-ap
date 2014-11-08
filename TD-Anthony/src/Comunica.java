import java.awt.* ; 
import java.awt.event.* ;
import java.io.*  ; 

import javax.swing.* ; 

/**
 * Comunica Class : IHM for TD3
 * @author Anthony
 */


public class Comunica extends JFrame implements ActionListener{

	private BufferedReader reader ; 
	private BufferedWriter writer ; 
	private JButton bReceive ; 
	private JButton bSend ; 
	private JLabel lmessrec ; 
	private JLabel lmesssend ;
	private JTextArea textRec ; 
	private JTextArea textToSend ;
	private JScrollPane zoneScrolableSend ; 
	private JScrollPane zoneScrolableRec ; 


	public Comunica( BufferedReader reader, BufferedWriter writer ) throws IOException {
		super("Communication Window");	
		this.writer = writer ;
		this.reader = reader ; 
		initComponents() ;			
		this.setSize(500, 200); // redimensionne bien la fen�tre
		this.setLocationRelativeTo(null); // et la met au milieu
	}


	/** Initializes the Fenetre components */
	private void initComponents() {
		// create the components

		// the labels
		lmessrec = new JLabel("Received message : ");
		lmesssend = new JLabel("Message to send : ");

		// 2 new text fields
		textToSend = new JTextArea(50,50) ;
		zoneScrolableSend=new JScrollPane(textToSend);		
		//
		textRec = new JTextArea(50,50) ;
		textRec.setEditable(false) ; 
		zoneScrolableRec=new JScrollPane(textRec);


		// buttons : creation and association with ActionListener
		bReceive = new JButton("Receive");
		bReceive.addActionListener(this);		
		bSend = new JButton("Send");
		bSend.addActionListener(this);

		// configures the JFrame layout using a border layout
		this.setLayout(new GridLayout(3,2));

		// places the components in the layout
		this.getContentPane().add(lmesssend);
		this.getContentPane().add(zoneScrolableSend);
		this.getContentPane().add(bSend);
		this.getContentPane().add(bReceive);
		this.getContentPane().add(lmessrec);
		this.getContentPane().add(zoneScrolableRec);

		addActionforEnterKeyReleased() ; 
		
		// packs the fenetre: size is calculated
		// regarding the added components
		this.pack();

		// the JFrame is visible now
		this.setVisible(true);

		// when we click on the close button, the system exits
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/** Add an action when the key "Enter" is relased  */
	public void addActionforEnterKeyReleased() {
		textToSend.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					bSend.doClick(); 
				}
			}
		});
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e){

		if (e.getSource() == bReceive){ // appuie sur bouton "Receive"
			try {
				textRec.setText(reader.readLine());
			} catch (IOException exception) {
				System.err.println("Erreur lecture ! "); 
				exception.printStackTrace(); 
			}
		}
		
		else if (e.getSource() == bSend){ // appuie sur bouton "Send"
			try {
				writer.write(textToSend.getText());
				writer.flush() ;
				textToSend.setText("");
			} 
			catch (Exception exception){
				System.err.println("Erreur ecriture ! "); 
				exception.printStackTrace(); 				
			}
		}
	}
}
