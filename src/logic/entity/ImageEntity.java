package logic.entity;
import java.awt.*;
import java.awt.geom.*;
import java.net.*;

import javax.imageio.ImageIO;

import logic.Point2D;

/**
 * Represents a drawable object. 
 * @author Ruben Cordeiro
 *
 */
public class ImageEntity extends GameObject{
	protected Image image; 
	protected Component component; 
	protected AffineTransform at; 
	protected Graphics2D g2d; 
	protected int height, width;

	public ImageEntity() 
	{
		super();
		width = 5; 
		height = 5;
	}
	
	public ImageEntity(Component a)
	{
		component = a; 
		image = null; 
		alive = true;
		width = 0;
		height = 0;
	}

	public ImageEntity(Component a, Graphics2D g2d)
	{
		component = a;
		this.g2d = g2d;
		image = null; 
		alive = true;
		width = 0;
		height = 0;
	}
	
	public Image getImage() {return image;}

	public void setImage(Image image) 
	{
		this.image = image;
		
		// Update all the remaining fields with the new image properties
		width = image.getWidth(component);
		height = image.getHeight(component);
		double x = component.getSize().width/2 - width/2; 
		double y = component.getSize().height/2 - height/2;
		at = AffineTransform.getTranslateInstance(x,  y);
	}
	
	public Component component() 
	{
		return component;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public double getCenterX()
	{
		return getX() + width() /2;
	}

	public double getCenterY()
	{ 
		return getY() + height() / 2;
	}

	public void setGraphics(Graphics2D g)
	{
		g2d = g;
	}
	
	public Graphics2D graphics() {return g2d;}

	protected URL getURL(String filename)
	{
		URL url = null; 

		try {
			url = this.getClass().getResource(filename);
		} catch(Exception e){
			e.printStackTrace();
		}

		return url;
	}

	public void load(String filename)
	{
		{
			try{
				image=ImageIO.read(getClass().getResource(filename));
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		setImage(image);
	}

	public void transform() {
		at.setToIdentity();
		at.translate((int)x + width() /2, (int)y + height()/2);
		at.rotate(Math.toRadians(getFaceAngle()));
		at.translate(-width()/2, -height()/2);
	}

	public void draw() {
		transform();
		g2d.drawImage(getImage(), at, component);
	}

	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int)x, (int)y, width(), height());
		return r;
	}

	/**
	 * Scales the object with the scale factor given as a parameter
	 * @param scaleFactor scale factor 
	 */
	public void scale(double scaleFactor) 
	{
		int newWidth = (int) Math.round(scaleFactor * width()); int newHeight = (int) Math.round(scaleFactor * height());
		Image scaled = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		setImage(scaled);
		width = newWidth; height = newHeight;
		double x = component.getSize().width/2 - width/2; 
		double y = component.getSize().height/2 - height/2;
		at = AffineTransform.getTranslateInstance(x,  y);
	}

	public void setPosition(Point2D pos) {
		x = pos.X(); 
		y = pos.Y();
	}
}
