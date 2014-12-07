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

	private boolean pieceTaken = false; //Keeps track of if a piece was taken, because applyMove returns a board
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
		if ((index < 0) || (index > Math.pow(b.size, 2))) {
			System.out.printf("Index out of bounds: %d\n", direction);
			System.exit(1);
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
        
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

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
							System.out.printf("Possible jump\n");
							int adj2 = getAdjacent(b, adj, dir);
							// index out of bounds
							if (adj2 == -1) {
								continue;
							} else if (b.pieceAt(adj2) == 0) {
								System.out.printf("Found jump/n");
								// delete any non-jumps
								for (Move m : result) {
									if (m.length() == 1)
										result.remove(m);
								}
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
	public static Board applyMove(Board b, Move m, Side s) {
		Board result = new Board(b);

		if (s == Side.BLACK) {
			if ((b.pieceAt(m.o) == 1) && (b.onLastRow(m.d) == true)) {
				result.setPiece(m.d, 2);
				// indicate end of turn
			} else {
				result.setPiece(m.d, b.pieceAt(m.o));
			}
			result.removePiece(m.o);
		} else if (s == Side.RED) {
			if ((b.pieceAt(m.o) == 3) && (b.onFirstRow(m.d) == true)) {
				result.setPiece(m.d, 4);
				// indicate end of turn
			} else {
				result.setPiece(m.d, b.pieceAt(m.o));
			}
			result.removePiece(m.o);
		}

		// delete the piece if a jump
		int coord = 0;
		if (m.length() > 1) {
			System.out.printf("Removing piece from jump\n");
			for (int i=0; i<4; i++) {
				coord = getAdjacent(b, m.o, i);
				if (getAdjacent(b, coord, i) == m.d)
					break;
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
	public static Board setup() {
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
        System.out.printf("%c%d", (char)(64 + y), x);
    }
	
    // plays a game of checkers
    public static void play(Player player1, Player player2) {
        boolean gameover = false;
        Board board = setup();
        Player plyr = player1;
        Side current = Side.BLACK;
        
        coordToString(board, board.convertCoord(3, 2));
        
        /*
        while (!gameover) {
            if (!canMove(board, current)) { // not working
                gameover = true;
                System.out.printf("%s wins!", current.opponent());
                break;
            }
             
            Move m = plyr.queryMove(board, getLegalMoves(board, current));
            
            while (getLegalMoves(board, current).contains(m) == false) {
            	System.out.printf("Move: (%d, %d)\n", m.o, m.d);
            	System.out.printf("Invalid move! Try again.\n");
            	m = plyr.queryMove(board, getLegalMoves(board, current));
            }
            
            board = applyMove(board, m, current);
            
            board.show();
            
            // TODO
            // still need to check if a move takes a piece

            current = current.opponent();

            if (plyr == player1) {
                plyr = player2;
            } else {
                plyr = player1;
            }
        }
        */
    }
}
