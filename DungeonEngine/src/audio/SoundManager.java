package audio;

import java.util.ArrayList;

public class SoundManager {
	private static ArrayList<Source> sources;
	private static ArrayList<String> gfxs;
	private static ArrayList<Integer> gfxsIDs;
	private static Source music;
	
	public static void init(){
		if(sources != null){
			AudioMaster.cleanUp();
		}
		sources = new ArrayList<>();
		gfxs = new ArrayList<>();
		gfxsIDs = new ArrayList<>();
		AudioMaster.init();
		AudioMaster.setListener(0, 0, 0);
		music = new Source();	
	}
	
	public static int addSource(){
		sources.add(new Source());
		return sources.size()-1;
	}
	
	public static Source getSource(int id){
		return sources.get(id);
	}
	
	public static void addSound(String name, String path){
		int buffer = AudioMaster.loadSound(path);
		if(buffer != 0){
			if(!gfxs.contains(name)){
				gfxs.add(name);
				gfxsIDs.add(buffer);
			}else{
				System.err.println("SoundManager: <" + name + "> is already defined as a sound");
			}			
		}else{
			System.err.println("SoundManager: not able to load " + name);
		}
	}
	
	public static int getSound(String name){
		if(gfxs.contains(name)){
			return gfxsIDs.get(gfxs.indexOf(name));
		}else{
			System.err.println("SoundManager: <" + name + "> is not defined as a sound");
			return 0;
		}
	}
	
	public static void playMusic(String name){
		music.setlooping(true);
		music.play(getSound(name));
	}
	
	public static void stopMusic(){
		music.stop();
	}
}
