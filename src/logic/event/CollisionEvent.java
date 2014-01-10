package logic.event;
import sound.SoundType;
import logic.entity.ImageEntity;

public class CollisionEvent extends GameEvent{
	private SoundType _soundType;
	private AnimationType _animationType;
	private ImageEntity _sprite;
	
	public CollisionEvent(AnimationType anim, ImageEntity sprite)
	{
		_animationType = anim; 
		_sprite = sprite;
		_soundType = null;
	}
	
	public CollisionEvent(AnimationType anim, ImageEntity sprite, SoundType sound)
	{
		_animationType = anim; _sprite = sprite;
		_soundType = sound;
	}
	
	public AnimationType animationType() {return _animationType;}
	public ImageEntity sprite() {return _sprite;};
	public SoundType soundType() {return _soundType;}
	
	/*public Sprite first() {return s1;}
	public Sprite second() {return s2;}*/
}
