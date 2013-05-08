package Common;

public class GameDataSingleton {
	private static GameData GD;
	
	private GameDataSingleton(){}
	
	public static GameData getInstance () {
		if(GD == null){
			GD = new GameData();
		}
		
		return GD;
	}
}
