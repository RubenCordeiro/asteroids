package logic.statistics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Leaderboard class stores information about the Player's names and scores in a descending order
 * @author Ruben Cordeiro
 *
 */
public class Leaderboard implements Serializable{

	private static final long serialVersionUID = 1L;

	private Set<Player> stats;
	private transient Graphics2D g2d;


	public Leaderboard()
	{
		stats = new TreeSet<Player>(new LeaderboardComparator());
	}

	public Leaderboard(Graphics2D g2d)
	{
		this();
		this.g2d = g2d;
	}

	public void setGraphics(Graphics2D g2d)
	{
		this.g2d = g2d;
	}

	public boolean insert(Player p)
	{
		boolean ret = stats.add(p);
		return ret; 
	}

	/**
	 * Tests if a Player has a score bigger than the first number of entries given as a parameter
	 * @param player Player to be tested
	 * @param nEntries number of Leaderboard entries to test
	 * @return true if the player has a higher score, false otherwise
	 */
	public boolean isHighScore(Player player, int nEntries)
	{
		for(Player p : stats)
		{
			if(nEntries == 0)
				return false;

			if(player.getScore() > p.getScore())
				return true;

			--nEntries;
		}


		return true;
	}

	public Set<Player> getStats()
	{
		return stats;
	}

	/**
	 * Returns the first number of entries given as a parameter
	 * @param nEntries number of leaderboard entries to return
	 * @return ArrayList with the number of Player entries
	 */
	public ArrayList<Player> getTopEntries(int nEntries)
	{
		ArrayList<Player> ret = new ArrayList<Player>();

		for(Player p : stats)
		{
			if(nEntries == 0)
				break;

			ret.add(p);

			--nEntries;
		}

		return ret;
	}

	@Override
	public String toString()
	{
		String ret = new String();
		for(Player p : stats)
		{
			ret += p.getName() + " " + p.getScore() + '\n';
		}
		return ret;
	}

	public void draw(int x, int y, int width, int height, int nEntries, Color c)
	{
		int namePosX = x + width / 3;
		int scorePosX = x + (int)(2 * (width / (double)3));
		int deltaY = height / (nEntries + 1);
		g2d.setColor(c);
		g2d.drawString("NAME", namePosX, y);
		g2d.drawString("SCORE", scorePosX, y);
		y += deltaY;
		for(Player p : stats)
		{
			if(nEntries == 0)
				break;

			g2d.drawString(p.getName(), namePosX, y);
			g2d.drawString(" " + p.getScore(), scorePosX, y);

			--nEntries;

			y += deltaY;
		}
	}

	public static Leaderboard load(String filepath)
	{
		Leaderboard leaderboard = null;
		ObjectInputStream output = null;
		Object temp = null;

		try {
			output = new ObjectInputStream(new FileInputStream(filepath));
			temp = output.readObject();
		} catch(Exception e){
			e.printStackTrace();
			return leaderboard;
		}
		finally {
			try {
				if(output != null) output.close();
			} catch(Exception e) {
				e.printStackTrace();
				return leaderboard;
			}
		}

		if(temp != null && temp instanceof Leaderboard)
			leaderboard = (Leaderboard)temp;

		return leaderboard;
	}

	public boolean save(String filepath)
	{
		ObjectOutputStream output = null;

		try {
			output = new ObjectOutputStream(new FileOutputStream(filepath));
			output.writeObject(this);
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(output != null) output.close();
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
}
