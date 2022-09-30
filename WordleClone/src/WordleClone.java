/**
 * This application plays a wordle-like game with the user.
 * A list of five letter words must exist in "five.txt" for this
 * application to run correctly.
 * 
 * Note that students will complete this application as part of
 * assignment #5.
 * 
 * @author Peter Jensen and ---Diego Perez---
 * @version September 18, 2022
 */
package assignment05;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * My wordle clone application.
 */
public class WordleClone
{
	/**
	 * Application entry point
	 * 
	 * @param args unused
	 */
	public static void main(String[] args)
	{
		boolean win = false;
		// Prepare the console scanner (note the variable name)
		
		Scanner console = new Scanner(System.in);
		
		// Print a message.
		
		System.out.println("Welcome to my Wordle clone.");
		System.out.println("You have six guesses to guess the secret word.");
		System.out.println("Each guess is scored and printed back to you:");
		System.out.println("  Incorrect letters are replaced with -,");
		System.out.println("  Correctly placed letters are capitalized,");
		System.out.println("  Correct but misplaced letters are lowercase.");
		
		// Choose the winning, secret word from a text file of words.
		
		String secretWord;
		secretWord = pickRandomWord("five.txt");
		
		
		// Loop, allow the user to make guesses.
		
		int guessCount = 1;  // Which guess they're on.
		
		while (guessCount <= 6)
		{
			// Give prompt, input a guess.  For input robustness, take the first word
			//   on each line.  (Input a line, then scan the first word from that line.)
			
			System.out.print("Enter guess #" + guessCount + ": ");
			String line = console.nextLine();
			if (line.trim().length() == 0)  // Skip blank lines (remove whitespace from ends, check length)
				continue;
			Scanner lineScanner = new Scanner(line);
			String guess = lineScanner.next();
			lineScanner.close();
			
			// Validate the guess. (Check the word against the list of words.)
			// If the guess is not a valid word, restart the loop.
			if(isValidWord(guess, "five.txt") == false) {
				System.out.println("Not a Word. Try again.");
				continue;
			}
			
			// They've made a guess, count it.
			
			guessCount++;
			
			// Score it and display the results.
			
			String scoredGuess = scoreGuess(guess, secretWord); 
			System.out.println ("Guess: " + guess);
			System.out.println ("Score: " + scoredGuess);
			
			// Check for win.  If the scoredGuess is all uppercase and
			//   matches the secret word, it's a win.  Display a message and
			//   end the program.  
			
			if (secretWord.toUpperCase().equals(scoredGuess)){
				System.out.println("You Won!. The word was " + secretWord + ".");
				win = true;
				break;
				
			}
		}

		if(win !=true) {
			System.out.println("You lost.  The word was " + secretWord + ".");
			console.close();
		}
	}
	
	/**
	 * Given a filename, this method returns a count of the number of
	 * words in the file.  (Note that word length is not checked here.)
	 * 
	 * @param filename the name of an existing text file
	 * @return the count of words in the file
	 */
	public static int countWords (String filename)
	{		
		int count = 0;
    	try (Scanner in = new Scanner(new File(filename));)
        {
			while(in.hasNext()) {
				count ++;
				in.next(); //has the scanner go to the next item, else will be an infinite loop
			}
		}catch(IOException e) {
			System.out.println("Could not read the words: " + e.getMessage());
		}
		return count;
	}
	
	/**
	 * Given a filename, this method will choose a random word within
	 * that file and return it
	 * @param filename is the file being used to find the random word
	 * @return returns a random word from the chosen file
	 */
	public static String pickRandomWord(String filename) {
		String randomWord = "";
		try (Scanner in = new Scanner(new File(filename));)
        {
			Random gen = new Random();
			int num = gen.nextInt(countWords(filename));
			for(int i = 0; i < num; i++) {
				randomWord = in.next();
			}
		}catch(IOException e) {
			System.out.println("Could not read the words: " + e.getMessage());
		}
		return randomWord;
	}
	
