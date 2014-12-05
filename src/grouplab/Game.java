package grouplab;

public class Game {
	public void main(String[] args){
		Player p1 = new HumanPlayer();
		Player p2 = new HumanPlayer();
		Checkers.play(p1, p2);
	}
}
