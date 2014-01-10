package logic.gamestate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import logic.GameSettings;
import logic.entity.ImageEntity;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;
import logic.statistics.Leaderboard;

/**
 * Leaderboards screen state, it displays the current leaderboard information
 * @author Ruben Cordeiro
 *
 */
public class LeaderboardScreen extends GameState implements Observer{

	private ImageEntity background;
	private Leaderboard leaderboard;
	
	public LeaderboardScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		this.component = comp; this.g2d = g2d; 
		this.context = context;
		
		background = new ImageEntity(comp, g2d);
		background.load("/resources/leaderboardBackground.png");
		leaderboard = context.getLeaderboard();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		context.goNext(Screens.START, GameManager.RESUME);
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() 
	{
		background.draw();
		int y = 20;
		leaderboard.draw(0, y, GameSettings.SCREENWIDTH, GameSettings.SCREENHEIGHT, 10, Color.GREEN);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, GameEvent e) {
		// TODO Auto-generated method stub
		
	}

}
