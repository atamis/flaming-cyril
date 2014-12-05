package grouplab.heuristics;

import grouplab.Board;
import grouplab.Checkers.Side;
import grouplab.ComputerPlayer;

public class NaivePlayer extends ComputerPlayer {

	public NaivePlayer(Side s) {
		super(s);
	}

	@Override
	public int heuristic(Board b) {
		return 0;
	}

}
