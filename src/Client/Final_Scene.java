package Client;

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
import javax.swing.JPanel;

public class Final_Scene extends JFrame implements ActionListener{
	
	private MyDrawPanel drawPanel;
	private JButton b_exit;
	private Image backgroundImage;
	
	
	public Final_Scene(int winner){
		
		drawPanel= new MyDrawPanel();
		initializeComponents();
		
		if(winner==1){
			try {
				backgroundImage = ImageIO.read(new File("scene3+red.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(winner==2){
			try {
				backgroundImage = ImageIO.read(new File("scene3+blue.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else new Common.PopUpWindow("Error al decidir ganador");
		drawPanel.repaint();
	}

	private void initializeComponents() {
		b_exit = new JButton("Exit");
		
		this.getContentPane().add(drawPanel);
		this.setVisible(true);		
		this.setSize(315, 264);		
		this.getContentPane().add(BorderLayout.SOUTH,b_exit);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		// Determine the new location of the window
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		 
		// Move the window
		this.setLocation(x, y);
		
	}
	class MyDrawPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){			
			g.drawImage(backgroundImage, 0, 0, null);
			
	     }   
	  }
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}
}
