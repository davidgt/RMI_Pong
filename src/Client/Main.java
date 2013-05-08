package Client;

import java.io.IOException;

import Client.scenes.Scene_1;

public class Main {
	
	public static String host;
	
	public static void main (String[]args) throws InterruptedException{
		Scene_1 escene1;
		
		try {
			escene1 = new Scene_1();
			escene1.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		host=(args.length < 1) ? null : args[0];		
	}
}
