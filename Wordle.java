import java.util.*;

public class Wordle {
    
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wordle wordle = new Wordle();
        Puzzle puzzle = new Puzzle();
        System.out.println("Welcome to Wordle!\n\n" + "INSTRUCTIONS:\n" + 
            "You have 6 tries to guess a 5-letter word.\n" + 
            "Please only guess an existing 5-letter word with no repeating letters.\n" +
            "After each try, an indicator will appear next to each letter of your guess to show how close it was to the word.\n" + 
            "! indicates a letter that is contained in the word and is also in the correct place within the word.\n" + 
            "? indicates a letter that is contained in the word but is in the wrong location.\n" + 
            "* indicates a letter that is not contained in the word at all.\n\n" + 
            "Good luck!");
            
        while (!puzzle.isSolved() && puzzle.getNumGuesses() < 6) {
            puzzle.show();
            System.out.print("\nMake a guess: ");
            String guess = scanner.nextLine();
            puzzle.makeGuess(guess);
            System.out.println();
        }
        
        if (puzzle.isSolved()) {
            System.out.println("You win!");
        } else if (puzzle.getNumGuesses() >= 6) {
            System.out.println("You lose! The word was: " + puzzle.getWord());
        }
    }
    
    public static void clearScreen() {
        System.out.println("\f");
    }
}