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
        String comma; //String of what was between X and Y value, should be a comma
        
        length = input.length(); //Length is the input's length
        
        //Try to grab substrings of the X, separator, and Y
        try {
            tempXS = input.substring(0,1); //X value
            tempYS = input.substring(1,2); //Y Value
            comma = input.substring(2,3); //Should be a comma
        } catch (IndexOutOfBoundsException e) {
            return -1; //Return fail
        }
        
        
        if ((length != 3) || (comma != ",")) { //If the length of the arguements is not 3, and the second arguement is not ','
            return -1; //Return fail
        }
        
        
        try { //Try to turn the first and second argument into integers
            tempX = Integer.parseInt(tempXS, 10); //10 means base 10
            tempY = Integer.parseInt(tempYS, 10); //10 means base 10
        } catch (NumberFormatException e) {
            return -1; //Return fail;
        }
        
        if ((tempX > 7) || (tempY > 7)) {
            return -1; //Return fail
        }
        
        
        coord = b.convertCoord(tempX, tempY); //The coordinates to return
        
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
