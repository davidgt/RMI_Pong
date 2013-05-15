package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import Common.GameData;
import Common.GameDataSingleton;
import Common.ServerI;


	
public class Server implements ServerI {
	
	private int playerNumber;
	private GameData gameData;
	private int winner;	
	private int[] score;
	private int timer;
	//private Boolean[] soundEffects;//[3]{racket,wall,goal}
	
	
    public Server() {
    	this.playerNumber=0;
    	this.gameData= GameDataSingleton.getInstance();
    	this.winner=0;
    	this.timer=0;
    	score=new int[2]; 
    	/*soundEffects=new Boolean[3];
    	for(int i=0;i<soundEffects.length;i++){
			soundEffects[i]=false;
		}*/
    }

    public static void conectar() {
    	
    	try {
    	    Server obj = new Server();
    	    ServerI stub = (ServerI) UnicastRemoteObject.exportObject(obj,0);

    	    // Bind the remote object's stub in the registry
    	    Registry registry = LocateRegistry.getRegistry();
    	    registry.rebind("server", stub);

    	    System.err.println("Server ready");
    	} catch (Exception e) {
    	    System.err.println("Server exception: " + e.toString());
    	    e.printStackTrace();
    	}
		
	}

	@Override
	public String greet() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello, im the Srver";
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
			/*for(int i=0;i<soundEffects.length;i++){
				soundEffects[i]=false;
			}*/
			
			//====================================GAME_Engine=========================================//
			//---------------Ball-Collisions-------------------//
			//---------------Ball_Frame-----------------------//
			//Colision techo
			if(gameData.getPosBally() < 0){
				gameData.setY_sig(true);
				//soundEffects[1]=true;
			}
			//Colision suelo
			if(gameData.getPosBally() > GameData.frameHeigth+(GameData.ballSize)-102){
				gameData.setY_sig(false);
				//soundEffects[1]=true;
			}
			
			//--------------Ball-Player-----------------------//
			if(colisionPlayer2()){
				gameData.setX_sig(false);
				//soundEffects[0]=true;
				System.out.println("Colision P2");
			}
			if(colisionPlayer1()){
				gameData.setX_sig(true);
				//soundEffects[0]=true;
				System.out.println("Colision P1");
			}
			//--------------Ball-Move--------------------------//
			if(gameData.getX_sig() && gameData.getY_sig()){
				gameData.setPosBallx(gameData.getPosBallx()+gameData.getSpeed());
				gameData.setPosBally(gameData.getPosBally()+gameData.getSpeed());
				System.out.println("1: "+gameData.getPosBallx()+" "+gameData.getPosBally());
			}
			if(!gameData.getX_sig() && gameData.getY_sig()){
				gameData.setPosBallx(gameData.getPosBallx()-gameData.getSpeed());
				gameData.setPosBally(gameData.getPosBally()+gameData.getSpeed());
				System.out.println("2: "+gameData.getPosBallx()+" "+gameData.getPosBally());
			}
			if(gameData.getX_sig() && !gameData.getY_sig()){
				gameData.setPosBallx(gameData.getPosBallx()+gameData.getSpeed());
				gameData.setPosBally(gameData.getPosBally()-gameData.getSpeed());
				System.out.println("3: "+gameData.getPosBallx()+" "+gameData.getPosBally());
			}
			if(!gameData.getX_sig() && !gameData.getY_sig()){
				gameData.setPosBallx(gameData.getPosBallx()-gameData.getSpeed());
				gameData.setPosBally(gameData.getPosBally()-gameData.getSpeed());
				System.out.println("4: "+gameData.getPosBallx()+" "+gameData.getPosBally());
			}
			//-------------Score Engine-----------------//
			score=gameData.getScore();
			if(gameData.getPosBallx()>GameData.frameWidth){
				score[0]++;
				gameData.resetGame();
				//soundEffects[2]=true;
			}
			if(gameData.getPosBallx()<0){
				score[1]++;	
				gameData.resetGame();
				//soundEffects[2]=true;
			}
			if(score[0]>=5)
				this.winner=1;
			if(score[1]>=5)
				this.winner=2;
				
			gameData.setScore(score);
			//------------------SpeedIncrease--------------//
			if(timer>900){
				gameData.setSpeed(gameData.getSpeed()+2);
				timer=0;
			}
			timer++;
			//===========================================================================================//	
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