package logic;

import java.applet.Applet;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.entity.Sprite;

/**
 * Base Game class that encapsulates basic information and establishes an interface for all the concrete implementations.
 * @author Ruben Cordeiro
 *
 */
public abstract class Game extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	public enum State {RUNNING, PAUSED};
	
	protected Thread _gameLoop;

	private ArrayList<Sprite> _sprites;

	protected BufferedImage backBuffer;
	protected Graphics2D g2d;
	protected int screenWidth, screenHeight;
	protected int _frameCount = 0, _frameRate = 0;
	protected int _desiredRate;
	@SuppressWarnings("unused")
	private long startTime = System.currentTimeMillis();
	protected State _currentState;
	
	public boolean isPaused() {return _currentState.equals(State.PAUSED);}
	public void pauseGame() {_currentState = State.PAUSED;}
	public void resumeGame() {_currentState = State.RUNNING;}
	
	public abstract void gameStartUp();
	public abstract void gameTimedUpdate();
	public abstract void gameRefreshScreen();
	public abstract void gameShutDown();
	public abstract void gameUpdate();
	
	public Game(int frameRate, int width, int height) {
		_frameRate = frameRate; screenWidth = width; screenHeight = height;
	}
	
	public Graphics2D graphics() {return g2d;}
	public int frameRate() {return _frameRate;}
	
	public Thread getGameLoop() {return _gameLoop; }
	public void setGameLoop(Thread _gameLoop) { this._gameLoop = _gameLoop;}
	
	public ArrayList<Sprite> getSprites() {	return _sprites;}
	public void setSprites(ArrayList<Sprite> _sprites) {this._sprites = _sprites;}
}
