package logic.event;

/**
 * The root class from which all event state objects are derived.
 * @author Ruben Cordeiro
 *
 */
public abstract class GameEvent implements Comparable<GameEvent>{
	protected long time;
	
	@Override
	public int compareTo(GameEvent other) {
		if      (this.time < other.time) return -1;
		else if (this.time > other.time) return +1;
		else                            return  0;
	}
}
