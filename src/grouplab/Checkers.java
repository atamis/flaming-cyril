package grouplab;

import java.util.Iterator;
import java.util.LinkedList;

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
	public static int STALEMATE_THRESHOLD = 49;
	private static int anotherTurn = 0; //Keeps track of whether or not the player gets another turn
	//0 indicates nothing of note happened and no extra turn
	//1 indicates a piece was taken, so extra turn
	//2 indicates that the piece was just promoted to king so it doesn't get another turn either way

	public enum Side{BLACK, RED;

	public Side opponent() {
		if(this == BLACK) return RED;
		else return BLACK;
	}}
	static int[] redDirection = { 0, 1 };
	static int[] blkDirection = { 2, 3 };
	static int[] kingDirection = { 0, 1, 2, 3 };

	public static int[] getDirections(int id) {
		switch (id) {
		case(1):
			return blkDirection;
		case(3):
			return redDirection;
		case(2):
		case(4):
			return kingDirection;
		}
		return null;
	}

	// tests if a player owns a piece at a specific index
	public static boolean ownsPiece(Board b, int index, Side p) {
		int piece = b.pieceAt(index);
		if (piece == 0) {
			return false;
		}
		if (p == Side.BLACK) {
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
	public static int getAdjacent(Board b, int index, int direction) {
		if ((index < 0) || (index >= Math.pow(b.size, 2))) {
			//System.out.printf("Index out of bounds: %d\n", index);
			return -1;
		}
		switch(direction) {
			// north west
			case(0):
				if  ((b.onFirstRow(index) == true)  || (b.onFirstCol(index) == true))
						return -1;
				return index - (b.size + 1);
			// north east
			case(1):
				if ((b.onFirstRow(index) == true) || (b.onLastCol(index) == true))
					return -1;
				return index - (b.size - 1);
			case(2):
				if ((b.onLastRow(index) == true) || (b.onFirstCol(index) == true))
					return -1;
				return index + (b.size - 1);
			case(3):
				if ((b.onLastRow(index) == true) || (b.onLastCol(index) == true))
					return -1;
				return index + (b.size + 1);
			default:
				System.out.printf("%d is not a valid direction!", direction);
				return -1;
		}
	}

	// get a legal moves for a player
	public static LinkedList<Move> getLegalMoves(Board b, Side p) {
		LinkedList<Move> result = new LinkedList<Move>();
		boolean canJump = false;

		for (int x = 0; x < b.size; x++) { //Gabriel changed to b.size
			for (int y = 0; y < b.size; y++) { //Gabriel changed to b.size

				int coord = b.convertCoord(x, y);


				if (ownsPiece(b, coord, p)) {
					//System.out.printf("Checking piece at %d\n", coord);
					for (int dir : getDirections(b.pieceAt(coord))) {
						int adj = getAdjacent(b, coord, dir);
						//System.out.printf("Got adjacent at %d, %d\n", coord, adj);
						if (adj == -1) {
							continue;
						}
						//System.out.printf("Testing: (ID:%d, Loc:%d, Dir:%d Adj: %d)\n", b.pieceAt(coord), coord, dir, adj);
						// test if a jump can be made
						if ((b.pieceAt(adj) != 0) && (!ownsPiece(b, adj, p))) {

							int adj2 = getAdjacent(b, adj, dir);
							// index out of bounds
							if (adj2 == -1) {
								continue;
							}
							else if (b.pieceAt(adj2) == 0) {
								// delete any non-jumps
								canJump = true;
								result.add(new Move(coord, adj2, b.size));
								Iterator<Move> iterator = result.iterator();
								while(iterator.hasNext()){
									Move m = iterator.next();
									if(m.length() == 1) iterator.remove();
								}
							}
						} else if ((b.pieceAt(adj) == 0) && (canJump == false)) {
							result.add(new Move(coord, adj, b.size));
						}
					}
				}
			}
		}
		return result;
	}


	// applies a move to the current board.
	public static Board applyMove(Board b, Move m, Side s) {
		Board result = new Board(b);
		result.stalemateCount = b.stalemateCount + 1;
		if(m.length() > 1) b.stalemateCount = 0;
		if (s == Side.BLACK) {
			if ((b.pieceAt(m.o) == 1) && (b.onLastRow(m.d) == true)) {
				result.setPiece(m.d, 2);
				anotherTurn = 2; //A king was created
				// indicate end of turn
			} else {
				result.setPiece(m.d, b.pieceAt(m.o));
			}
			result.removePiece(m.o);
		} else if (s == Side.RED) {
			if ((b.pieceAt(m.o) == 3) && (b.onFirstRow(m.d) == true)) {
				result.setPiece(m.d, 4);
				anotherTurn = 2; //A king was created
				// indicate end of turn
			} else {
				result.setPiece(m.d, b.pieceAt(m.o));
			}
			result.removePiece(m.o);
		}

		// delete the piece if a jump
		int coord = 0;
		if (m.length() > 1) {
			//System.out.printf("Removing piece from jump\n");
			for (int i=0; i<4; i++) {
				coord = getAdjacent(b, m.o, i);
				if(coord == -1) continue;
				if (getAdjacent(b, coord, i) == m.d)
					break;
			}
			if (anotherTurn != 2) { //if the piece was not just promoted to king
				for (Move mv : getLegalMoves(result, s)) {
					if (mv.length() > 2) {
						anotherTurn = 1; //A piece was taken and another jump is possible
						break;
					} else {
						anotherTurn = 0; //Another jump is not possible
					}
				}
			}
			result.removePiece(coord);
		}
		return result;
			
	}

	// tests if a given board state is a winning board state
	public static boolean canMove(Board b, Side s) {
		if (getLegalMoves(b, s).size() > 0)
			return true;
		return false;
	}

	// sets up the initial board state
	public static Board setup(int size) {
		Board b = new Board(size);
		// Black pieces
		for (int r=0; r < 3; r++) {
			for (int c=0; c < b.size; c++) {
				if (r % 2 == 0) {
					if (c % 2 != 0) b.setPiece(b.convertCoord(c, r), 1);
				} else {
					if (c % 2 == 0) b.setPiece(b.convertCoord(c, r), 1);
				}
			}
		}
		// Red pieces
		for (int r=b.size-3; r < b.size; r++) {
			for (int c=0; c<b.size; c++) {
				if (r % 2 == 0) {
					if (c % 2 != 0) b.setPiece(b.convertCoord(c, r), 3);
				} else {
					if (c % 2 == 0) b.setPiece(b.convertCoord(c, r), 3);
				}
			}
		}
		return b;
	}

	public static void coordToString(Board b, int coord) {

		int x = coord % b.size;
		int y = (int)Math.floor(coord / b.size);
        //System.out.printf("%c%d", (char)(64 + y), x);
    }

    // plays a game of checkers
    public static void play(Player player1, Player player2, int size) {
        boolean gameover = false;
        Board board = setup(size);
        Player plyr = player1;
        Side current = Side.BLACK;

        coordToString(board, board.convertCoord(3, 2));


        while (!gameover) {
        	board.show();
        	if(board.stalemateCount > STALEMATE_THRESHOLD){
        		System.out.println("Stalemate. You both suck.");
        		gameover = true;
        	}
            if (!canMove(board, current)) { // not working
                gameover = true;
                System.out.printf("%s wins!", current.opponent());
                break;
            }

            Move m = plyr.queryMove(board, getLegalMoves(board, current));

            while (getLegalMoves(board, current).contains(m) == false) {
            	System.out.printf("Move: O:(%d, %d), D:(%d, %d)(\n", (m.o % board.size), (m.o / board.size), (m.d & board.size), (m.d /board.size)); ///rito delivers
            	//System.out.printf("Move: (%d, %d)\n", m.o, m.d);
            	System.out.printf("Invalid move! Try again.\n");
            	m = plyr.queryMove(board, getLegalMoves(board, current));
            }

            board = applyMove(board, m, current);




            if (anotherTurn != 1) { //If  a piece was not taken or if the piece that took it became a king
	            current = current.opponent();

	            if (plyr == player1) {
	                plyr = player2;
	            } else {
	                plyr = player1;
	            }
	        }
	        anotherTurn = 0;
        }

    }
}
