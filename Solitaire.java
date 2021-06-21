import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Solitaire {

    private ArrayList<Character> deck;
    private ArrayList<Character> table;
    private String trackGame;
    private int deckSize;

    // Two constructors, with either a random deck or one supplied by the user.
    // Both the deck and the table are an ArrayList with a maximum size of 52.

    // trackGame is a string. It tracks the whole game:
    // whatever is on the table; the deck and the open cards, at each round. 
    // This game does not require any player actions once it started. 
    // Therefore, the player receives the whole game progress in one single output.
    // The code "|[#]|" simulates the deck, and displays the number of cards still in the deck.
    public Solitaire() {
        deck = createDeck();
        table = new ArrayList<>(52);
        deckSize = deck.size();
        trackGame = "|[" + deckSize + "]| ";
    }    

    public Solitaire(char[] myDeck) {
        deck = createDeck(myDeck);
        table = new ArrayList<>(52);
        trackGame = "";
        deckSize = deck.size();
        trackGame = "|[" + deckSize + "]| ";
    }

    // Helpers for the constructors:

    // Create a new randomly shuffled deck.
    private ArrayList<Character> createDeck() {
        ArrayList<Character> newDeck = new ArrayList<>(52);
        for (int i = 0; i < 26; i++) {
            newDeck.add('R');
            newDeck.add('B');
            Collections.shuffle(newDeck);
        }
        return newDeck;
    }
    /* *
    * @param  myDeck a list of characters
    * @return newDeck an ArrayList of characters
    * myDeck is created in the PlaySolitaire class
     */
    private ArrayList<Character> createDeck(char[] myDeck) {
        ArrayList<Character> newDeck = new ArrayList<>(52);
        for (char i: myDeck) {
            newDeck.add(i);
        }
        return newDeck;
    }


    // Start playing!

    // In a do-while loop, the game is played until the deck is used up.
    // Then, it checks if it was a winning deck and updates the output accordingly.
    public String play() {
        do {
            draw();
            evaluateTable();
        } while (deck.size() > 0);
        if (table.size() == 0) {
            trackGame += "\n" + "You won!";
        } else {
            trackGame += "\n" + "You lost :(";
        }
        return trackGame;
    }

    // Helpers for playing

    // Remove the last card from the deck and add it to the table.
    // The table must have at least four cards, assuming the deck is not empty.
    private void draw() {
        int count = 0;
        do {
            table.add(deck.remove(deck.size()-1));
            count ++;
            deckSize --;
        } while (table.size() < 4 && deck.size() != 0);

        trackGame += "\n" + "Added " + count + " cards to the table";
        trackGame += "\n" + "|[" + deckSize + "]| " + table;
    }
    // Check if any cards can be removed, and remove them according to the game rules.
    private void evaluateTable() {
        // The right-most card on the table:
        char last = table.get(table.size()-1);
        // The loop repeats while the table has at least four cards,
        // and the fourth-to-last is the same as the last.
        while (table.size() >=4 && last == table.get(table.size()-4)){
            //If the last four are the same, remove them all.
            // Otherwise, remove the second- and third-to-last (The ones between the match).
            if (last == table.get(table.size()-2) && last == table.get(table.size()-3)){
                // remove last four
                table.remove(table.size()-1);
                table.remove(table.size()-1);
                table.remove(table.size()-1);
                table.remove(table.size()-1);

                trackGame += "\n" + "Removed four cards";
                trackGame += "\n" + "|[" + deckSize + "]| " + table;
            } else {
                // remove two
                table.remove(table.size()-2);
                table.remove(table.size()-2);
                trackGame += "\n" + "Removed two cards";
                trackGame += "\n" + "|[" + deckSize + "]| " + table;
            } //  end if
        } // end while
    } // end evaluate


    // writes the game to a file
    public void saveGame(String game, String fileName) {
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(fileName, true));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file" + fileName);
        }
        outputStream.println("\n" + game + "\n");        
        outputStream.close();
 
    }
    
} // end class