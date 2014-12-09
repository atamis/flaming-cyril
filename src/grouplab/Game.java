package grouplab;

import java.util.Scanner;

public class Game {

	//I got bored of making a new variable for each function call halfway through this one, so 
	//put your hands together for style inconsistencies (#you'rewelcome)


	//Main method the game
	public static void main(String[] args){

		int playerOneSelection; //Integer selection of the type of player one
		int playerTwoSelection; //Integer selection of the type of player Two
		Player playerOne = null; //Player one
		Player playerTwo = null; //Player two
		Scanner humanInput;  //A scanner to grab input
		InputInterpretor inputHelper; //An input interpretor to make sure the user can follow instructions

		humanInput = new Scanner(System.in);  //Initialize the scanner
        inputHelper =  new InputInterpretor();  //Initialize the inputinterpretor

		System.out.println("Lets play some Checkers."); //Yeay?
		
		System.out.println("Would you like player one to be: 1 Human, 2 Min-Max, 3 Andrew");  //Which type of player
		System.out.println("Type the assigned integer of your choice."); //Hold the end user's hand
		playerOneSelection = inputHelper.intToPlayer((humanInput.nextInt())); //Grab the input, then make sure its not wrong
		

		//IMPORTANT UNKOWN RESULT WITH CHARACTER, INVESTIGATE
		while (playerOneSelection == -1) { //While the input is invalid
			System.out.println("Invalid input"); //Tell the user that they are a moron
			playerOneSelection = inputHelper.intToPlayer(humanInput.nextInt()); //Try to grab a selection again
		}

		switch(playerOneSelection) { //Create the correct player
			case 1: playerOne = new HumanPlayer();
			//case 2: p1 = new Min-Max thing();
			//case 3: p1 = new Andrew thing();
		}

		System.out.println("Would you like player two to be: (1) Human, (2) Min-Max, (3) Andrew"); //Which type of player
		System.out.println("Type the assigned integer of your choice."); //Hold the end user's hand
		playerTwoSelection = inputHelper.intToPlayer(humanInput.nextInt()); //Grab the input, then make sure its not wrong


		//IMPORTANT UNKOWN RESULT WITH CHARACTER, INVESTIGATE
		while (playerTwoSelection == -1) { //While the input is invalid
			System.out.println("Invalid input"); //Tell the user that they are a moron
			playerTwoSelection = inputHelper.intToPlayer(humanInput.nextInt()); //Try to grab a selection again
		}

		switch(playerTwoSelection) { //Create the correct player
			case 1: playerTwo = new HumanPlayer();
			//case 2: p1 = new Min-Max thing();
			//case 3: p1 = new Andrew thing();
		}
		
		Checkers.play(playerOne, playerTwo); //Play the game


	}
}
