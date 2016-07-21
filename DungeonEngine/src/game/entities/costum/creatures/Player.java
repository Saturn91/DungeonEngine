package game.entities.costum.creatures;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import display.renderer.Renderer;
import toolbox.Vector;
import game.Game;
import game.controller.Controler;
import game.entities.costum.GameObject;
import game.entities.costum.tileset.TileSet;

public class Player{
	private Vector2f position;
	private float speed = 7f;
	
	private Vector2f[] hitbox = {
		new Vector2f(-0.25f, 0.25f),
		new Vector2f(-0.25f, -0.25f),
		new Vector2f(0.25f, -0.25f),
		new Vector2f(0.25f, 0.25f)			
	};
	private Controler controler = new Controler();
	private TileSet playerTileSet;
	private static final String NAME = "Player";
	private Animation walkAnimation;
	private GameObject object;

	public Player(Vector2f position) {
		this.position = position;
		playerTileSet = new TileSet(NAME, "Graphics/Player/player", 32, 32);
		walkAnimation = new Animation(playerTileSet);
		walkAnimation.setLayer(3);
		walkAnimation.addFrame(0, 0, position, 125);
		walkAnimation.addFrame(1, 0, position, 125);
		walkAnimation.addFrame(2, 0, position, 125);
		walkAnimation.addFrame(3, 0, position, 125);
		object = walkAnimation.getActualFrame(position);
		controler = new Controler();
		controler.addCommand("up", Keyboard.KEY_W);
		controler.addCommand("right", Keyboard.KEY_D);
		controler.addCommand("down", Keyboard.KEY_S);
		controler.addCommand("left", Keyboard.KEY_A);
	}
	
	public void tick(long delta){
		updatePosition(delta);
		updateAnimation();
		updateHitbox();
	}
	
	public void updateAnimation(){
		walkAnimation.tick();
		object = walkAnimation.getActualFrame(position);
	}
	
	private void updatePosition(long delta){
		controler.tick();
		if(controler.isActive("up")){
			goTo(0, delta);
		}
		
		if(controler.isActive("right")){
			goTo(1, delta);
		}
		
		if(controler.isActive("down")){
			goTo(2, delta);
		}
		
		if(controler.isActive("left")){
			goTo(3, delta);
		}
		object.setPosition(position);
	}
	
	private static final Vector2f direction[] = {
		new Vector2f(0,1),
		new Vector2f(1,0),
		new Vector2f(0,-1),
		new Vector2f(-1,0)
	};
	/**
	 * 0 = up, 1 = right, 2 = down, 3 = left
	 * @param direction
	 */
	private void goTo(int goTo, long delta){
		
		position.x += (delta*speed*Renderer.getZoom()/10000)*direction[goTo].x;
		position.y += (delta*speed*Renderer.getZoom()/10000)*direction[goTo].y;
		
	}
	
	public GameObject getGameObject(){
		return object;
	}
	
	private Vector2f movedHitbox[] = new Vector2f[4];
	/**
	 * @return float[0] = left up, ... float[3] = right up, counterclockwise
	 */
	private void updateHitbox(){
		for(int i = 0; i < 4; i++){
			movedHitbox[i] = Vector.add(object.getPosition(), hitbox[i]);
		}		
	}
	
	public Vector2f[] getHitbox(){
		return movedHitbox;
	}
	
}
