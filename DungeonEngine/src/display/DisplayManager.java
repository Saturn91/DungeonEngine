package display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	private String gameTitle;
	private int width;
	private int height;
	private int sync = 120;
	
	private long lastTimeTickLine = 0;
	private long nowTime;
	private long delta = 0;
	private long longestDelta = 0;
	
	public DisplayManager(String gameTitle, int width, int height) {
		this.gameTitle = gameTitle;
		this.width = width;
		this.height = height;
		buildDisplay();
	}

	private void buildDisplay() {
		//Constructor takes Version of OpenGL, 
		//then we set forward compatible (for newer Versions true 
		//and an other setting...
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(gameTitle);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Say OpwnGL where in the Display it can render the content (vgl Canvas!)
		GL11.glViewport(0, 0, width, height);		
	}
	
	public long updateDisplay(){
		
		Display.sync(sync);
		Display.update();
		return delta;
	}
	
	public void closeDisplay(){
		Display.destroy();
	}
	
	public void setDisplaySync(int sync){
		this.sync = sync;
	}
}
