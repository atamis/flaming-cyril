package grouplab.heuristics;

import grouplab.Board;
import grouplab.Checkers.Side;
import grouplab.ComputerPlayer;

public class NaivePlayer extends ComputerPlayer {

	public NaivePlayer(Side s, int d) {
		super(s,d);
		this.name = "NaivePlayer";
	}

	@Override
	public int heuristic(Board b) {
		return 0;
	}

}
