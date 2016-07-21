package audio;

import org.lwjgl.openal.AL10;

public class Source {
	private int sourceID;
	
	public Source(){
		sourceID = AL10.alGenSources();
	}
	
	public void play(int buffer){
		//stop();
		AL10.alSourcei(sourceID, AL10.AL_BUFFER, buffer);
		continuePlay();
	}
	
	public void pause(){
		AL10.alSourcePause(sourceID);
	}
	
	public void continuePlay(){
		AL10.alSourcePlay(sourceID);
	}
	
	public void stop(){
		AL10.alSourceStop(sourceID);
	}
	
	public void delete(){
		stop();
		AL10.alDeleteSources(sourceID);
	}
	
	public void setlooping(boolean loop){
		if(loop){
			AL10.alSourcef(sourceID, AL10.AL_LOOPING, AL10.AL_TRUE);
		}else{
			AL10.alSourcef(sourceID, AL10.AL_LOOPING, AL10.AL_FALSE);
		}
	}
	
	public boolean isPlaying(){
		return AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}
	
	//geschwindigkeit
	public void setVelocity(float x, float y, float z){
		AL10.alSource3f(sourceID, AL10.AL_VELOCITY, x, y, z);
	}
	
	public void setVolume(float volume){
		AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
	}
	
	//Tonhöhe
	public void setPitch(float pitch){
		AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
	}
	
	public void setPosition(float x, float y, float z){
		AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
	}
}
