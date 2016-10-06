/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
	
	private ArrayList<String> lexicon = new ArrayList<String>();
	
	public HangmanLexicon() {

		try{
			BufferedReader hangmanWords = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true){
				String line = hangmanWords.readLine();
				if (line ==null) break;
				
				lexicon.add(line);
			}
			hangmanWords.close();
		}
		catch (IOException ex){
			throw new ErrorException(ex);
			
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
	};
}
