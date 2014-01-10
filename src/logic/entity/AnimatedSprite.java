package logic.entity;


import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 * An animatedSprite is an Sprite with a tileset animation
 * @author Ruben Cordeiro
 *
 */
public class AnimatedSprite extends Sprite implements Cloneable{
	
	private Image animImage; 
	
	private BufferedImage tempImage;
	private Graphics2D tempSurface;
	
	private int currentFrame, totalFrames, animDir, frameCount, frameDelay, frameWidth, frameHeight, columns;
	
	public AnimatedSprite() 
	{
		super();
	}
	
	public AnimatedSprite(Component applet, Graphics2D g2d) 
	{
		super(applet, g2d);
		currentFrame = 0; totalFrames = 0; animDir = 1; frameCount = 0; frameDelay = 0; 
		frameWidth = 0; frameHeight = 0; columns = 0;
	}
	
	/**
	 * Loads the tileset image and updates the AnimatedSprite information
	 * @param filepath path of the tileset file
	 * @param columns number of columns of the tileset
	 * @param rows number of rows of the tileset
	 * @param width width of the tileset
	 * @param height height of the tileset
	 */
	public void load(String filepath, int columns, int rows, int width, int height) 
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		animImage = tk.getImage(getURL(filepath));
		this.columns = columns; totalFrames = columns * rows;
		frameWidth = width; frameHeight = height;
		
		tempImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		tempSurface = tempImage.createGraphics();
		super.setImage(tempImage);
	}
	
	public int currentFrame() {return currentFrame;}
	public void setCurrentFrame(int frameNum) {currentFrame = frameNum;}
	
	public int frameWidth() {return frameWidth;}
	public void setFrameWidth(int width) {frameWidth = width;}
	
	public int frameHeight() {return frameHeight;}
	public void setFrameHeight(int height) {frameHeight = height;}
	
	public int totalFrames() {return totalFrames;}
	public void setTotalFrames(int frameNum) {totalFrames = frameNum;}
	
	public int animationDirection() {return animDir;}
	public void setAnimationDirection(int dir) {animDir = dir;}
	
	public int frameCount() {return frameCount;}
	public void setFrameCount(int count) {frameCount = count;}
	
	public int frameDelay() {return frameDelay;}
	public void setFrameDelay(int delay) {frameDelay = delay;}
	
	public int columns() {return columns;}
	public void setColumns(int cols) {columns = cols;}
	
	/**
	 * Updates the tileset animation based on the current frame, animation direction and fame delay
	 */
	public void updateAnimation() {
		
		++frameCount;
		
		if(frameCount > frameDelay)
		{
			frameCount = 0;
			currentFrame += animDir;
			
			if(currentFrame > totalFrames - 1)
				currentFrame = 0;
			else
				if(currentFrame < 0)
					currentFrame = totalFrames - 1;
		}
	}
	
	/**
	 * Draws the current tile
	 */
	public void draw() {
		int frameX = (currentFrame % columns) * frameWidth;
		int frameY = (currentFrame / columns) * frameHeight;
		
		tempSurface.drawImage(animImage, 0,0, frameWidth - 1, frameHeight - 1, frameX, frameY, 
				frameX + frameWidth, frameY + frameHeight, component);
		
		super.setImage(tempImage);
		super.transform();
		super.draw();
	}
		
	@Override
	public AnimatedSprite clone() {
		AnimatedSprite newSprite = new AnimatedSprite(this.component, this.g2d);
		newSprite.alive = true;
		newSprite.animImage = this.animImage;
		newSprite.image = this.image;
		newSprite.columns = this.columns; newSprite.totalFrames = this.totalFrames;
		newSprite.frameWidth = this.frameWidth; newSprite.frameHeight = frameHeight;
		newSprite.tempImage = new BufferedImage(newSprite.frameWidth, newSprite.frameHeight, BufferedImage.TYPE_INT_ARGB);
		newSprite.tempSurface = newSprite.tempImage.createGraphics();
		newSprite.frameCount = 0;
		newSprite.setImage(newSprite.tempImage);
		return newSprite;
	}
}
