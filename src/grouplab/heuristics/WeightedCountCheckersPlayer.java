package grouplab.heuristics;

import grouplab.Board;
import grouplab.Checkers.Side;
import grouplab.ComputerPlayer;

public class WeightedCountCheckersPlayer extends ComputerPlayer {

	private int pawn_value;
	private int king_value;

	public WeightedCountCheckersPlayer(Side s, int pawn_value, int king_value) {
		super(s);
		this.pawn_value = pawn_value; 
		this.king_value= king_value;
	}

	@Override
	public int heuristic(Board b) {
		int score = 0;
		for (int x = 0; x < b.size; x++) {
			for (int y = 0; y < b.size; y++) {
				double weight = distance(x, y, b.size/2, b.size/2);
				switch(b.pieceAt(b.convertCoord(x, y))) {
				case(1):
					score = (int) (score + pawn_value * weight);
					break;
				case(2):
					score = (int) (score + king_value * weight);
					break;
				case(3):
					score = (int) (score - pawn_value * weight);
					break;
				case(4):
					score = (int) (score - king_value * weight);
					break;
				}
			}
		}

		return score;
	}

	private double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2-y1, 2));
	}
}
