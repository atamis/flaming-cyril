package grouplab;

/**
* Interface that should be implemented by all players
*
* @author Nick Care (2014)
* @author Andrew Amis (2014)
* @author Sam Goree (2014)
* @author Gabe Appleby (2014)
*/
public abstract class Player {
    // asks a player to select a move from a list of valid moves
    public abstract Move queryMove(Board b, LinkedList<Move> legalMoves);

    // gets the player's name
    public abstract String getName();

    // sets the player's name
    public abstract void setName();
}
