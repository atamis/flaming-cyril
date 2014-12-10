package grouplab;

public class Move {
	int o = -1; int d = -1; int s = 0;

	public Move(int origin, int dest, int size) {
		o = origin;
		d = dest;
		s = size;
	}

	public int length() {
		if ((Math.abs(o - d)-s) > 2)
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
