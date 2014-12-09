package grouplab;

/**
* Data structure for a Move in a checkers game
*
* @author Nick Care (2014)
* @author Andrew Amis (2014)
* @author Sam Goree (2014)
* @author Gabe Appleby (2014)
*/
public class Move {
	int o = -1; int d = -1;

	public Move(int origin, int dest) {
		o = origin;
		d = dest;
	}

	public int length() {
		if ((Math.abs(o - d)-8) > 2)
			return 2;
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if ((o == ((Move)obj).o) && (d == ((Move)obj).d)) {
			return true;
		}
		return false;
	}
}
