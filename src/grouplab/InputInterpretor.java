package grouplab;

public class InputInterpretor {
    
    public static final int MIN_PLAYER_TYPE = 1;  //The minimum valid player selection
    public static final int MAX_PLAYER_TYPE = 3;  //The maximum valid player selection

    public InputInterpretor() {
        
    }
    
    //Takes in a string and board, returns a coordinate;
    //Returns -1 on failure
    public int stringToCoord(String input, Board b) {
        int length; //Temporary length
        int coord; //Temporary coordinate
        int tempX; //Temporary X value
        int tempY; //Temporary Y value
        String tempXS; //String of temporary X value
        String tempYS; //String of temporary Y value
        String[] splitInput; //The split input of the human player

        
        splitInput = input.split(","); //Split the input by a comma
        

        if (splitInput.length != 2) { //If the length of the arguements is not 2
            return -1; //Return fail
        }

        
        try { //Should be fine if first check passes, but never hurts to surround with try catch
            tempXS = splitInput[0]; //X value
            tempYS = splitInput[1]; //Y Value
        } catch (IndexOutOfBoundsException e) {
            return -1; //Return fail
        }
        
        try { //Try to turn the first and second argument into integers
            tempX = Integer.parseInt(tempXS, 10); //10 means base 10
            tempY = Integer.parseInt(tempYS, 10); //10 means base 10
        } catch (NumberFormatException e) {
        	//System.out.printf("Failed to parse");
            return -1; //Return fail;
        }
        
        if ( (tempX > b.size-1) || (tempX < 0) || (tempY > b.size-1) || (tempY < 0) ) {
        	//System.out.printf("out of bounds");
            return -1; //Return fail    
        }
        
        //System.out.println("TempX: " + tempX);
        //System.out.println("Tempy: " + tempY);
        coord = b.convertCoord(tempX, tempY); //The coordinates to return
        //System.out.println("Coord: " + coord);
        return coord; //return the coordinates
    }


    //Takes in an integer for player selection and makes sure its valid
    //Returns the number if valid, and -1 if invalid
    public int intToPlayer(int playerSelection) {
        if ((playerSelection < MIN_PLAYER_TYPE) || (playerSelection > MAX_PLAYER_TYPE)) {
            return -1; //Return fail
        }
        else {
            return playerSelection; //Return valid selection
        }
    }

}
