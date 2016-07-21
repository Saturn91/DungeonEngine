package game.entities.costum.block;

import org.lwjgl.util.vector.Vector2f;

public class Block {
	private Vector2f position;
	private float size;
	private Vector2f hitboxes[][];
	
	public Block(Vector2f position, float size) {
		this.position = position;
		this.size = size;
		hitboxes = new Vector2f[2][2];
		setHitbox();
	}
	
	public boolean intersects(Vector2f position){
		return(	position.x >= hitboxes[0][1].x &&
			position.x <= hitboxes[1][1].x &&
			position.y <= hitboxes[0][1].y &&
			position.y >= hitboxes[1][0].y);
	}
	
	public void setPosition(Vector2f position){
		this.position = position;
		setHitbox();
	}
	
	public void setSize(float size){
		this.size = size;
		setHitbox();
	}
	
	public boolean isInRange(Vector2f position, float distance){
		float mesureDistance = new Vector2f(this.position.x - position.x,
												this.position.y - position.y).length();
		return mesureDistance <= distance;
	}
	
	private void setHitbox(){
		hitboxes[0][0] = new Vector2f(-size*0.5f+position.x, -size*0.5f+position.y);	//downLeft
		hitboxes[1][0] = new Vector2f(size*0.5f+position.x, -size*0.5f+position.y);		//downRight
		hitboxes[1][1] = new Vector2f(size*0.5f+position.x, size*0.5f+position.y);		//upRight
		hitboxes[0][1] = new Vector2f(-size*0.5f+position.x, size*0.5f+position.y);		//upLeft
	}
	
}
