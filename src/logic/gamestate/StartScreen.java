package logic.gamestate;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ui.component.Button;

import logic.Point2D;
import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;

/**
 * Initial game screen state that connects the major game states: 
 * <p>
 * MainScreen;
 * <p>
 * LeaderboardScreen;
 * <p>
 * SettingsScreen
 * <p>
 * HelpScreen.
 * 
 * @author Ruben Cordeiro
 *
 */
public class StartScreen extends GameState implements Observer{
	
	private ImageEntity foreground, background; 
	private Button startButton, leaderBoardsButton, settingsButton, helpButton;
	
	public StartScreen()
	{
		
	}
	
	public StartScreen(Component comp, Graphics2D g2d)
	{
		component = comp; this.g2d = g2d;
		foreground = new ImageEntity(comp);
		background = new ImageEntity(comp);
		
		background.load("/resources/myBackground.png");
		foreground.load("/resources/mainMenu.png");
		
		startButton = new Button(component);
		leaderBoardsButton = new Button(component);
		settingsButton = new Button(component);
		helpButton = new Button(component);
		
		startButton.addObserver(this);
		leaderBoardsButton.addObserver(this);
		settingsButton.addObserver(this);
		helpButton.addObserver(this);
		
		startButton.load("/resources/optionBackgroundSlim.png");
		leaderBoardsButton.load("/resources/optionBackgroundSlim.png");
		settingsButton.load("/resources/optionBackgroundSlim.png");
		helpButton.load("/resources/optionBackgroundSlim.png");
		
		startButton.setPosition(new Point2D(389, 312));
		leaderBoardsButton.setPosition(new Point2D(389, 404));
		settingsButton.setPosition(new Point2D(389, 494));
		helpButton.setPosition(new Point2D(389, 586));
		
		startButton.setAction("start");
		leaderBoardsButton.setAction("leaderboards");
		settingsButton.setAction("settings");
		helpButton.setAction("help");
	}
	
	public StartScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		this(comp, g2d); 
		this.context = context;
	}
	
	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		g2d.drawImage(background.getImage(), 0, 0, 1280 - 1, 720 - 1, component);
		g2d.drawImage(foreground.getImage(), 0, 0, 1280 - 1, 720 - 1, component);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		startButton.mouseClicked(e);
		leaderBoardsButton.mouseClicked(e);
		settingsButton.mouseClicked(e);
		helpButton.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, GameEvent e) {
		if(!(e instanceof ActionEvent))
			return; 
		
		ActionEvent ev = (ActionEvent)e;
		
		switch(ev.getMessage())
		{
		case "start": 
		{
			context.goNext(Screens.MAIN, GameManager.RESTART);
			break;
		}
		case "settings": 
		{
			context.goNext(Screens.OPTIONS, GameManager.RESUME);
			break;
		}
		case "help": 
		{
			context.goNext(Screens.HELP, GameManager.RESUME);
			break;
		}
		case "leaderboards": 
		{
			context.goNext(Screens.LEADERBOARDS, GameManager.RESTART);
			break;
		}
		default:
			break;
		}
		
	}

	@Override
	public void restart() {
		
	}

}
