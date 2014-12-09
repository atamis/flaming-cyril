package grouplab;

import java.util.LinkedList;

/**
* Interface that should be implemented by all players
*
* @author Nick Care (2014)
* @author Andrew Amis (2014)
* @author Sam Goree (2014)
* @author Gabe Appleby (2014)
*/
public interface Player {
	// asks the player for a move
	public Move queryMove(Board b, LinkedList<Move> moves);

	// the player's name
	public String getName();
	
	public void setName(String name);

	// is the player human
	public boolean isHuman();
}
