package logic.event;

import sound.SoundType;

/**
 * Stores information about an sound sample that needs to be played. 
 * @author Ruben Cordeiro
 *
 */
public class SoundEvent extends GameEvent{
	protected SoundType _soundType;
	
	public SoundEvent(){}
	
	public SoundEvent(SoundType soundType)
	{
		_soundType = soundType;
	}
	
	public SoundType soundType() {return _soundType;}
}
