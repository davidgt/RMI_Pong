package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Common.ServerI;

public class Client {
	public static ServerI stub;
	public Client(){}
	
	public static Boolean Connect(String host) {		
		//System.setSecurityManager(new RMISecurityManager());
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			stub = (ServerI) registry.lookup("server");		    
			String response = stub.greet();			
			System.out.println("response: " + response);
			return true;
		} catch (Exception e) {
			new Common.PopUpWindow("Error al conectar con el servidor");
			System.err.println("Client exception: " + e.toString());			
			e.printStackTrace();
			return false;
		}
		
		
	}

	
}