package game.entities.light;

public class LightEngine {
	private static Light t_lights[];
	private static boolean lightsStorage[];
	private static int lightCounter;
	
	public LightEngine(int maxLights) {
		t_lights = new Light[maxLights];
		lightsStorage = new boolean[maxLights];
		lightCounter = 0;
	}
	
	public static int addLight(Light light){
		for(int i = 0; i < t_lights.length; i++){
			if(!lightsStorage[i]){
				lightCounter++;
				lightsStorage[i] = true;
				t_lights[i] = light;
				return i;
			}
		}
		System.out.println("maxNum of Lights is " + t_lights.length + "!");
		return -1;
	}
	
	public static void delLight(int i){
		if(t_lights[i] != null){
			lightsStorage[i] = false;
			t_lights[i] = null;
			lightCounter --;
		}		
	}
	
	public static Light[] getLights(){
		Light lights[] = new Light[lightCounter];
		int thisCounter = 0;
		for(int i = 0; i < lights.length; i++){
			if(lightsStorage[i] && t_lights[i] != null){
				lights[thisCounter] = t_lights[i];
				thisCounter ++;
			}
		}
		
		return lights;
	}
	
	
}
