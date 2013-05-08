package Server;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Igu extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	private List list;
	private JLabel label_status;
	private JButton b_connect,b_discon;
	private JPanel panel_top;
	
	

	public Igu (String nom){
		super(nom);
		initializeComponents();
		
		
	}

	private void initializeComponents() {
		list=new List();
		label_status=new JLabel("Servidor Apagado");
		b_connect=new JButton("Connect Server");
		b_connect.addActionListener(this);
		b_discon=new JButton("Disconnect Server");
		b_discon.addActionListener(this);
		panel_top = new JPanel();
		
		panel_top.add(b_connect);
		panel_top.add(b_discon);
		this.getContentPane().add(BorderLayout.SOUTH,label_status);
		this.getContentPane().add(list);		
		this.getContentPane().add(BorderLayout.NORTH,panel_top);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b_connect){			
			Connect();
		}
		else Disconnect();
		
	}

	private void Disconnect() {
		System.out.println("Discon");
		
	}

	private void Connect() {
		System.out.println("Connect");
		Server.conectar();
		label_status.setText("Server Connected");
	}

}
