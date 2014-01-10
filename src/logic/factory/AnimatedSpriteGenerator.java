package logic.factory;

import logic.entity.AnimatedSprite;

/**
 * Concrete AnimatedSprite object generator
 * @author Ruben Cordeiro
 *
 */
public class AnimatedSpriteGenerator implements Generator<AnimatedSprite>{
	protected AnimatedSprite product;
	
	public AnimatedSpriteGenerator(AnimatedSprite prod) {
		product = prod;
	}
	
	@Override
	public AnimatedSprite generate() {
		return product.clone();
	}
	
	public AnimatedSprite generate(double scaleFactor)
	{
		AnimatedSprite ret = product.clone();
		ret.scale(scaleFactor);
		return ret;
	}

	@Override
	public AnimatedSprite generate(AnimatedSprite prod, double scaleFactor) {
		AnimatedSprite ret = prod.clone();
		ret.scale(scaleFactor);
		return ret;
	}

}
