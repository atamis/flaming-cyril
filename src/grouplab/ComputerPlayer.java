package grouplab;

import grouplab.Checkers.Side;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
* Interface that should be implemented by all non-human players
*
* @author Nick Care (2014)
* @author Andrew Amis (2014)
* @author Sam Goree (2014)
* @author Gabe Appleby (2014)
*/
public class ComputerPlayer implements Player {
	Side s;
	String name;
	int depth;
	Random r;

	public ComputerPlayer(Side s) {
		this.s = s;
		this.name = "DefaultComputer";
		r = new Random();
		depth = 5;
	}
	
	public ComputerPlayer(Side s, int d){
		this(s);
		depth = d;
	}

	public ComputerPlayer(Side s, String name) {
		this.s = s;
		this.name = name;
		r = new Random();
		depth = 5;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String str) {
		this.name = str;
	}

	//heuristic
	public int heuristic(Board b) {
		return 0;
	}

	//figures out the best move
	//if a move takes a piece, it should keep the side the same
	public Move queryMove(Board b, LinkedList<Move> moves){

		Move bestMove = null;
		int best = (s == Side.BLACK)?Integer.MIN_VALUE:Integer.MAX_VALUE, eval;
		for(Move m : moves){
			if(s == Side.BLACK){
				//evaluate the world where we take that move
				eval = alphabeta(Checkers.applyMove(b, m, s), depth, Integer.MIN_VALUE, Integer.MAX_VALUE, s);
				//if our new world is better for black than the best one found so far, keep it around
				if(eval >= best){
					bestMove = m;
					best = eval;
				}
			}else{
				//evaluate
				eval = alphabeta(Checkers.applyMove(b, m, s), depth, Integer.MIN_VALUE, Integer.MAX_VALUE, s);
				if(eval <= best){
					bestMove = m;
					best = eval;
				}
			}
		}
		return bestMove;
	}

	//implementation of minimax with alpha beta pruning, black is max
	private int alphabeta(Board b, int depth, int alpha, int beta, Side s){
		Board temp;
		List<Move> moves = Checkers.getLegalMoves(b, s);
		Collections.shuffle(moves, r);
		//moves.add(moves.remove(0));
		//base case
		if(depth == 0 || moves.size() == 0) return heuristic(b);
		if(s == Side.BLACK){
			for(int i = 0; i < moves.size(); i++){
				temp = Checkers.applyMove(b, moves.get(i), s);
				if(temp.stalemateCount > Checkers.STALEMATE_THRESHOLD) return Integer.MIN_VALUE;
				//this is the most obnoxious line of java I've ever written. I'm so proud :)
				alpha = Math.max(alpha, alphabeta(temp, depth - 1, alpha, beta,
						((moves.get(i).length() == 1)? ((s == Side.BLACK)? Side.BLACK : Side.RED):s)));
				if(beta <= alpha) break;
			}
			return alpha;
		}else{
			for(int i = 0; i < moves.size(); i++){
				temp = Checkers.applyMove(b, moves.get(i), s);
				if(temp.stalemateCount > Checkers.STALEMATE_THRESHOLD) return Integer.MAX_VALUE;
				beta = Math.min(beta, alphabeta(temp, depth - 1, alpha, beta,
						((moves.get(i).length() == 1)? ((s == Side.BLACK)? Side.BLACK : Side.RED):s)));
				if(beta <= alpha) break;
			}
			return beta;
		}
	}

	public boolean isHuman() {
		// TODO Auto-generated method stub
		return false;
	}
}
