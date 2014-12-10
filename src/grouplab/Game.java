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
		silent = true;
		// player objects
		Player player1 = null; Player player2 = null;
		Scanner input = new Scanner(System.in);

		LinkedList<String> controllers = new LinkedList<String>();
		controllers.add(0, "HumanPlayer");
		controllers.add(1, "NaivePlayer");
		controllers.add(2, "CCPlayer");
		controllers.add(3, "BCCPlayer");
		controllers.add(4, "WCCPlayer");
		controllers.add(5, "CWCCPlayer");


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
			switch (result) {
				case 1:
					player1 = new NaivePlayer(Side.BLACK);
					break;
				case 2:
					player1 = new WeightedCountCheckersPlayer(Side.BLACK, 1, 2);
					break;
				case 3:
					player1 = new BetterCountCheckersPlayer(Side.BLACK);
					break;
				case 4:
					player1 = new WeightedCountCheckersPlayer(Side.BLACK, 1, 3);
					break;
				case 5:
					player1 = new CenterWeightedCountCheckersPlayer(Side.BLACK, 1, 3);
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
			switch (result) {
			case 1:
				player2 = new NaivePlayer(Side.RED);
				break;
			case 2:
				player2 = new WeightedCountCheckersPlayer(Side.RED, 1, 2);
				break;
			case 3:
				player2 = new BetterCountCheckersPlayer(Side.BLACK);
				break;
			case 4:
				player2 = new WeightedCountCheckersPlayer(Side.BLACK, 1, 3);
				break;
			case 5:
				player2 = new CenterWeightedCountCheckersPlayer(Side.BLACK, 1, 3);
				break;
		}
		}

		Checkers.play(player1, player2);
	}
}
