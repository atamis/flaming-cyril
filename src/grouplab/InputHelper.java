package grouplab;

/**
 * Helper class for input processing for Checkers game
 *
 * @author Nick Care (2014)
 * @author Andrew Amis (2014)
 * @author Sam Goree (2014)
 * @author Gabe Appleby (2014)
 */
public class InputHelper {

    /*
     * prints a formatted menu with options given
     * if the length of the options is one, it will default to that option
     * @param msg - message to be printed to the user
     * @param options - options - posistion in list is the id
     */
    public static int queryMenu(Scanner in, String msg, LinkedList<String> options) {
        System.out.printf("%s\n", msg); // print out the message to stdout

        while (true) {
            for (String option : options) {
                System.out.printf("\t%s\n", option);
            }

            System.out.printf("Enter an option from the list above: ")
            String response = in.nextLine();

            for (String opt : options) {
                if (response.equals(opt)) {
                    return options.indexOf(reponse);
                }
            }
            System.out.printf("Input did not match any option. Try again.\n")
        }
    }

    /*
     * gets a string from stdin
     */
    public String queryStr(Scanner in, String msg) {
        System.out.printf("%s\n", msg);

        String response = in.nextLine();
        if (response.length() == 0) {
            System.out.printf("Please enter a valid string!"\n);
        }
        return response;
    }

    /*
     * converts a string based coordinate to an integer representation
     */
    public static int stringToCoord(Board b, String input) {
        int y = ((int)input.substring(0,1).toUpperCase())-64;
        if ((y < 64) || (y > 90)) {
            System.out.printf("Invalid row coordinate!");
            return -1;
        }
        int x = ((int)input.substring(1,2))-48;
        if (x < 0) || (x > 8) {
            System.out.printf("Invalid column coordinate!");
            return -1;
        }
        return b.convertCoord()
    }

    /*
     * converts and index to a string representation
     */
     public static void coordToString(Board b, int coord) {
         int x = coord % b.size;
         int y = (int)Math.floor(coord / b.size);
         System.out.printf("%c%d", (char)(64 + y), x);
     }
}
