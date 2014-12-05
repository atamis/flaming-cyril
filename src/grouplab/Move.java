package grouplab;

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