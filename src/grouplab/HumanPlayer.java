package grouplab;

import java.util.LinkedList;
import java.util.Scanner;
/**
* Class that will handle user input
*
* @author Nick Care
* @author Andrew Amis
* @author Gabe Appleby
* @author Sam Goree
*/
public class HumanPlayer extends Player {
    @Override
    public Move queryMove(Board b, LinkedList<Move> moves) {
        Scanner in = new Scanner(System.in);

        LinkedList<String> mlist = new LinkedList<String>();

        for (Move m : moves) {
            String move = "(" + InputHelper.coordToString(b, m.o) + ", " + InputHelper.coordToString(b, m.d) + ")";
            mlist.add(move);
        }

        int mid = InputHelper.queryMenu(in, "Valid Moves", mlist);

        return moves.get(mid);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String str) {

    }
}
