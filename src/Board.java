package grouplab;

import java.util.Hashtable;

public class Board {
	Hashtable<Integer, Integer> store;
	int size = 0;
	
	// creates a standard checkers board of dimension 8
	public Board() {
		store = new Hashtable<Integer, Integer>(8*8);
	}
	
	// creates a checkers board of dimension size
	public Board(int s) {
		size = s;
		store = new Hashtable<Integer, Integer>(s*s);
	}
	
	// creates a clone of board b
	public Board(Board b) {
		store = b.store;
		size = b.size;
	}
	
	// method to convert coordinate into human readable format
	public int convertCoord(String coord) {
		if (coord.length() > 2) {
			System.out.printf("Invalid coordinate format (%s)!", coord);
		}
		// TODO
		return 0;
	}
	
	// converts x, y coords to board index
	public int convertCoord(int x, int y) {
		return (x + (y * size));
	}
	
	// places a piece with id at index
	public void setPiece(int index, int id) {
		if (store.get(index) != 0) {
			System.out.printf("Piece already exists at %d!", index);
			return;
		}
		store.put(index, id);
	}
	
	// detects if a piece is on the last row
	public boolean onLastRow(int index) {
		return false;
	}
	
	// detects if a piece is on the first row
	public boolean onFirstRow(int index) {
		return false;
	}
	
	// detects if a piece is on the first column
	public boolean onFirstCol(int index) {
		return false;
	}
	
	// detects if a piece is on the last column
	public boolean onLastCol(int index) {
		return false;
	}

	// get the piece at a given location
	public int pieceAt(int index) {
		return store.get(index);
	}
	
	// prints the board to stdout
	public void show() {
		// TODO
	}
	
	// clears all pieces from the board
	public void clear() {
		for (int i = 0; i < size; i++) {
			store.put(i, 0);
		}
	}
	
	// tests if two boards are equal
	@Override
	public boolean equals(Object o) {
		return false;
	}
}
