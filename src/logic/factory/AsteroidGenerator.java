package logic.factory;

import logic.entity.Asteroid;

/**
 * Concrete Asteroid object generator
 * @author Ruben Cordeiro
 *
 */
public class AsteroidGenerator implements Generator<Asteroid>{
	private Asteroid product;
	
	public AsteroidGenerator() 
	{
		
	}
	
	public AsteroidGenerator(Asteroid ast)
	{
		product = ast;
	}
	
	public Asteroid generate(Asteroid ast, double scaleFactor) {
		Asteroid ret = ast.clone();
		ret.scale(scaleFactor);
		ret.setDamage((int)Math.round((ret.getDamage() * scaleFactor) + 1));
		return ret;
	}

	@Override
	public Asteroid generate() {
		return product.clone();
	}
	
	public Asteroid generate(double scaleFactor)
	{
		Asteroid ret = product.clone();
		ret.scale(scaleFactor);
		ret.setDamage((int)Math.round((ret.getDamage() * scaleFactor) + 1));
		return ret;
	}
}
