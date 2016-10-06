	
	/*here is how the program works so that I can remember after a few days away
	*setUpGame() is run, and in doing so sets hiddenWord = to showNumberOfLetters() 
	*showNumberOfLetters() is called first
	*when it gets to the part of the method where word.length() is needed
	*it jumps up to see what the instance variable of word is 
	*word = pickWord(), so the pickWord method is ran in order to generate a random secret word
	*the secret word is created and then used in the word.length of the showNumberOfLetters() method
	*showNumberOfLetters() then runs through and returns a set of dashes the length of the secret word (-------)
	*setUpGame() now has everything it needs in order to print to the screen
	*playGame() is now called, asking a user what there letter guess is. This play game while loop will run until the game is over.
	*that letter is stored as getChar. If getChar is more than one letter, the user is told this is not allowed
	*If getChar is only one letter, that while loop breaks
	*The original input of the user was a string. It is now converted to a character
	*I then see if the character is lowercase, and if it is, convert it to uppercase
	*now the method letterCheck() is called. letterCheck() will do the following
	*first simply check to see if the guessed letter is in the word, or not in the word
	*if it is not in the word, subtracts a guess and adds the character to incorrectLetters
	*if it is in the word, prints that it is correct, then runs through each character of the secret word
	*determining which letters match the guessed letter, and replace those letters
	*Note, letterCheck() is a void method and does not return a value, it simply alters certain variables (guessCounter or hiddenWord)
	*once variables are altered by letterCheck(), program goes back to playGame() and check if the hiddenWord == word
	*if yes, the user has won the game and the while loop breaks. If no, the updated hiddenWord is displayed.  
	*If the guessCounter == 0, the game is over and the user loses
	*/

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	//creating all instance variables
	
	//creating new HangmanLexicon object (instance of HangmanLexicon class)
	private HangmanLexicon hangmanWords;
	
	//creates instance of HangmanCanvas class
	private HangmanCanvas canvas;
	
	//creating a new RandomGenerator object (instance of RandomGenerator class)
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	//this is the secret word
	private String word = pickWord();
	
	//this is the dash representation of the word, changes as the user guesses correct letters
	private String hiddenWord = showNumberOfLetters();
	
	//this is the number of guesses that the user has left
	private int guessCounter = 8;
	
	//this string keeps track of all letters guessed that we INCORRECT
	private String incorrectLetters = "";
	
	//this is the most recent character that the user has entered
	private char ch;
	
    public void run() {
		
    	//testing functions
 
    	setUpGame();
    	playGame();
	}
    
    

    //this method sets up the game at the beginning of a turn
    private void setUpGame(){
    	
    	//this is calling a method in the canvas object (instance of hangmnaCanvas class)
    	canvas.reset();
    	hiddenWord = showNumberOfLetters();
    	canvas.displayWord(hiddenWord);
    	println("Welcome to Hangman!");
    	println("The word now looks like this: " +hiddenWord );
    	println("You have " + guessCounter +" guess left.");

    }
    
    
    //this is the method that will generate a word, at random, from the lexicon
    private String pickWord (){	
    	hangmanWords = new HangmanLexicon();
    	int randomWord = rgen.nextInt(0 , hangmanWords.getWordCount()-1);
    	String pickedWord = hangmanWords.getWord(randomWord);
    	return pickedWord;
    }
    
    //this is the method that will show the user how many letters are in the secret word
    private String showNumberOfLetters(){ 	
    	String result = "";
    	for(int i = 0; i < word.length(); i++){
    		result = result + "-";
    	}
    	return result;
    }
    
    //this is the method that plays the game
    private void playGame() {
    	while (guessCounter > 0){
    		
    		String getChar = readLine("Your guess:");
    		
    		while (true){
    			if (getChar.length() > 1){
    				println("You can only guess one letter at a time. Try again: ");
    			}
		
    			if (getChar.length() == 1) break;
    		}
    		
    		ch = getChar.charAt(0);
    		if (Character.isLowerCase(ch)){
    			ch = Character.toUpperCase(ch);
    		}
    		
    		letterCheck();								
    		
    		if (hiddenWord.equals(word) ){
    			println ("You guessed the "+ word);
    			println("You win!");
    			break;
    		}
    		
    		println("The word now looks like " + hiddenWord);
    		println("You now have " + guessCounter + " guesses left."); 

    		}
    		
    		if (guessCounter == 0){
    			println("You have lost the game.");
    			println("The word was: "+ word);
    			
    		}
    	
    }	
    	
    //checks to see if hidden word contains the guessed letter, updates it if it does
    //takes a guess away from guessCounter if it does not, and adds letter to list of words that can't be guessed again
    private void letterCheck (){
    
    	//checks to see if the guessed letter is in the word
    	if (word.indexOf(ch) == -1) {
    		println("There are no " + ch + "'s in the word");
    		guessCounter--;
    		incorrectLetters = incorrectLetters + ch;
    		canvas.noteIncorrectGuess(incorrectLetters);
    	}
    	 
    	if (word.indexOf(ch) != -1){
    		println("That guess is correct.");
    	}
    	
    	//now the method will run through the letters of the secret word and see which ones match the guessed letter 
    	//if it is a match, it updates the hidden word to reveal the position of the guessed letter
    	for(int i = 0; i < word.length(); i++){
    		
    		if(ch == word.charAt(i)) {
    			
    			if (i > 0){
    				hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i+1);
    			}
    			
    			if (i == 0){
    				hiddenWord = ch + hiddenWord.substring(1);
    			}
    			canvas.displayWord(hiddenWord);
    		}
    	}

    }
    
    
    public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
}