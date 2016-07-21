package audio;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;


public class AudioMaster {
	
	private static List<Integer> buffers = new ArrayList<Integer>();
	
	public static void init(){
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static int loadSound(String path){
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile;
		try {
			waveFile = WaveData.create(new BufferedInputStream(new FileInputStream("res/gfx/"+path))); ;
		} catch (Exception e) {
			waveFile = null;
		}
		
		if(waveFile != null){
			AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
			waveFile.dispose();
			return buffer;
		}else{
			System.err.println("AudioMaster: not able to load Sound: res/gfx/" + path);
			return 0;
		}
		
	}
	
	public static void setListener(float x, float y, float z){
		AL10.alListener3f(AL10.AL_POSITION, x, y, z);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static void cleanUp(){
		for(Integer buffer: buffers){
			AL10.alDeleteBuffers(buffer);
		}
		AL.destroy();
	}
}
