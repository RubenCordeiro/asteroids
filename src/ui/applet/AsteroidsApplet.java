package ui.applet;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import logic.Game;
import logic.GameSettings;
import logic.entity.ImageEntity;
import logic.gamestate.GameManager;
import logic.gamestate.MainScreen;
import logic.gamestate.Screens;

/**
 * Applet based interface implementation
 * @author Ruben Cordeiro
 *
 */
public class AsteroidsApplet extends Game{
	
	private static final long serialVersionUID = 1L;
	
	public AsteroidsApplet() 
	{
		super(60, GameSettings.SCREENWIDTH, GameSettings.SCREENHEIGHT);
	}
	
	GameManager gameManager;
	MainScreen main;
	
	@Override
	public void init() 
	{
		backBuffer =  new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		
		gameManager = new GameManager(null, this, g2d);
		gameManager.goNext(Screens.START, GameManager.RESUME);
		
		resize(1280, 720);
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		ImageEntity cursr = new ImageEntity(this);
		cursr.load("/resources/crosshaircyan.png");
		Cursor myCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursr.getImage(), new Point(this.getX(), 
				this.getY()), "crosshair");
		this.setCursor(myCursor);
	}

	@Override
	public void update(Graphics g)
	{
		gameManager.getCurrentState().draw();
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backBuffer, 0, 0, this);
	}

	@Override
	public void start() {
		_gameLoop = new Thread(this);
		_gameLoop.start();
	}

	@Override
	public void stop() {
		_gameLoop = null;
		gameManager.getCurrentState().exit();
		gameManager.getSoundManager().stop(); 
		gameManager.getSoundManager().stopBackgroundSound();
		gameManager.getLeaderboard().save("leaderboard.ast");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gameManager.getCurrentState().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {

		gameManager.getCurrentState().keyReleased(e);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		gameManager.keyTyped(e);
	}

	@Override
	public void run() 
	{
		Thread t = Thread.currentThread();

		while(t == _gameLoop)
		{
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
				gameManager.getCurrentState().update();
				repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gameManager.getCurrentState().mouseClicked(e);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		gameManager.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gameManager.mouseReleased(e);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gameManager.mouseDragged(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gameManager.mouseMoved(e);

	}

	@Override
	public void gameStartUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameTimedUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameRefreshScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameShutDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameUpdate() {
		// TODO Auto-generated method stub
		
	}
}
