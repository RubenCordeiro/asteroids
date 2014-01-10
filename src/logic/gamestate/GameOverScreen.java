package logic.gamestate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import sound.SoundSample;
import ui.component.Button;
import ui.component.TextField;

import logic.GameSettings;
import logic.Point2D;
import logic.entity.ImageEntity;
import logic.event.ActionEvent;
import logic.event.GameEvent;
import logic.observer.Observable;
import logic.observer.Observer;
import logic.statistics.Player;

/** 
 * Game Over screen state, it displays the final player's score 
 * and allows the user to insert it in the leaderboard with a custom name
 * 
 * @author Ruben Cordeiro
 *
 */
public class GameOverScreen extends GameState implements Observer{
	private Button backButton;
	private ImageEntity background, highScoreNotification, entryAddedNotification;
	private TextField textBox;
	private SoundSample backgroundMusic;
	private boolean entryAdded;

	public GameOverScreen(Component comp, Graphics2D g2d)
	{
		this.component = comp; 
		this.g2d = g2d;
		background = new ImageEntity(comp);
		background.load("/resources/gameOverScreen.png");
		
		highScoreNotification = new ImageEntity(comp, g2d);
		highScoreNotification.load("/resources/newHighscore.png");
		highScoreNotification.setPosition(new Point2D(519, 559));
		
		backButton = new Button(comp);
		backButton.setGraphics(g2d);
		backButton.load("/resources/backButton.png");
		
		textBox = new TextField(comp, g2d, Color.BLACK, new Font("arial", Font.PLAIN, 30));
		textBox.load("/resources/textbox.png");
		textBox.setMaxSize(20);
		textBox.setPosition(new Point2D(451, 600));
		textBox.setAction("player_text");
		textBox.addObserver(this);
		
		entryAddedNotification = new ImageEntity(comp, g2d);
		entryAddedNotification.load("/resources/leaderboardSuccessNotification.png");
		entryAddedNotification.setPosition(new Point2D(textBox.getX(), textBox.getY()));
		
		backButton.setPosition(new Point2D(384, 591));
		backButton.setAction("back");
		backButton.addObserver(this);
		entryAdded =  false;
		backgroundMusic =  new SoundSample("/resources/gameOverSong.wav");
	}

	public GameOverScreen(Component comp, Graphics2D g2d, GameManager context)
	{
		this(comp, g2d);
		this.context = context;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		textBox.keyPressed(e);

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		textBox.keyTyped(e);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		backButton.mouseClicked(e);
		textBox.mouseClicked(e);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{

	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{

	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		g2d.drawImage(background.getImage(), 0, 0, 1280 - 1, 720 - 1, component);
		backButton.draw();
		
		Font prevFont = g2d.getFont();
		g2d.setColor(Color.GREEN);
		g2d.setFont(new Font("arial", Font.PLAIN, 20));
		g2d.drawString("Score: " + context.getHUDManager().getScore(), (int)highScoreNotification.getX() + 5, 
				(int)highScoreNotification.getY() - 10);
		g2d.setFont(prevFont);
		
		if(context.getLeaderboard().isHighScore(new Player("", context.getHUDManager().getScore()), 10))
		{
			highScoreNotification.draw();
		    textBox.draw();
		}
		
		if(entryAdded)
			entryAddedNotification.draw();

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
	public void update(Observable o, GameEvent e) {
		if(!(e instanceof ActionEvent))
			return; 

		ActionEvent ev = (ActionEvent)e;

		switch(ev.getMessage())
		{
		case "back": 
			if(textBox.isEnabled() && !entryAdded)
				return;
			else
				context.goNext(Screens.START, GameManager.RESUME);
			break; 
		case "player_text": 
			String text = textBox.getText();
			context.getLeaderboard().insert(new Player(text, context.getHUDManager().getScore()));
			context.getLeaderboard().save(GameSettings.LEADERBOARD_PATH);
			textBox.setEnabled(false);
			entryAdded = true;
			break;
		default: 
			break;
		}

	}

	@Override
	public void restart() {
		
		if(context.getLeaderboard().isHighScore(new Player("", context.getHUDManager().getScore()), 10))
		{
			textBox.setEnabled(true);
			textBox.setText("Enter your name");
		}
		else
			textBox.setEnabled(false);
		
		entryAdded = false;
		context.getSoundManager().playBackgroundSound(backgroundMusic);
		
	}

}
