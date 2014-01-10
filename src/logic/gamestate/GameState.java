package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Base State class that ensures a common interface
 * @author Ruben Cordeiro
 *
 */
public abstract class GameState implements KeyListener, MouseListener, MouseMotionListener{
	protected Component component;
	protected Graphics2D g2d;
	protected GameManager context;
	
	public abstract void setVisible(boolean b);
	public abstract void draw();
	public abstract void update();
	public abstract void exit();
	public abstract void restart();
}
