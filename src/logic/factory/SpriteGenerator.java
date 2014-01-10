package logic.factory;

import logic.entity.Sprite;

/**
 * Concrete Sprite object generator
 * @author Ruben Cordeiro
 *
 */
public class SpriteGenerator implements Generator<Sprite> {
protected Sprite product;

public SpriteGenerator(Sprite prod) {
	product = prod;
}

@Override
public Sprite generate() {
	return product.clone();
}

public Sprite generate(double scaleFactor) {
	Sprite ret = product.clone();
	ret.scale(scaleFactor);
	return ret;
}

@Override
public Sprite generate(Sprite prod, double scaleFactor) {
	Sprite ret = prod.clone();
	ret.scale(scaleFactor);
	return ret;
}
}
