package grouplab;

import grouplab.Checkers.Move;

import java.util.LinkedList;

public interface Player {
	// asks the player for a move
	public abstract Move queryMove(Board b, LinkedList<Move> moves);
	
}
