package grouplab;

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
    public void main(String[] args) {
        // player objects
        Player player1; Player player2;

        Scanner input = new Scanner(System.in);

        ArrayList<Player> controllers;
        controllers.add(0, HumanPlayer);
        controllers.add(1, ComputerPlayer);

        // setup players
        int result = 0;

        // player1
        result = InputHelper.queryMenu(input, "Who should play on the black side?", controllers);

        // if its a human player
        if (result == 0) {
            player1 = new HumanPlayer();
            String name = queryString("Enter your name: ");
            player1.setName(name);
        } else {
            player1 = new ComputerPlayer();
        }

        // player2
        result = InputHelper.queryMenu(input, "Who should play on the red side?", controllers);
        if (result == 0) {
            player2 = new HumanPlayer();
            String name = queryString("Enter your name: ");
            player.setName(name);
        } else {
            player2 = new ComputerPlayer();
        }

        Checkers.play(player1, player2);
    }
}
