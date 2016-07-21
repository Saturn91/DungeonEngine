package game.level;

import toolbox.SaveSystem;

public class LevelFileReader {
	private static SaveSystem saveSystem = new SaveSystem("res/levels/", "lvl");
	public static int mapWidth;
	public static int mapHeight;
	public static int[][] read(String fileName){
		int map[][] = new int[1][1];
				
		saveSystem.readFile("res/levels/"+ fileName);
		mapWidth = 0;
		mapHeight = 0;
		try {
			mapWidth = Integer.parseInt(saveSystem.loadPrefixLine("width:"));
			mapHeight = Integer.parseInt(saveSystem.loadPrefixLine("height:"));
		} catch (Exception e) {
			System.err.println("LevelFileReader: width or height is not a number!");
		}
		map = new int[mapWidth][mapHeight];
		for(int y = 0; y < mapHeight; y++){
			for(int x = 0; x < mapWidth; x++){
				String line = saveSystem.loadLine((mapHeight-1) - y+2);
				try {
					map[x][y] = Integer.parseInt(line.substring(x*5, x*5+5));
				} catch (Exception e) {
					System.err.println("LevelFileReader: error in line" + y + ": " + line.substring(x*4, x*4+4) + " is not a number!");
				}
				
			}
		}
		
		return map;
	}
}
