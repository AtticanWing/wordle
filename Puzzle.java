import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Puzzle {
    private String word;
    ArrayList<String> words;
    private int rightLetters;
    private int numGuesses;
    private String wrongLetters;
    private String corrLetters;

    public Puzzle() {
        words = new ArrayList<String>(3300);
        loadWords("words.txt");
        int r = (int)(Math.random()*words.size());
        word = words.get(r);
        wrongLetters = "";
        corrLetters = "";
        rightLetters = 0;
        numGuesses = 0;
    }

    /** Shows the guesses on the terminal
     *  
     */
    public void show() {
        System.out.println();
        System.out.println("Number of Guesses: " + getNumGuesses());
        System.out.println("Correct Letters: " + corrLetters);
        System.out.println("Wrong Letters: " + wrongLetters);
    }

    /** Accessor method for the puzzle word
     *  @return the String word
     */
    public String getWord() {
        return word;
    }

    public void loadWords(String filename) {
        File wordfile = new File(filename);
        try {
            Scanner fileScanner = new Scanner(wordfile);
            while (fileScanner.hasNext()) {
                String w = fileScanner.nextLine();
                if (w.length() == 5 && !Character.isUpperCase(w.charAt(0))) { 
                    if (notDouble(w)) {
                        words.add(w);
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public boolean notDouble(String _w) {
        for (int i = 0; i < _w.length(); i++) {
            String sub = _w.substring(i, i+1);
            if (_w.indexOf(sub) != _w.lastIndexOf(sub)) {
                return false;
            }
        }
        return true;
    }

    /** Method that determines whether the puzzle has been solved
     *  @return true if not solved, false if solved
     */
    public boolean isSolved() {
        if (rightLetters == word.length()) {
            return true;
        } else {
            return false;
        }
    }

    /** Method that takes guess and sees if correct
     *  @param guess letter guessed by player
     *  @return true if word contains the letter, false if the word does not
     */
    public void makeGuess(String guess) {
        rightLetters = 0;
        if (guess.length() < 5 || guess.length() > 5) {    
            System.out.print("Invalid guess. Please enter a 5-letter word.");
        } else if (!words.contains(guess)) {
            System.out.print("Invalid word. Please enter a valid word.");
        } else {
            for (int i = 0; i < guess.length(); i++) {
                if (guess.substring(i, i+1).equals((word.substring(i, i+1)))) {
                    System.out.print((word.substring(i, i+1)) + "! ");
                    rightLetters++;
                    if (corrLetters.length() < 1) {
                        corrLetters += guess.substring(i, i+1);
                    } else if (!corrLetters.contains(guess.substring(i, i+1))) {
                        corrLetters += ", " + guess.substring(i, i+1);
                    }
                } else if (word.contains((guess.substring(i, i+1)))) {
                    System.out.print((guess.substring(i, i+1)) + "? ");
                    if (corrLetters.length() < 1) {
                        corrLetters += guess.substring(i, i+1);
                    } else if (!corrLetters.contains(guess.substring(i, i+1))) {
                        corrLetters += ", " + guess.substring(i, i+1);
                    }
                } else {
                    System.out.print((guess.substring(i, i+1)) + "* ");
                    if (wrongLetters.length() < 1) {
                        wrongLetters += guess.substring(i, i+1);
                    } else if (!wrongLetters.contains(guess.substring(i, i+1))) {
                        wrongLetters += ", " + guess.substring(i, i+1);
                    }
                }
            }
            numGuesses++;
        }
    }

    public int getNumGuesses() {
        return numGuesses;
    }
}