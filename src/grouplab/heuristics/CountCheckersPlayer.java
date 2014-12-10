package grouplab.heuristics;

import grouplab.Board;
import grouplab.Checkers.Side;
import grouplab.ComputerPlayer;

public class CountCheckersPlayer extends ComputerPlayer {

	private int pawn_value;
	private int king_value;

	public CountCheckersPlayer(Side s, int pawn_value, int king_value, int depth) {
		super(s, depth);
		this.pawn_value = pawn_value; 
		this.king_value= king_value;
	}

	@Override
	public int heuristic(Board b) {
		int score = 0;
		for (int x = 0; x < b.size; x++) {
			for (int y = 0; y < b.size; y++) {
				switch(b.pieceAt(b.convertCoord(x, y))) {
				case(1):
					// black normal
					score = score + pawn_value;
					break;
				case(2):
					// Black king
					score = score + king_value;
					break;
				case(3):
					// Red normal
					score = score - pawn_value;
					break;
				case(4):
					// Red king
					score = score - king_value;
					break;
				}
			}
		}

		return score;
	}

}
