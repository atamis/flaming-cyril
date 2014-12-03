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
	
	int[] redDirection = { 1, 2 };
	int[] blkDirection = { 3, 4 };
	int[] kingDirection = { 1, 2, 3, 4 };
	
	public int[] getDirections(int id) {
		switch (id) {
		case(1):
			return blkDirection;
		case(2):
			return redDirection;
		case(3):
		case(4):
			return kingDirection;
		}
		return null;
	}
	
	public static enum Player {
		BLACK,
		RED;
		
		
	}

	public class Move {
		int o = -1; int d = -1;
		
		public Move(int origin, int dest) {
			o = origin;
			d = dest;
		}
		
		public int length() {
			if (Math.abs(o - d - 8) > 1)
				return 2;
			return 1;
		}
	}
	
	// tests if a player owns a piece at a specific index
	public boolean ownsPiece(Board b, int index, Player p) {
		int piece = b.pieceAt(index);
		if (piece == 0) {
			System.out.printf("There isn't even a piece on tile %d!", index);
			return false;
		}
		if (p == Player.BLACK) {
			if ((piece == 1) || (piece == 2))
				return true;
			return false;
		 } else {
			 if ((piece == 3) || (piece == 4)) 
			 	return true;
			 return false;
		 }
	}
	
	// gets tile in specified direction
	public int getAdjacent(Board b, int index, int direction) {
		if ((index < 0) || (index > b.size))
			System.out.printf("Index out of bounds: %d", direction);
		switch(direction) {
		case(0):
			if  ((b.onFirstRow(index) == true)  || (b.onFirstCol(index) == true))
					return -1;
			return direction - (b.size + 1);
		case(1):
			if ((b.onFirstRow(index) == true) || (b.onLastCol(index) == true))
				return -1;
			return direction - (b.size - 1);
		case(2):
			if ((b.onLastRow(index) == true) || (b.onFirstCol(index) == true))
				return -1;
			return direction + (b.size - 1);
		case(3):
			if ((b.onLastRow(index) == true) || (b.onLastCol(index) == true))
				return -1;
			return direction + (b.size + 1);
		default:
			System.out.printf("%d is not a valid direction!", direction);
			return -1;
		}
	}
	
	// get a legal moves for a player
	public List<Move> getLegalMoves(Board b, Player p) {
		List<Move> result = new LinkedList<Move>();
		boolean canJump = false;
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				
				int coord = b.convertCoord(x, y);
				if (ownsPiece(b, coord, p)) {
					// TODO
					// handle piece direction options e.g. pawns vs. kings, red vs. black
					for (int dir : getDirections(b.pieceAt(coord))) {
						int adj = getAdjacent(b, coord, dir);
						
						// test if a jump can be made
						if ((b.pieceAt(adj) != 0) && (ownsPiece(b, adj, p) == false)) {
							int adj2 = getAdjacent(b, adj, dir);
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
	
	// applies a move to the current board.
	public Board applyMove(Board b, Move m, Player p) {
		Board result = new Board(b);
		
		if (p == Player.BLACK) {
			if ((b.pieceAt(m.o) == 1) && (b.onLastRow(m.d) == true)) {
				result.setPiece(m.d, 2);
				// indicate end of turn
			} else {
				result.setPiece(m.d, b.pieceAt(m.o));
			}
		} else if (p == Player.RED) {
			if ((b.pieceAt(m.o) == 3) && (b.onFirstRow(m.d) == true)) {
				result.setPiece(m.d, 4);
				// indicate end of turn
			} else {
				result.setPiece(m.d, b.pieceAt(m.o));
			}
		}
		
		// delete the piece if a jump
		int coord = 0;
		if (m.length() > 1) {
			for (int i=0; i<4; i++) {
				coord = getAdjacent(b, m.o, i);
				if (getAdjacent(b, coord, i) == m.d)
					break;
			}
			result.setPiece(coord, 0);
		}
		return result;
	}
	
	// tests if a given board state is a winning board state
	public boolean isWin(Board b, Player p) {
		if (getLegalMoves(b, p).size() > 0)
			return false;
		return true;
	}

	// initial set up of the board
	public void init() {
		
	}
	
	// sets up the initial board state
	public Board setup() {
		Board b = new Board(8);
		// Black pieces
		for (int r=0; r < 3; r++) {
			for (int c=0; c < 8; c++) {
				if (r % 2 == 0) {
					if (c % 2 != 0) b.setPiece(b.convertCoord(c, r), 1);
				
				} else {
					if (c % 2 == 0) b.setPiece(b.convertCoord(c, r), 1);
				}	
			}
		}
		// Red pieces
		for (int r=5; r < 8; r++) {
			for (int c=0; c<8; c++) {
				if (r % 2 == 0) {
					if (c % 2 != 0) b.setPiece(b.convertCoord(c, r), 1);
				} else {
					if (c % 2 == 0) b.setPiece(b.convertCoord(c, r), 1);
				}	
			}
		}
		return b;
	}
	
	// TODO
	// plays a game of checkers
	public void play() {

		// TODO
		// check if a move takes a piece - if it does recurse
	}
}
