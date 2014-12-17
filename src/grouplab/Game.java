package grouplab;


import grouplab.Checkers.Side;
import grouplab.heuristics.*;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	public static int GAME_MODE = 0; //0 is checkers, 1 is suicide checkers
	public static boolean silent;
	
	public static void main(String[] args) {
		boolean bulkTest = true;
		if (bulkTest == true) {
			silent = true;
			LinkedList<ComputerPlayer> playersB = new LinkedList<ComputerPlayer>();
			int depth = 6;
			//for (int depth = 2; depth < 9; depth+=6) {
				playersB.add(new NaivePlayer(Side.BLACK, depth ));
				playersB.add(new WeightedCountCheckersPlayer(Side.BLACK, 1, 2, depth));
				playersB.add(new BetterCountCheckersPlayer(Side.BLACK, depth));
				playersB.add(new WeightedCountCheckersPlayer(Side.BLACK, 1, 3, depth));
				playersB.add(new CenterWeightedCountCheckersPlayer(Side.BLACK, 1, 3, depth));
				playersB.add(new NaiveCountCheckersPlayer(Side.BLACK, depth));
			//}
			LinkedList<ComputerPlayer> playersR = new LinkedList<ComputerPlayer>();
			//for (int depth = 2; depth < 9; depth+=6) {
				playersR.add(new NaivePlayer(Side.RED, depth ));
				playersR.add(new WeightedCountCheckersPlayer(Side.RED, 1, 2, depth));
				playersR.add(new BetterCountCheckersPlayer(Side.RED, depth));
				playersR.add(new WeightedCountCheckersPlayer(Side.RED, 1, 3, depth));
				playersR.add(new CenterWeightedCountCheckersPlayer(Side.RED, 1, 3, depth));
				playersR.add(new NaiveCountCheckersPlayer(Side.RED, depth));
			//}
			ExecutorService executor = Executors.newFixedThreadPool(4);
			CompletionService<String> compService = new ExecutorCompletionService<>(executor);

			for (ComputerPlayer player1Q : playersB) {
				for (ComputerPlayer player2Q : playersR) {
					compService.submit(new CheckersTask(player1Q, player2Q, 8));
					compService.submit(new CheckersTask(player1Q, player2Q, 12));
					compService.submit(new CheckersTask(player1Q, player2Q, 13));
					compService.submit(new CheckersTask(player1Q, player2Q, 14));
					/*System.out.println(player1Q.getName() + ", " + player1Q.s + ", depth " + player1Q.depth + ". Against " + player2Q.getName() + ", " + player2Q.s + ", depth " + player2Q.depth + "." + " Board " + 8);
					Checkers.play(player1Q, player2Q, 8);
					System.out.println(player1Q.getName() + ", " + player1Q.s + ", depth " + player1Q.depth + ". Against " + player2Q.getName() + ", " + player2Q.s + ", depth " + player2Q.depth + "." + " Board " + 12);
					Checkers.play(player1Q, player2Q, 12);
					System.out.println(player1Q.getName() + ", " + player1Q.s + ", depth " + player1Q.depth + ". Against " + player2Q.getName() + ", " + player2Q.s + ", depth " + player2Q.depth + "." + " Board " + 15);
					Checkers.play(player1Q, player2Q, 13);
					System.out.println(player1Q.getName() + ", " + player1Q.s + ", depth " + player1Q.depth + ". Against " + player2Q.getName() + ", " + player2Q.s + ", depth " + player2Q.depth + "." + " Board " + 14);
					Checkers.play(player1Q, player2Q, 14);*/
					
				}
			}
			
			while(true) {
				try {
					Future<String> future = compService.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
		}
		else {
		
		
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
}
