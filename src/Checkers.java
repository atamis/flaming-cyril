package grouplab;

import java.util.LinkedList;
import java.util.List;

/**
 * default implementation of checkers
 * 
 * @author Nick Care
 * @author Andrew Amis
 * @author Sam Goree
 * @author Gabe Appleby
 *
 */

public class Checkers {
	Board board;
	
	public static enum Player {
		RED,
		BLACK;
	}
	
	// java doesn't have tuples... wtf
	public class Move {
		int o = -1; int d = -1;
		
		public Move(int origin, int dest) {
			o = origin;
			d = dest;
		}
		
		public int length() {
			// TODO
			return 0;
		}
	}
	
	// initial set up of the board
	public void init() {
		board = new Board(8);
	}
	
	// sets up the initial board state
	public void setup() {
		// Black pieces
		for (int r=0; r < 3; r++) {
			for (int c=0; c < 8; c++) {
				if (r % 2 == 0) {
					if (c % 2 != 0) board.setPiece(board.convertCoord(c, r), 1);
				
				} else {
					if (c % 2 == 0) board.setPiece(board.convertCoord(c, r), 1);
				}	
			}
		}
		// Red pieces
		for (int r=5; r < 8; r++) {
			for (int c=0; c<8; c++) {
				if (r % 2 == 0) {
					if (c % 2 != 0) board.setPiece(board.convertCoord(c, r), 1);
				} else {
					if (c % 2 == 0) board.setPiece(board.convertCoord(c, r), 1);
				}	
			}
		}
	}
	
	// tests if a player owns a piece at a specific index
	public boolean ownsPiece(int index, int player) {
		int piece = board.pieceAt(index);
		if (piece == 0) {
			System.out.printf("There isn't even a piece on tile %d!", index);
			return false;
		}
		if (player == 0) {
			if ((piece == 1) || (piece == 2))
				return true;
			return false;
		 } else {
			 if ((piece == 3) || (piece == 4)) 
			 	return true;
			 return false;
		 }
	}
	
	// tests if a move is legal
	public boolean legalMove(Move m) {
		return legalMove(m.o, m.d);
	}
	
	// tests if a move is legal
	public boolean legalMove(int origin, int target) {
		// TODO
		return false;
	}
	
	// get a legal moves for a player
	public List<Move> getLegalMoves(int player) {
		List<Move> result = new LinkedList<Move>();
		
		// TODO
		return result;
	}
	
	// tests if a given board state is a winning board state
	public boolean isWin(int player) {
		if (getLegalMoves(player).size() > 0)
			return false;
		return true;
	}
	
	// gets tile in specified direction
	public int getAdjacent(int index, int direction) {
		if ((index < 0) || (index > board.size))
			System.out.printf("Index out of bounds: %d", direction);
		switch(direction) {
		// get north west
		case(0):
			if  ((index < board.size)  || (index % board.size == 0))
					return -1;
			return direction - (board.size + 1);
		// get north east
		case(1):
			if ((index < board.size) || (index + 1 % board.size == 0))
				return -1;
			return direction - (board.size - 1);
		// get south west
		case(2):
			// TODO
			if (index > board.size*board.size - board.size) 
				return -1;
		// get south east
		case(3):
			// TODO
			return -1;
		default:
			System.out.printf("%d is not a valid direction! Exitting.", direction);
			System.exit(0);
		}
		return -1;
	}
	
	// applies a move to the current board.
	public Board applyMove(Move m) {
		// TODO
		return board;
	}
	
	// plays a game of checkers
	public void play() {
		// TODO
	}
}
