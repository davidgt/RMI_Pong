package Common;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerI extends Remote{
	
	String greet ()throws RemoteException;
	Boolean isGameReady() throws RemoteException;
	int getPlayerNumber() throws RemoteException;	
	void setPlayerPos(int player,int pos) throws RemoteException;
	int getPlayerPos (int player) throws RemoteException;
	int winner() throws RemoteException;
	
	//Boolean[] getSoundEffects() throws RemoteException;
	
	GameData getGameData() throws RemoteException;	
    
    void gameEngineTick(int player) throws RemoteException;
}