	/**
	 * Given a word and a filename, this method determines if the word
	 * is in the file.  True is returned if the word is in the file,
	 * and false is returned otherwise.  
	 * 
	 * Note that the word should not have any whitespace in it, or it 
	 * won't match anything scanned from the file.  (No special check
	 * is done here for that case.)  The reason is that this function
	 * uses the .next() function from the Scanner class, and this
	 * strips away whitespace.
	 * 
	 * @param word a String without whitespace
	 * @param filename the name of an existing text file
	 * @return true iff the word was found in the file.
	 */
	public static boolean isValidWord (String word, String filename)
	{
		boolean isValid = false;
		try (Scanner in = new Scanner(new File(filename));)
        {
			while(in.hasNext()) {
				String validWord;
	            validWord = in.next(); 
	            if(word.equals(validWord)) {
	            	isValid = true;
	            }
			}
        }
		catch(IOException e) {
			System.out.println("Could not read the words: " + e.getMessage());
		}
		return isValid;
	}
	
	/**
	 * This method returns a copy of the input String, but with the 
	 * character at the specified position changed to the given letter.
	 * Position must be a valid position in the String or the results
	 * are undefined.  
	 * 
	 * Note that this function does not alter the original String, it
	 * just returns a copy with a letter replaced.  Here is an example
	 * of how this method can be used.
	 * 
	 * word = replaceLetter(word, 1, 'a');
	 * 
	 * If word originally contained "put", the word would now contain
	 * "pat".   
	 * 
	 * 
	 * @param s any non-empty string
	 * @param position a valid position in the string
	 * @param letter a letter to put in the string
	 * @return a copy of the original string, with a letter replaced
	 */
	public static String replaceLetter(String s, int position, char letter)
	{
		return s.substring(0,position) + letter + s.substring(position+1, s.length());
		
	}
	
	/**
	 * Given a user's guess and a Wordle answer, this method 'scores' the guess.
	 * It returns something that looks like a copy of the guess:  Guess characters
	 * appear to be replaced with a '-' if they don't exist in the answer.  They
	 * remain unchanged if they exist in the answer but are in the wrong spot.
	 * They are changed to uppercase if they're in the correct spot.
	 * 
	 * This function requires five character strings of letters.
	 * 
	 * For example:
	 *      answer: miter
	 *      guess:  timid
	 *      score:  tIm--
	 *      
	 * Note that each letter in the guess or answer is only scored once.  Thus,
	 * even though there were multiple i's in the guess, only one was scored.
	 * 
	 * @param guess a five letter string
	 * @param answer a five letter string
	 * @return the wordle score for the guess
	 */
	public static String scoreGuess (String guess, String answer)
	{
		// The score (before we start) is a five character string of dashes.
		// Set it up.
		
		String score = "-----";
		
		// Score the correct letters first.  If there is a match,
		// put a capital letter in the score, then 'remove' the matching
		// letter in the answer.  For example:
		//    answer:                  abcde
		//    guess:                   ecccc
		//    adjust score like this:              --C--
		//    remove matching letter from answer:  ab-de
		//    remove matching letter from guess:   ec-cc
		// This way, that letter cannot be matched again later.
		for(int position = 0; position < 5; position++) {
			char letter = answer.charAt(position);
			if (guess.charAt(position) == answer.charAt(position)){
				char correct = Character.toUpperCase(letter);
				answer = replaceLetter(answer, position, '-');
				guess = replaceLetter(guess, position, '-');
				score = replaceLetter(score, position, correct);
			}
		}
		
		// Next score misplaced letters.  If there is a match,
		// put a capital letter in the score, then 'remove' the matching
		// letter in the answer.  For example:
		//    answer:                  ab-de
		//    guess:                   ec-cc
		//    adjust score like this:              e-C--
		//    remove matching letter from answer:  ab-d-
		//    remove matching letter from guess:   -c-cc
		// Again, every time an answer letter matches, remove it by
		// replacing it with a dash so that it won't match again.
		for(int position = 0; position < 5; position++) {
			char guessChar = guess.charAt(position);
			for(int answerPosition = 0; answerPosition < 5; answerPosition++) {
				if(guess.charAt(answerPosition) == '-')
					continue;
				if(answer.charAt(answerPosition) == guessChar) {
					answer = replaceLetter(answer, position, '-');
					guess = replaceLetter(guess, answerPosition, '-');
					score = replaceLetter(score, position, guessChar);
				}
			}
		}
		// Done with scoring.  Return the score string.
		
		return score;
	}

}
