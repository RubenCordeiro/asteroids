package logic.visitor;

import logic.entity.ImageEntity;
import logic.entity.Sprite;

/**
 * General collision interface
 * @author Ruben Cordeiro
 *
 */
public interface Collideable {
	public boolean collidesWith(ImageEntity s);
	public void accept(Sprite s);
}
