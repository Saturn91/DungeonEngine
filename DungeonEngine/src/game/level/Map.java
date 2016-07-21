package game.level;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import shaders.StaticShader;
import display.renderer.Loader;
import display.renderer.Renderer;
import game.entities.costum.GameObject;
import game.entities.costum.creatures.Player;
import game.entities.costum.things.Torch;
import game.entities.costum.tileset.TileSet;
import game.entities.light.LightEngine;
import game.entities.standart.Camera;

public class Map {
	private Player player;
	private Level level;
	private LightEngine lightEngine;
	public Map() {
		TileSet tileset = new TileSet("standartTileset", "Graphics/TestTileSet", 32, 32);
		lightEngine = new LightEngine(25);
		Torch torch = new Torch(new Vector2f(2, 3));
		level = LevelInterpreter.generateLevel("testLevel", tileset);
		LightEngine.addLight(torch.getLight());
		player = new Player(new Vector2f(1, 2));
		Camera.bindTo(player.getGameObject());
	}
	
	public void tick(long delta){
		player.tick(delta);
	}
	
	
	public void render(Renderer renderer, StaticShader shader){
		//render Player
		renderer.render(player.getGameObject(), shader);
		
		//render Level
		level.render(renderer, shader);
	}
	
	public void cleanUp(){
		Loader.cleanUp();
	}
}
