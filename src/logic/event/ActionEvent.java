package logic.event;

/**
 * Stores information about an action message.
 * @author Ruben Cordeiro
 *
 */
public class ActionEvent extends GameEvent{
	protected String message;
	
	public ActionEvent(String msg)
	{
		message = msg;
	}
	
	public ActionEvent(String msg, int time)
	{
		this(msg);
		this.time = time;
	}
	
	public String getMessage()
	{
		return message;
	}
}
