package grouplab;

import java.util.Scanner;
import java.util.LinkedList;

/**
* Class that will handle user input
*
* @author Nick Care
* @author Andrew Amis
* @author Gabe Appleby
* @author Sam Goree
*/
public class HumanPlayer extends Player {

    public Move queryMove(Board b, LinkedList<Move> moves) {
        Scanner in = new Scanner(System.in);

        LinkedList<String> mlist = new LinkedList<Strings>;

        for (Move m : moves) {
            String move = "(" + coordToString(b, m.o) + ", "coordToString(b, m.d) + ")"
            mlist.add(move);
        }

        int mid = InputHelper.queryMenu(in, "Valid Moves", mlist);

        return moves.get(mid);
    }

    public String getName() {

    }

    public void setName(String str) {

    }
}
