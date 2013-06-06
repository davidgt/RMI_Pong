package Server;

//import java.rmi.NotBoundException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//import javax.xml.bind.ParseConversionEvent;

import Common.GameData;
import Common.GameDataSingleton;
import Common.ServerI;


	
public class Server implements ServerI {
	
	private int playerNumber;
	private GameData gameData;
	private int winner;	
	private int[] score;
	private int timer;
	private Igu GUI;
	
    public Server(Igu gui) {
    	this.playerNumber=0;
    	this.gameData= GameDataSingleton.getInstance();
    	this.winner=0;
    	this.timer=0;
    	score=new int[2];
    	this.GUI = gui;
    	/*soundEffects=new Boolean[3];
    	for(int i=0;i<soundEffects.length;i++){
			soundEffects[i]=false;
		}*/
    }

    public static int conectar(Igu gui) {
    	int port = ((int) (Math.random() * 1000)) + 2000; 
    	try {
    	    Server obj = new Server(gui);
    	    ServerI stub = (ServerI) UnicastRemoteObject.exportObject(obj,port);
    	    
    	    // Bind the remote object's stub in the registry
    	    Registry registry = LocateRegistry.createRegistry(port);
    	    //Registry registry = LocateRegistry.getRegistry();
    	    registry.rebind("server", stub);
    	    
    	    System.err.println("Server ready");
    	    return port;
    	} catch (Exception e) {
    	    System.err.println("Server exception: " + e.toString());
    	    e.printStackTrace();
    	}
    	return -1;
	}
    
    public static boolean desconectar(int port){
    	try { 
    	    Registry registry = LocateRegistry.getRegistry(port);
            registry.unbind("server");
            Thread.sleep(1000);
            return true;
            //UnicastRemoteObject.unexportObject(this, true);
    	} catch (NotBoundException e) {
            System.err.println("Error shutting down the server - could not unbind the registry"+ e.getMessage());
        } catch (InterruptedException e) {
        	System.err.println("Unable to sleep when shutting down the server"+ e.getMessage());
        } catch (AccessException e) {
        	System.err.println("Access Exception"+ e.getMessage());
        } catch (UnmarshalException e) {
            System.err.println("UnMarshall Exception"+ e.getMessage());
        } catch (RemoteException e) {
        	System.err.println("Remote Exception"+ e.getMessage());
        }
	return false;
    }

	@Override
	public String greet() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello, im the Server";
	}

	@Override
	public int getPlayerNumber() throws RemoteException {		
		playerNumber++;
		if(playerNumber>2)
			System.out.println("La estamos liando con el playerNumber");
		return playerNumber;
	}

	@Override
	public GameData getGameData() throws RemoteException {
		// TODO Auto-generated method stub
		return this.gameData;
	}

	public GameData getGD(){
		return this.gameData;
	}
	
	
	@Override
	public void setPlayerPos(int player, int pos) throws RemoteException {
		if(player==1)
			gameData.setPos(1, pos);
		if(player==2)
			gameData.setPos(2, pos);		
	}

	@Override
	public int winner() throws RemoteException {
		// TODO Auto-generated method stub
		return winner;
	}

	@Override
	public Boolean isGameReady() throws RemoteException {
		if(this.playerNumber==2)
			return true;
		else return false;
	}
	
	@Override
	public void gameEngineTick(int player) throws RemoteException {
		if(player==1){	
			//Colision techo
			colision_top();
			//Colision suelo
			colision_bottom();
			//Colisón derecha
			colision_right();
			//Colisión izquierda
			colision_left();
			
			//Colisón paleta jugador 2
			if(colisionPlayer2()){				gameData.setPosX_sig(false); 	}
			//Colisón paleta jugador 1
			if(colisionPlayer1()){				gameData.setPosX_sig(true); 	}
			
			//--------------Ball-Move--------------------------//
			move_ball();
			//-------------Score Engine-----------------//
			if(score[0]>=5)				this.winner=1;
			if(score[1]>=5)				this.winner=2;
				
			//------------------SpeedIncrease--------------//
			if(timer>900){
				gameData.setSpeed(gameData.getSpeed()+2);
				timer=0;
			}
			timer++;
		}	
	}

	public void colision_top(){
		if(gameData.getPosBally() < 0){
			gameData.setPosY_sig(true);
			gameData.setPosBally(0);
		}
	}
	public void colision_bottom(){
		if(gameData.getPosBally() > GameData.frameHeigth+(GameData.ballSize)-102){
			gameData.setPosY_sig(false);
			gameData.setPosBally(GameData.frameHeigth+(GameData.ballSize)-102);
		}
	}
	public void colision_left(){
		if(gameData.getPosBallx()<0){
			score=gameData.getScore();
			score[1]++;
			this.GUI.showGameStatus(this.gameData);
			gameData.setScore(score);
			gameData.resetGame();
		}
	}
	public void colision_right(){
		if(gameData.getPosBallx()>GameData.frameWidth){
			score=gameData.getScore();
			score[0]++;
			this.GUI.showGameStatus(this.gameData);
			gameData.setScore(score);
			gameData.resetGame();
		}
		
	}
	
	public void move_ball(){
		if(gameData.getX_sig() && gameData.getY_sig()){
			gameData.setPosBallx(gameData.getPosBallx()+gameData.getSpeed());
			gameData.setPosBally(gameData.getPosBally()+gameData.getSpeed());
		}
		if(!gameData.getX_sig() && gameData.getY_sig()){
			gameData.setPosBallx(gameData.getPosBallx()-gameData.getSpeed());
			gameData.setPosBally(gameData.getPosBally()+gameData.getSpeed());
		}
		if(gameData.getX_sig() && !gameData.getY_sig()){
			gameData.setPosBallx(gameData.getPosBallx()+gameData.getSpeed());
			gameData.setPosBally(gameData.getPosBally()-gameData.getSpeed());
		}
		if(!gameData.getX_sig() && !gameData.getY_sig()){
			gameData.setPosBallx(gameData.getPosBallx()-gameData.getSpeed());
			gameData.setPosBally(gameData.getPosBally()-gameData.getSpeed());
		}
	}
	
	private boolean colisionPlayer1() {		
		if(gameData.getPosBallx() > 10 && gameData.getPosBallx() < 10 + GameData.racketWidth){
			if(gameData.getPosBally() < gameData.getPos(1)+GameData.racketHeigth && gameData.getPosBally() > gameData.getPos(1))
				return true;
			if(gameData.getPosBally()+GameData.ballSize < gameData.getPos(1)+GameData.racketHeigth && gameData.getPosBally()+GameData.ballSize > gameData.getPos(1))
				return true;
		}
		return false;
	}

	private boolean colisionPlayer2() {
		if(gameData.getPosBallx()+GameData.ballSize > GameData.frameWidth-30 && gameData.getPosBallx()+GameData.ballSize < GameData.frameWidth - 10){
			if(gameData.getPosBally() < gameData.getPos(2)+GameData.racketHeigth && gameData.getPosBally() > gameData.getPos(2))
				return true;
			if(gameData.getPosBally()+GameData.ballSize < gameData.getPos(2)+GameData.racketHeigth && gameData.getPosBally()+GameData.ballSize > gameData.getPos(2))
				return true;
		}
		return false;
	}

	@Override
	public int getPlayerPos(int player) throws RemoteException {
		return gameData.getPos(player);
	}

	/*@Override
	public Boolean[] getSoundEffects() throws RemoteException {
		// TODO Auto-generated method stub
		return this.soundEffects;
	}*/
}