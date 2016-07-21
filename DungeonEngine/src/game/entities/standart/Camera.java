package game.entities.standart;

import game.controller.Controler;
import game.entities.costum.GameObject;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import display.renderer.Renderer;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;	//High of Camera
	private float yaw;		//aiming left an d right
	private Controler controler;
	private static boolean boundTo = false;
	private static GameObject object;
	
	public Camera() {
		controler = new Controler();
	}
	
	public void tick(long delta){
		if(!boundTo){
			/*controler.tick(delta);
			position.x = controler.getPosition().x;
			position.y = controler.getPosition().y;*/
		}else{
			position.x = object.getPosition().x;
			position.y = object.getPosition().y;
		}		
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}
	
	public void setZoom(float zoom){
		position.z = zoom;
	}
	
	public static void bindTo(GameObject tobject){
		boundTo = true;
		object = tobject;
	}
	
	public static void unbind(){
		boundTo = false;
		object = null;
	}
}
