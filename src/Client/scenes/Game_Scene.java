package Client.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

import Client.Client;
import Client.Final_Scene;
import Client.scenes.game_scene.Game_Scene_key_status;
import Client.scenes.game_scene.key_status_normal;
import Common.GameData;
import Common.GameDataSingleton;
import Client.Sounds.*;

public class Game_Scene extends JFrame implements Runnable, KeyListener{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	Thread thread;
    boolean timerOn;

	private MyDrawPanel drawPanel;
	
	private int player;
	private JPanel topPanel;
	private JLabel l_score;
	private JLabel l_time;
	private Boolean winner;
	
	private Common.GameData gameData;
	
	private Game_Scene_key_status key_status;
	
	private Boolean[] soundEffects;
	
	public Game_Scene (int player){
		timerOn=true;
		thread=new Thread(this);
		gameData= GameDataSingleton.getInstance();
		winner=false;
		this.player=player;
		drawPanel = new MyDrawPanel();
		
		key_status = new key_status_normal(this);
		
		
		try {
			gameData=Client.stub.getGameData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initializeComponents();			
		thread.start();
		//bucle Game_Scene
		while(!winner){
			try {
				Game_Scene_run();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public JLabel getL_score() {
		return l_score;
	}
	public void setL_score(JLabel l_score) {
		this.l_score = l_score;
	}
	public JLabel getL_time() {
		return l_time;
	}
	public void setL_time(JLabel l_time) {
		this.l_time = l_time;
	}
	public Game_Scene_key_status getKey_status() {
		return key_status;
	}
	public void setKey_status(Game_Scene_key_status key_status) {
		this.key_status = key_status;
	}



	private void updateGameData() {		
		try {
			gameData=Client.stub.getGameData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void Game_Scene_run() throws RemoteException {
		long time1=System.currentTimeMillis();
		//----------------------------------------//
		//soundEffects();
		Client.stub.gameEngineTick(player);		
		updateGameData();
		//----------------------------------------//
		l_score.setText("Score: "+gameData.getScore()[0]+" - "+gameData.getScore()[1]+"   Speed = "+gameData.getSpeed() );
		drawPanel.repaint();
		//----------Hay_Ganador?--------------------//
		if(Client.stub.winner()!=0){
			new Final_Scene(Client.stub.winner());
			this.dispose();
			this.winner=true;
			this.timerOn=false;
		}
		
		long time2 = System.currentTimeMillis();
		if(time2-time1<25)
			try {
				Thread.sleep(50-(time2-time1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	/*private void soundEffects() {
		try {
			soundEffects=Client.stub.getSoundEffects();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(soundEffects[0]==true)
			SoundEffect.RACKET.play();
		if(soundEffects[1]==true)
			SoundEffect.WALL.play();
		if(soundEffects[2]==true)
			SoundEffect.GOAL.play();
		
	}*/



	private void initializeComponents() {
		topPanel=new JPanel();
		l_score= new JLabel("Score: ");
		l_time=new JLabel("");
		
		this.addKeyListener(this);
		
		topPanel.add(l_score);
		topPanel.add(l_time);
		
		this.getContentPane().add(drawPanel);
		this.getContentPane().add(BorderLayout.NORTH,topPanel);
		this.setVisible(true);
		this.setSize(Common.GameData.frameWidth,Common.GameData.frameHeigth);
		this.setResizable(false);
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
	


	class MyDrawPanel extends JPanel
	  {
	     /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
	         g.setColor(Color.BLACK);
	         g.fillRect(0,0,this.getWidth(),this.getHeight());
	         
	         g.setColor(Color.WHITE);
	         g.fillOval(gameData.getPosBallx(),gameData.getPosBally(),Common.GameData.ballSize,Common.GameData.ballSize); 
	         
	         g.setColor(Color.red);
	         g.fillRect(10, gameData.getPos(1), Common.GameData.racketWidth, Common.GameData.racketHeigth);
	         
	         g.setColor(Color.blue);
	         g.fillRect(this.getWidth()-30, gameData.getPos(2), Common.GameData.racketWidth, Common.GameData.racketHeigth);
	     }   
	  }



	@Override
	public void keyTyped(KeyEvent e) {
		key_status.keyTyped(e);
		drawPanel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		 Integer minutos = 0 , segundos = 0, milesimas = 0;
	     
	     String min="", seg="", mil="";
	     try{	            
	    	 while( timerOn ){
	    		 Thread.sleep( 4 );	    		
	    		 milesimas += 4;	                 
	    		
	    		 if( milesimas == 1000 ){	                
	    			 milesimas = 0;
	    			 segundos += 1;	    			
	    			 if( segundos == 60 ){	                    
	    				 segundos = 0;
	    				 minutos++;
	    			 }
	    		 }
	    		
	    		 if( minutos < 10 ) min = "0" + minutos;
	    		 else min = minutos.toString();
	    		 if( segundos < 10 ) seg = "0" + segundos;
	    		 else seg = segundos.toString();
	                 
	    		 if( milesimas < 10 ) mil = "00" + milesimas;
	    		 else if( milesimas < 100 ) mil = "0" + milesimas;
	    		 else mil = milesimas.toString();	                 
	    		
	    		 l_time.setText("Time: "+ min + ":" + seg + ":" + mil );                
	    	 }
	     }catch(Exception e){}
	   
	     l_time.setText( "Time: 00:00:000" );
		
	}
}
