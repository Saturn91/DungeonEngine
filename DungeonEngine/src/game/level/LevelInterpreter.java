package game.level;

import org.lwjgl.util.vector.Vector2f;

import game.Game;
import game.entities.costum.GameObject;
import game.entities.costum.block.Block;
import game.entities.costum.tileset.TileSet;

public class LevelInterpreter {
	public static void generateLevel(int[][] mapID, int mapWidth, int mapHeight, TileSet tileset){
		try {
			int test = mapID[mapWidth-1][mapHeight-1];
			Game.deleteGameObjects();
			for(int x = 0; x < mapWidth; x++){
				for(int y = 0; y < mapHeight; y++){
					int adress = mapID[x][y];
					if(adress != -1){
						int numInY = adress%100;
						int numInX = (adress-numInY)/100;
						Game.addGameObject(tileset.getTile(numInX, numInY, new Vector2f(x,y), 1, 0));
					}					
				}
			}
		} catch (Exception e) {
			System.err.println("LevelInterpreter wrong Parameters Mapwidth/height");
		}
	}
	
	public static void generateLevelWithoutOverriting(int[][] mapID, int mapWidth, int mapHeight, TileSet tileset){
		try {
			int test = mapID[mapWidth-1][mapHeight-1];
			for(int x = 0; x < mapWidth; x++){
				for(int y = 0; y < mapHeight; y++){
					int adress = mapID[x][y];
					if(adress != -1){
						int numInY = adress%100;
						int numInX = (adress-numInY)/100;
						Game.addGameObject(tileset.getTile(numInX, numInY, new Vector2f(x,y), 1, 0));
					}
				}
			}
		} catch (Exception e) {
			System.err.println("LevelInterpreter wrong Parameters Mapwidth/height");
		}
	}
	
	public static Level generateLevel(String filePath,  TileSet tileset){
		int mapID[][] = LevelFileReader.read(filePath);
		int mapWidth = LevelFileReader.mapWidth;
		int mapHeight = LevelFileReader.mapHeight;
		Level level = new Level();
		try {
			int test = mapID[mapWidth-1][mapHeight-1];
			Game.deleteGameObjects();
			for(int x = 0; x < mapWidth; x++){
				for(int y = 0; y < mapHeight; y++){
					int adress = mapID[x][y];
					if(adress != -1){
						int numInY = adress%100;
						int numInX = (adress-numInY)/100;
						Vector2f position = new Vector2f(x,y);
						level.addObject(tileset.getTile(numInX, numInY, position, 1, 0));
						level.addBlock(new Block(position, 1));
					}					
				}
			}
		} catch (Exception e) {
			System.err.println("LevelInterpreter wrong Parameters Mapwidth/height");
		}
		
		return level;
	}
}
