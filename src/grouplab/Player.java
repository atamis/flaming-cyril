package grouplab;


import java.util.LinkedList;

public interface Player {
	// asks the player for a move
	public Move queryMove(Board b, LinkedList<Move> moves);
	
	// the player's name
	public String getName();
}
