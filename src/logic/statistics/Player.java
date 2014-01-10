package logic.statistics;

import java.io.Serializable;

/**
 * The Player class encapsulates the information relative to a game player: name and score
 * @author Ruben Cordeiro
 *
 */
public class Player implements Comparable<Player>, Serializable{
	private static final long serialVersionUID = -427346187135930658L;
	private String name; 
	private int score;
	
	public Player(String name, int score)
	{
		this.name = name; this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Player other) {
		
		if(score > other.score)
			return -1;
		else
			if(score < other.score)
				return 1;
		
		return (name.compareTo(other.name));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Player)
		{
			Player other = (Player)o;
			return (name.equals(other.name) && score == other.score);
		}
		else
			return false;
	}
	
	
}
