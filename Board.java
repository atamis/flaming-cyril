package grouplab;

/**
 * Basic board constructor class used for a game of checkers (Draughts)
 * 
 * @author Nick Care
 * @author Andrew Amis
 * @author Gabe Appleby
 * @author Sam Goree
 */

public class Board {
  int x, y = 0;
  
  // constructs a board with a bounds height and width
  public Board(int height, int width) {
    x = height;
    y = width;
  }
  
  // constructs a copy of a Board b
  public Board(Board b) {
  
  }
  
  public enum PIECE {
    WHITE_PAWN,
    WHITE_KING,
    BLACK_PAWN,
    BLACK_KING;
  }
  
  // internal class to hold details about tiles on the board
  public class Tile() {
  
  }
  
  public boolean gameOver() {
    
  }
  
  // returns a string based form of a Board
  public String toString() {
    return "";
  }
  
}
