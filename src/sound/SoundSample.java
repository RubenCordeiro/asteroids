package sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * A SoundSample is an audio resource than can be played
 * @author Ruben Cordeiro
 *
 */
public class SoundSample implements Cloneable{
	private AudioInputStream sample;
	private Clip clip;
	private boolean looping = false;
	private int repeat = 0;
	private String filename = "";

	public SoundSample() {
		try {
			clip = AudioSystem.getClip();
		}catch(LineUnavailableException e) 
		{
			e.printStackTrace();
		}
	}

	public SoundSample(String _filename) {
		this();
		load(_filename);
	}

	private URL getURL(String filename) {
		URL url = null;

		try {
			url = this.getClass().getResource(filename);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return url;
	}

	public boolean load(String filename) {
		
		try {
			setFilename(filename);
			sample = AudioSystem.getAudioInputStream(getURL(filename));
			clip.open(sample);
			return true;
		}
		catch(IOException e) {e.printStackTrace(); return false;}
		catch(UnsupportedAudioFileException e) {e.printStackTrace(); return false;}
		catch(LineUnavailableException e) {e.printStackTrace(); return false;}
	}
	
	public void play() {
		if(!isLoaded()) return;
		
		// reset the sound clip
		clip.setFramePosition(0);
		if(looping)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			clip.loop(repeat);
	}
	
	public void stop() {
		clip.stop();
	}
	
	// getters
	public Clip getClip() {return clip;}
	public boolean getLooping() {return looping;}
	public int getRepeat() {return repeat;}
	public String getFilename(){return filename;}
	public boolean isLoaded(){return sample != null;}
	
	// setters
	public void setLooping(boolean val) {looping = val;}
	public void setRepeat(int value) {repeat = value;}
	public void setFilename(String name) {filename = name;}
	
	@Override
	public SoundSample clone()
	{
		SoundSample newSound = new SoundSample();
		newSound.load(filename);
		newSound.sample = this.sample;
		newSound.looping = false;
		newSound.filename = filename;
		newSound.repeat = repeat;

		return newSound;
	}
}
