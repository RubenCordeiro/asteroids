package sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.event.GameEvent;
import logic.event.SoundEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * The SoundManager is a class responsible for playing all the sound samples in the game.
 * @author Ruben Cordeiro
 *
 */
public class SoundManager implements Observer{

	private boolean effectsEnabled, backgroundEnabled; 

	Map<SoundType, ArrayList<SoundSample>> sounds;
	
	ArrayList<SoundSample> activeSounds;
	
	SoundSample backgroundSound;

	public SoundManager() {
		sounds = new HashMap<SoundType, ArrayList<SoundSample>>();
		activeSounds = new ArrayList<SoundSample>();
		effectsEnabled = true;
		backgroundEnabled = true;
	}

	public void add(SoundType soundType, SoundSample sound, int numSamples) {
		
		ArrayList<SoundSample> samples = new ArrayList<SoundSample>();
	
		for(; numSamples > 0; --numSamples)
			samples.add(sound.clone());
		
		sounds.put(soundType, samples);
	}

	public void play(SoundType type)
	{
		if(!sounds.containsKey(type) || !effectsEnabled)
			return;

		ArrayList<SoundSample> samples = sounds.get(type);
		SoundSample current;
		for(int n = 0; n < samples.size(); ++n)
		{
			current = samples.get(n);
			if(!current.getClip().isActive())
			{
				current.play();
				return;
			}
				
		}
		
		samples.get(0).play();
	}

	public void playBackgroundSound(SoundSample s)
	{
		if(backgroundEnabled)
		{
			if(backgroundSound != null)
				backgroundSound.stop();
			
			backgroundSound = s; 
			s.setLooping(true);
			backgroundSound.play();
		}
	}

	public void stopBackgroundSound()
	{
		if(backgroundSound != null)
			backgroundSound.stop(); 
		
		backgroundSound = null; 
	}

	/**
	 * Stops all the active sound samples
	 */
	public void stop()
	{
		SoundSample s;
		for(int n = 0; n < activeSounds.size(); ++n)
		{
			s = activeSounds.get(n);
			s.stop();
			s = null;
		}
	}

	public void purgeSounds() {

	}

	public void enableEffects(boolean value)
	{
		effectsEnabled = value;
	}
	
	public void enableBackgroundMusic(boolean value)
	{
		backgroundEnabled = value;
	}

	/**
	 * Listens to a SoundEvent information
	 */
	@Override
	public void update(Observable o, GameEvent e) {
		if(!(e instanceof SoundEvent))
			return;

		SoundEvent event = (SoundEvent)e;

		play(event.soundType());
	}

}
