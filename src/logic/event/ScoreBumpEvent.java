package logic.event;

/**
 * Stores information about a score increase.
 * @author Ruben Cordeiro
 *
 */
public class ScoreBumpEvent extends GameEvent{
	protected int scoreBump;
	
	public ScoreBumpEvent() 
	{
		scoreBump = 0;
	}
	
	public ScoreBumpEvent(int score)
	{
		scoreBump = score;
	}
	
	public int scoreBump() {return scoreBump;}
}
