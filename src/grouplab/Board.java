package grouplab;

import java.util.ArrayList;

public class Board {
	ArrayList<Integer> store;
	public int size = 0;
	
	// creates a standard checkers board of dimension 8
	public Board() {
		this(8);
	}
	
	// creates a checkers board of dimension size
	public Board(int s) {
		size = s;
		store = new ArrayList<Integer>();
		for(int i = 0; i < s*s; i++) store.add(i, 0);
	}
	
	// creates a clone of board b
	public Board(Board b) {
		//TODO: Fix this to actually copy the contents
		store= new ArrayList<Integer>(b.store);
		size = b.size;
	}
	
	// converts x, y coords to board index
	public int convertCoord(int x, int y) {
		return (x + (y * size));
	}
	
	// places a piece with id at index
	public boolean setPiece(int index, int id) {
		if (pieceAt(index) != 0) {
			System.out.printf("Piece already exists at %d!\n", index);
			return false;
		}
		store.set(index, id);
		return true;
	}
	
	public void removePiece(int index) {
		store.set(index, 0);
	}
	// get the id of a piece at location index
	public int pieceAt(int index) {
		return store.get(index);
	}
	
	// detects if a piece is on the last column
	public boolean onLastCol(int index) {
		if ((index + 1) % size == 0)
			return true;
		return false;
	}
	
	// detects if a piece is on the first column
	public boolean onFirstCol(int index) { 
		if ((index == 0) || (index % size == 0))
			return true;
		return false;
	}
	
	// detects if a piece is on the first row
	public boolean onFirstRow(int index) {
		if (index < size)
			return true;
		return false;
	}
	
	// detects if a piece is on the last row
	public boolean onLastRow(int index) {	
		if ((index > Math.pow(size, 2) - size))
			return true;
		return false;
	}

	// prints the board to stdout
	//A very dumb way to do this, but it was quick and it is not really important
	//to the logic.
	public void show() {
		System.out.print("\n   ");
		int i= 0;
		for (i = 0; i < size; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n   ");
		for (i = 0; i < size; i++) {
			System.out.print("_ ");
		}
		System.out.print("\n");
		for (int x = 0; x < size; x++) {
			System.out.print(x + " |");
			for (int y = 0; y < size; y++) {
				int index = this.convertCoord(y, x);
				System.out.print(getCharRep(store.get(index)) + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	//Returns a character representation of a board piece
	private char getCharRep(int intRep) {
		switch(intRep) {
			case 0: //Empty space
				return '.'; 
			case 1: //Black pawn
				return 'b';
			case 2: //Black king
				return 'B';
			case 3: //Red pawn
				return 'r';
			case 4: //Red king
				return 'R';
			default: //Error
				return 'E';
		}
	}
	
	// clears all pieces from the board
	public void clear() {
		for (int i = 0; i < size; i++) {
			store.set(i, 0);
		}
	}
	
	// TODO
	// tests if two boards are equal
	@Override
	public boolean equals(Object o) {
		return false;
	}
}
