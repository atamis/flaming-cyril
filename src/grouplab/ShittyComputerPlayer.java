/*
 * Implements ComputerPlayer with a heuristic of "return 0"
 * Not a very smart player
 */

package grouplab;

import grouplab.Checkers.Side;

public class ShittyComputerPlayer extends ComputerPlayer {

	public ShittyComputerPlayer(Side s) {
		super(s);
	}

	@Override
	public int heuristic(Board b) {
		return 0;
	}

}
