
/**
 * Example of a Fenetre (JFrame) using awt and swing
 * @author Anthony
 */

import java.awt.BorderLayout;
import java.awt.event.* ;
import javax.swing.*;


public class Fenetre extends JFrame implements ActionListener{

	/** labels */
	private JLabel label1;
	private JLabel label2;

	/** a textfield for the name */
	private JTextField text;

	/** a button to perform an action: e.g. say hello (TBD) */
	private JButton button;


	/** Creates a Fenetre */
	public Fenetre(String name) {
		super(name) ; 
		initComponents();
	}


	@Override /** When we click on the button  */
	public void actionPerformed(ActionEvent e){
		this.remove(label2);
		label2 = new JLabel("Welcome to the Chat System "+text.getText()); 
		this.add("South",label2);
		this.pack();
	}


	/** Add an action when the key "Enter" is relased  */
	public void addActionforEnterKeyReleased() {
		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					remove(label2);
					label2 = new JLabel("Welcome to the Chat System "+text.getText()); 
					add("South",label2);
					pack(); 
				}
			}
		});
	}


	/** Initializes the Fenetre components */
	private void initComponents() {
		// create the components
		
		label1 = new JLabel("Please provide a name : ");
		label2= new JLabel();
		
		// a new text field with 20 columns
		text = new JTextField(20);
		addActionforEnterKeyReleased() ; 
		
		// a new button identified as OK
		button = new JButton("OK");
		button.addActionListener(this);

		// configures the JFrame layout using a border layout
		this.setLayout(new BorderLayout());
		
		// places the components in the layout
		this.add("West",label1);
		this.add("Center",text);
		this.add("East",button);
		
		// packs the fenetre: size is calculated
		// regarding the added components
		this.pack();
		
		// the JFrame is visible now
		this.setVisible(true);

		// when we click on the close button, the system exits
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/** main entry point */
	public static void main(String[] args) {
		Fenetre f = new Fenetre("Chat System v0.1");
	}

}