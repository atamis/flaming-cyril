
package grouplab;

import java.util.List;
import java.util.LinkedList;

import grouplab.Checkers.Move;
import grouplab.Checkers.Side;


public abstract class ComputerPlayer implements Player{
	
	static int DEPTH = 5;
	Side s;
	String name;
	
	public ComputerPlayer(Side s){
		this.s = s;
		name = "DefaultComputer";
	}
	
	public ComputerPlayer(Side s, String name){
		this.s = s;
		this.name = name;
	}
		
	//figures out the best move
	public Move queryMove(Board b, LinkedList<Move> moves){
		Move bestMove = null;
		int best = (s == Side.BLACK)?Integer.MIN_VALUE:Integer.MAX_VALUE, eval;
		for(Move m : moves){
			if(s == Side.BLACK){
				//evaluate the world where we take that move 
				eval = alphabeta(Checkers.applyMove(b, m, s), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, s);
				//if our new world is better for black than the best one found so far, keep it around
				if(eval >= best){
					bestMove = m;
					best = eval;
				}
			}else{
				//evaluta
				eval = alphabeta(Checkers.applyMove(b, m, s), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, s);
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
		//base case
		if(depth == 0 || moves.size() == 0) return heuristic(b);
		if(s == Side.BLACK){
			for(int i = 0; i < moves.size(); i++){
				temp = Checkers.applyMove(b, moves.get(i), s);
				alpha = Math.max(alpha, alphabeta(temp, depth - 1, alpha, beta, 
								((s == Side.BLACK)? Side.BLACK : Side.RED)));
				if(beta <= alpha) break;
			}
			return alpha;
		}else{
			for(int i = 0; i < moves.size(); i++){
				temp = Checkers.applyMove(b, moves.get(i), s);
				beta = Math.min(beta, alphabeta(temp, depth - 1, alpha, beta,
								(s == Side.BLACK)? Side.BLACK : Side.RED));
				if(beta <= alpha) break;
			}
			return beta;
		}
	}
	
	// the player's name
	public String getName(){ return name;}
	
	//heuristic 
	public abstract int heuristic(Board b);
	
}