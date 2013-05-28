package Client.scenes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.Client;


public class Scene_1 extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private MyDrawPanel drawPanel;
	private static final long serialVersionUID = 1L;
	private RunScene_1 run1;
	private JButton b_connect;
	private Image backgroundImage;
	private JTextField textField;
	private JTextField textField_port;
	private JPanel mainPanel;
	private JPanel ipPanel;
	private JLabel ipLabel;
	
	public Scene_1() throws InterruptedException, IOException{
		System.out.println("Scene_1");
		drawPanel=new MyDrawPanel();
		initializeComponents();
		run1 = new RunScene_1();		
		
	}
	
	private void initializeComponents() throws IOException{
		b_connect = new JButton ("CONNECT");
		b_connect.addActionListener(this);		
		backgroundImage = ImageIO.read(new File("scene1.jpg"));
		textField=new JTextField("127.0.0.1");
		textField.setSize(100, 10);
		textField_port=new JTextField("1099");
		textField_port.setSize(30, 10);
		mainPanel=new JPanel();
		ipPanel = new JPanel();
		ipLabel = new JLabel("IP ADRESS: ");
		
		//ipPanel.setLayout(new BorderLayout());
		ipPanel.add(ipLabel);
		ipPanel.add(textField);		
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(b_connect,BorderLayout.SOUTH);
		mainPanel.add(ipPanel,BorderLayout.NORTH);
		
		this.getContentPane().add(drawPanel);
		this.setSize(315, 264);		
		this.getContentPane().add(BorderLayout.SOUTH,mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		// Determine the new location of the window
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		 
		// Move the window
		this.setLocation(x, y);
		drawPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.b_connect.setEnabled(false);
		
		if(!Client.Connect(textField.getText(), textField_port.getText()))
			this.b_connect.setEnabled(true);
		
		try {
			backgroundImage = ImageIO.read(new File("scene1+loading.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		drawPanel.repaint();
		
		run1.start();	
		
	}
	/*public void paintComponent(Graphics g){
		super.paintComponents(g);
		
		g.drawImage(backgroundImage, 0, 0, null);
	}*/
	class MyDrawPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g){			
			g.drawImage(backgroundImage, 0, 0, null);
			
	     }   
	  }
	
}
