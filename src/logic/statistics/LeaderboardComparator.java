package logic.statistics;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator class that ensure the ordering of the Player objects in the Leaderboard TreeSet
 * @author Ruben Cordeiro
 *
 */
public class LeaderboardComparator implements Comparator<Player>, Serializable{

	private static final long serialVersionUID = -4031503501385927323L;

		@Override
	    public int compare(Player p1, Player p2) {
	        return p1.compareTo(p2);
	    }
}
