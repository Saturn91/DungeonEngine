package game.entities.costum.things;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import game.Game;
import game.entities.costum.creatures.Animation;
import game.entities.costum.tileset.TileSet;
import game.entities.light.Light;

public class Torch {
	private Animation torchAnimation;
	private Vector2f position;
	private Light light;
	
	public Torch(Vector2f position){
		this.position = position;
		light = new Light(position, new Vector3f(1, 0.75f, 0.25f));
		light.setStrenght(2.5f);
		torchAnimation = new Animation(new TileSet("Torch", "Graphics/Things/torchTileSet", 32, 32));
		torchAnimation.addFrame(0, 0, position, 150);
		torchAnimation.addFrame(1, 0, position, 150);
		torchAnimation.addFrame(0, 1, position, 150);
		torchAnimation.addFrame(1, 1, position, 150);
		Game.addAnimation(torchAnimation);
	}
	
	public Light getLight(){
		return light;
	}
}
