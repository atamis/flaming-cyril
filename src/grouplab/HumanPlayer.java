package grouplab;

import java.util.LinkedList;
import java.util.Scanner;

public class HumanPlayer implements Player {
    
    public HumanPlayer() {
        
    }
    //Takes in a board and list of moves and returns a move
    public Move queryMove(Board b, LinkedList<Move> moves) {
        int origin; //Where the piece started
        int dest; //Where it wants to go
        Scanner humanInput; //A scanner to grab input
        InputInterpretor inputHelper; //An input interpretor to make sure the user can follow instructions
        Move theMove; //The move the player will make
        
        
        humanInput = new Scanner(System.in);  //Initialize the scanner
        inputHelper =  new InputInterpretor();  //Initialize the inputinterpretor

        
        System.out.println("Which piece would you like to move? Format: 2,3");
        origin = getInput(humanInput, inputHelper, b); //Grab the players input
        while (origin == -1) { //While origin is not a valid coordinate
            System.out.println("Invalid Input");
            System.out.println("Which piece would you like to move? Format: 2,3");
            origin = getInput(humanInput, inputHelper, b); //Grab the players input
        }
        
        System.out.println("Where would you like to move that? Format: 2,3");
        dest = getInput(humanInput, inputHelper, b); //Grab the players input
        while (dest == -1) { //While destination is not a valid coordinate
            System.out.println("Invalid Input");
            System.out.println("Which piece would you like to move? Format: 2,3");
            dest = getInput(humanInput, inputHelper, b); //Grab the players input
        }
        
        theMove = new Move(origin, dest); //Assign the move to theMove variable
        return theMove; //Return the move
    }
    
    //Gets the player input
    private int getInput(Scanner humanInput, InputInterpretor inputHelper, Board b) {
        int coord; //The coordinate
        String tempInput; //The input from the player
        
        tempInput= humanInput.nextLine(); //Grabs the raw input from the player
        coord = inputHelper.stringToCoord(tempInput, b); //Turns the raw inpt form the player into a move
        
        return coord; //returns the move
    }

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
