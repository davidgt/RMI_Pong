package Server;

import java.awt.BorderLayout;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Igu extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	private JLabel label_status;
	private JButton b_connect,b_discon;
	private JPanel panel_top;
	private java.awt.TextArea textArea;
	
	private int port;

	public Igu (String nom){
		super(nom);
		initializeComponents();
		showIps();
	}

	private void showIps() {
		//IP ADRESS
    	String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    String add=iface.getDisplayName() + " " + ip;
                    System.out.println(add);
                    textArea.setText(textArea.getText()+ip+"\n");
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        //END IP ADRES
		
	}

	private void initializeComponents() {
		textArea = new java.awt.TextArea();
		label_status=new JLabel("Servidor Apagado");
		b_connect=new JButton("Connect Server");
		b_connect.addActionListener(this);
		b_discon=new JButton("Disconnect Server");
		b_discon.addActionListener(this);
		panel_top = new JPanel();
		
		panel_top.add(b_connect);
		panel_top.add(b_discon);
		this.getContentPane().add(BorderLayout.SOUTH,label_status);
		this.getContentPane().add(textArea);		
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
		if(Server.desconectar(port)){
			System.out.println("---- SERVER OFF ----");
	        textArea.setText(textArea.getText()+"---- SERVER OFF ----\n");

		} else {
			System.out.println("---- Error al apagar el servidor ----");
	        textArea.setText(textArea.getText()+"---- Error al apagar el servidor ----\n");
		}
	}

	private void Connect() {
		System.out.println("Connect");
		port = Server.conectar(this);
		while(port <= 0){
			port = Server.conectar(this);
			System.out.println("---- CANNOT USE PORT: "+String.valueOf(port)+" ----");
	        textArea.setText(textArea.getText()+"---- CANNOT USE PORT: "+String.valueOf(port)+" ----\n");
		}
		label_status.setText("Server Connected at port: " + String.valueOf(port));
		System.out.println("---- SERVER CONECTED: "+String.valueOf(port)+" ----");
        textArea.setText(textArea.getText()+"---- SERVER CONECTED: "+String.valueOf(port)+" ----\n");
	}
	
	public void showGameStatus(Common.GameData gameData){
		textArea.setText(textArea.getText()+"---- IP ----\n");
		showIps();
		textArea.setText(textArea.getText()+"---- Juego: ----\n");
		textArea.setText(String.valueOf(gameData.getScore()[0])+" - "+ String.valueOf(gameData.getScore()[1]));
		
	}

}
