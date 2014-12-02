package grouplab;

import java.util.LinkedList;
import java.util.List;

/**
 * default implementation of checkers
 * ALL MOVES ARE ASSUMED TO BE LEGAL
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
		BLACK,
		RED;
		
	}
	
	// java doesn't have tuples... wtf
	public class Move {
		int o = -1; int d = -1;
		
		public Move(int origin, int dest) {
			o = origin;
			d = dest;
		}
		
		// TODO
		public int length() {
			return 0;
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
	
	// TODO
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
			System.exit(1);
		}
		return -1;
	}
	
	// TODO
	// get a legal moves for a player
	public List<Move> getLegalMoves(Board b, int player) {
		List<Move> result = new LinkedList<Move>();
		boolean canJump = false;
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				
				int coord = b.convertCoord(x, y);
				if (ownsPiece(coord, player)) {
					// TODO
					// handle piece direction options e.g. pawns vs. kings, red vs. black
					for (int dir = 0; dir < 4; dir++) {
						int adj = getAdjacent(coord, dir);
						
						// test if a jump can be made
						if ((b.pieceAt(adj) != 0) && (ownsPiece(adj, player) == false)) {
							int adj2 = getAdjacent(adj, dir);
							// index out of bounds
							if (adj2 == -1) {
								continue;
							} else if (b.pieceAt(adj2) == 0) {
								// delete any non-jumps
								for (Move m : result) {
									if (m.length() == 1)
										result.remove(m);
								}
								result.add(new Move(coord, adj2));
								canJump = true;
							}
						} else if ((b.pieceAt(adj) == 0) && (canJump == false)) { 
							result.add(new Move(coord, adj));
							
						}
					}
				}	
			}
		}
		return result;
	}
	
	// TODO
	// applies a move to the current board.
	public Board applyMove(Board b, Move m) {
		// TODO
		return board;
	}
	
	// tests if a given board state is a winning board state
	public boolean isWin(Board b, int player) {
		if (getLegalMoves(b, player).size() > 0)
			return false;
		return true;
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
	
	// TODO
	// plays a game of checkers
	public void play() {

	}
}
