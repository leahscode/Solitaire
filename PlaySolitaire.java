import java.util.Scanner;

public class PlaySolitaire {

    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        // First we get a name for the file where the user can save games.
        // We need to do it only once, so it's done before entering the loop.
        // Note that the game file can be anything; if it doesn't exist, it will be created.
        System.out.println("Let's have fun!");
        System.out.println("But first, please enter the name of your game file:");
        String fileName = keyboard.nextLine();

        // We're playing at least once, so I use a do-while loop.
        // The loop runs until the user does not want to play another round.
        do {
            // The object instance is declared within an if-else, using either of the 2 constructors
            Solitaire game;
            
            System.out.println("Let's play!");
            System.out.println("We have an infinite number of decks!");
            System.out.println("Do you want to use your own deck? (Y/N)");
            if (confirm()) {
                // User wants to supply a deck:
                // The argument is a method that returns a char[] array from the string input.
                game = new Solitaire(getUserDeck());
            } else {
                game = new Solitaire();
            }
            // Play the game, then print the game progress.
            String trackGame = game.play();
            System.out.println(trackGame);

            // Then, ask if the player wants it written to a file.
            System.out.println("Save game? (Y/N)");
            if (confirm()) {
                game.saveGame(trackGame, fileName);
            }  
    
            // Ask if another round should be played.
            // Any input that does not start with the letter "Y" will exit the loop.
            System.out.println("Play again? (Y/N)");    
        } while (confirm());
    } // end main


    // It's just easier and cleaner to use this method for all confirmations above
    private static Boolean confirm() {
        char answer = keyboard.nextLine().charAt(0);
        return answer == 'y' || answer == 'Y';
    }

    // When the user wants to supply a deck, to be used for the second constructor.
    public static char[] getUserDeck() {
        System.out.println("Please enter your deck as a string of characters, with no spaces.");
        char[] userDeck;

        // Get the input and convert it to an array.
        userDeck = keyboard.next().toCharArray();
        // Skip the nextLine, so it's not confused by the interpreter for later input.
        keyboard.nextLine();

        return userDeck;
    }

    
}