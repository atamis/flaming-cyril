package grouplab;

import java.util.concurrent.Callable;

class CheckersTask implements Callable<String> {
	private int board;
	private ComputerPlayer player2Q;
	private ComputerPlayer player1Q;

	public CheckersTask(ComputerPlayer p1, ComputerPlayer p2, int board) {
		this.player1Q = p1;
		this.player2Q = p2;
		this.board = board;
	}
	
	@Override public String call() {
		Checkers.play(player1Q, player2Q, board);
		System.out.println(player1Q.getName() + ", " + player1Q.s + ", depth " + player1Q.depth + ". Against " + player2Q.getName() + ", " + player2Q.s + ", depth " + player2Q.depth + "." + " Board " + board + "\n");

		return null;
	}
	
}