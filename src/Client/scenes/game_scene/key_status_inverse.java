package Client.scenes.game_scene;

import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import Client.Client;
import Client.scenes.Game_Scene;
import Common.GameData;

public class key_status_inverse implements Game_Scene_key_status {
	
	private Game_Scene GS;
	
	public key_status_inverse (Game_Scene GS){
		this.GS = GS;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		try {
			GameData gameData=Client.stub.getGameData();
			if(GS.getPlayer() == 1 || GS.getPlayer() == 2){
				if(e.getKeyChar()=='a'){
					if(gameData.getPos(GS.getPlayer())>0)
						Client.stub.setPlayerPos(GS.getPlayer(), Client.stub.getPlayerPos(GS.getPlayer())-5);	
				}
				if(e.getKeyChar()=='q'){
					if(gameData.getPos(GS.getPlayer())<500-GameData.racketHeigth)
						Client.stub.setPlayerPos(GS.getPlayer(), Client.stub.getPlayerPos(GS.getPlayer())+5);
				}
				if(e.getKeyChar()=='i'){
					System.err.println("He apretado la i madafaka!");
					this.GS.setKey_status(new key_status_normal(this.GS));
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
