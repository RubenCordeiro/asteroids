package logic;

import java.awt.Color;
import java.awt.Graphics2D;

import logic.entity.Ship;
import logic.event.GameEvent;
import logic.event.ScoreBumpEvent;
import logic.observer.Observer;

/**
 * Class responsible for displaying the statistic information on the screen:
 * <p>
 * Ship's health and shield; 
 * <p>
 * Current score
 * 
 * @author Ruben Cordeiro
 *
 */
public class HUDManager implements Observer{

	private final int maxPlayerHealth, maxPlayerShield;
	private int score, playerHealth, playerShield, waveNumber;
	private transient double ratio;

	public HUDManager() {
		maxPlayerHealth = 0; maxPlayerShield = 0;
		score = 0; playerHealth = 0; playerShield = 0; waveNumber = 1;
	}

	public HUDManager(int score, int playerHealth, int playerShield) 
	{
		maxPlayerHealth = playerHealth; maxPlayerShield = playerShield;
		this.score = score; this.playerHealth = playerHealth; this.playerShield = playerShield; waveNumber = 1;
	}
	
	public int getScore() 
	{
		return score;
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.GREEN);
		g2d.drawString("Score: " + score, 10, 10);

		if(playerHealth > 0)
			ratio = (playerHealth / (double)maxPlayerHealth) * 100;
		else
			ratio = 0;

		g2d.drawString("Health: " + (int)ratio + "%", 10, 25);

		if(playerShield > 0)
			ratio = (playerShield / (double)maxPlayerShield) * 100;
		else
			ratio = 0;

		g2d.drawString("Shield : " + (int)ratio + "%", 10, 40);
		g2d.drawString("Wave: " + waveNumber, (int)(GameSettings.SCREENWIDTH / (double)2),25);
	}
	
	public void incWaveNumber(int n)
	{
		waveNumber += n;
	}

	@Override
	public void update(logic.observer.Observable o, GameEvent e) {

		Ship ship;

		if(o instanceof Ship)
		{
			ship = (Ship)o;
			playerHealth = ship.health();
			playerShield = ship.shield();
			return; 
		}

		if(e instanceof ScoreBumpEvent)
		{
			score += ((ScoreBumpEvent)e).scoreBump();
			return;
		}
	}

}
