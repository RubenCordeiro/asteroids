package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logic.entity.AnimatedSprite;

import logic.event.AnimationEvent;
import logic.event.AnimationType;
import logic.event.GameEvent;

import logic.factory.AnimatedSpriteGenerator;
import logic.observer.Observer;

/**
 * The AnimationManager is a class responsible for generating, updating and drawing all the animations in the game.
 * @author Ruben Cordeiro
 *
 */
public class AnimationManager implements Observer{
	
	/**
	 * Active animations.
	 */
	private ArrayList<AnimatedSprite> _animations;
	private Map<AnimationType, AnimatedSpriteGenerator> generators;
	private transient double x, y;

	public AnimationManager() {
		_animations = new ArrayList<AnimatedSprite>();
		generators = new HashMap<AnimationType, AnimatedSpriteGenerator>();
	}

	public void add(AnimationType type, AnimatedSpriteGenerator g)
	{
		generators.put(type, g);
	}

	/**
	 * Draws and updates the active animations.
	 */
	public void draw() 
	{

		for(int n=0; n < _animations.size(); ++n)
		{
			AnimatedSprite current =  _animations.get(n);

			if(!current.isAlive())
				continue;

			current.updateAnimation();

			if(current.currentFrame() == current.totalFrames() - 1)
			{
				current.setCurrentFrame(0);
				current.setAlive(false);
			}
			else
				current.draw();
		}
	}

	/**
	 * Updates all the active animations.
	 */
	public void updateAnimations()
	{
		for(int n=0; n < _animations.size(); ++n)
		{
			AnimatedSprite current =  _animations.get(n);

			if(!current.isAlive())
				continue;

			current.updateAnimation();

			if(current.currentFrame() == current.totalFrames() - 1)
			{
				current.setCurrentFrame(0);
				current.setAlive(false);
				_animations.remove(n);
			}
		}
	}

	public void handle(AnimationEvent e) {

		if(!generators.containsKey(e.type()))
			return;

		AnimatedSprite newAnimation = generators.get(e.type()).generate();

		newAnimation.setPosition(e.position());
		_animations.add(newAnimation);
	}

	@Override
	public void update(logic.observer.Observable o, GameEvent e) {

		if(!(e instanceof AnimationEvent))
			return;

		AnimationEvent ev = (AnimationEvent)e;

		if(!generators.containsKey(ev.type()))
			return;

		AnimatedSprite newAnimation = generators.get(ev.type()).generate();

		if(newAnimation == null)
			return;

		Point2D spritePos = ev.position();

		x = spritePos.X() - newAnimation.getBounds().width / 2;
		y = spritePos.Y() - newAnimation.getBounds().height / 2;

		newAnimation.setPosition(new Point2D(x, y));
		_animations.add(newAnimation);
	}

}
