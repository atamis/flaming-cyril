package grouplab;


import grouplab.Checkers.Side;
import grouplab.heuristics.*;

import java.util.LinkedList;
import java.util.Scanner;

/**
* Game handles setting up all of the initial game variables and user
* interface. No checkers logic should ever make its way in here.
*
* @author Nick Care (2014)
* @author Sam Goree (2014)
* @author Andrew Amis (2014)
* @author Gabe Appleby (2014)
*/
public class Game {
	public static boolean silent;
	public static void main(String[] args) {
		//silent = true;
		// player objects
		Player player1 = null; Player player2 = null;
		Scanner input = new Scanner(System.in);

		LinkedList<String> controllers = new LinkedList<String>();
		controllers.add(0, "HumanPlayer");
		controllers.add(1, "NaivePlayer"); //naive - all moves are 0
		controllers.add(2, "CCPlayer"); //counts checkers weighted for kings
		controllers.add(3, "BCCPlayer"); //same as CCPlayer with set weights
		controllers.add(4, "WCCPlayer"); //I don't know what this does - please let me know
		controllers.add(5, "CWCCPlayer"); //I don't know what this does - please let me know
		controllers.add(6, "NCCPlayer"); //counts checkers weighted all the same


		// setup players
		int result = 0;

		// player1
		result = InputHelper.queryMenu(input, "Who should play on the black side?", controllers);

		// if its a human player
		if (result == 0) {
			player1 = new HumanPlayer();
			String name = InputHelper.queryStr(input, "Enter your name: ");
			player1.setName(name);
		} else {
			int depth = InputHelper.queryMenu(input, "Depth?");
			switch (result) {
				case 1:
					player1 = new NaivePlayer(Side.BLACK, depth);
					break;
				case 2:
					player1 = new WeightedCountCheckersPlayer(Side.BLACK, 1, 2, depth);
					break;
				case 3:
					player1 = new BetterCountCheckersPlayer(Side.BLACK, depth);
					break;
				case 4:
					player1 = new WeightedCountCheckersPlayer(Side.BLACK, 1, 3, depth);
					break;
				case 5:
					player1 = new CenterWeightedCountCheckersPlayer(Side.BLACK, 1, 3, depth);
					break;
				case 6:
					player1 = new NaiveCountCheckersPlayer(Side.BLACK, depth);
					break;
			}
			
		}

		// player2
		result = InputHelper.queryMenu(input, "Who should play on the red side?", controllers);
		if (result == 0) {
			player2 = new HumanPlayer();
			String name = InputHelper.queryStr(input, "Enter your name: ");
			player2.setName(name);
		} else {
			int depth = InputHelper.queryMenu(input, "Depth?");
			switch (result) {
			case 1:
				player2 = new NaivePlayer(Side.RED, depth);
				break;
			case 2:
				player2 = new WeightedCountCheckersPlayer(Side.RED, 1, 2, depth);
				break;
			case 3:
				player2 = new BetterCountCheckersPlayer(Side.RED, depth);
				break;
			case 4:
				player2 = new WeightedCountCheckersPlayer(Side.RED, 1, 3, depth);
				break;
			case 5:
				player2 = new CenterWeightedCountCheckersPlayer(Side.RED, 1, 3, depth);
				break;
			case 6:
				player2 = new NaiveCountCheckersPlayer(Side.RED, depth);
				break;
			}
			
		}
		int size = InputHelper.queryMenu(input, "What should the board size be?");

		Checkers.play(player1, player2, size);
	}
}
