package Common;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUpWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	
	public PopUpWindow(String text){
		label=new JLabel(text);
		initializeComponents();
	}

	private void initializeComponents() {
		// TODO Auto-generated method stub
		this.add(label);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(500, 200);
		this.setVisible(true);
	}
}
