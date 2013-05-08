package Client.scenes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.Client;
import Client.Main;


public class Scene_1 extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private MyDrawPanel drawPanel;
	private static final long serialVersionUID = 1L;
	private RunScene_1 run1;
	private JButton b_connect;
	private Image backgroundImage;
	
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
		
		this.getContentPane().add(drawPanel);
		this.setSize(315, 264);		
		this.getContentPane().add(BorderLayout.SOUTH,b_connect);
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
		Client.Connect(Main.host);
		
		try {
			backgroundImage = ImageIO.read(new File("scene1+loading.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.b_connect.setEnabled(false);
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

		public void paintComponent(Graphics g){			
			g.drawImage(backgroundImage, 0, 0, null);
			
	     }   
	  }
	
}
