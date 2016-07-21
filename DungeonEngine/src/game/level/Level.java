package game.level;

import game.entities.costum.GameObject;
import game.entities.costum.block.Block;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import shaders.StaticShader;
import display.renderer.Renderer;

public class Level {
	private ArrayList<Block> blocks;
	private ArrayList<GameObject> objects;
	
	public Level() {
		objects = new ArrayList<>();
		blocks = new ArrayList<>();
	}
	int i = 0;
	public void addBlock(Block block){
		blocks.add(block);
	}
	
	public void addObject(GameObject object){
		objects.add(object);
	}
	
	public boolean intersects(Vector2f position, float distance){
		for(Block b: blocks){
			if (b.isInRange(position, distance)){
				if(b.intersects(position)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean intersects(Vector2f vertices[], float distance){
		for(Vector2f v: vertices){
			for(Block b: blocks){
				if (b.isInRange(v, distance)){
					if(b.intersects(v)){
						return true;
					}
				}
			}
		}		
		return false;
	}
	
	public void render(Renderer renderer, StaticShader shader){
		for(GameObject o: objects){
			renderer.render(o, shader);
		}
	}
}
