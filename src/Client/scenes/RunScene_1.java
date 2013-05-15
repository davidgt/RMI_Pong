package Client.scenes;

import java.rmi.RemoteException;

import Client.Client;


public class RunScene_1 extends Thread{
	public int player;
	
	@Override
	public void run(){			
		Boolean exit=false;
		try {
			player =Client.stub.getPlayerNumber();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}		
		while(!exit){
			long time1=System.currentTimeMillis();
//--------------------------------------------------------//
			
			try {
				if(Client.stub.isGameReady()){
					
					System.out.println("ESCENA_2 Player:"+player);
					exit=true;
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//-------------------------------------------------------//			
			long time2 = System.currentTimeMillis();
			if(time2-time1<25)
				try {
					Thread.sleep(100-(time2-time1));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("Bucle...1");			
		}
		
		new Game_Scene(player);		
		
	}
}
