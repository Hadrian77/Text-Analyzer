/**
 * 
 * An object used to store a word, and how often it occurs, comparable by amount of occurrences.
 *
 */
public class AnalyzedWord implements Comparable<AnalyzedWord> {
	
	
	private String word;
	private int occurences;
	
	
	/**
	 * Creates new AnalyzedWord Object containing a string passed by the user, and starting with 1 occurrence.
	 * @param word Word to be stored for tracking occurrences.
	 * 
	 * 
	 */
	public AnalyzedWord(String word) {
		
		this.word = word;
		this.occurences = 1;
	}
	
	/**
	 * 
	 * Adds one occurrence of the word to the occurrence count.
	 * 
	 */
	
	public void addOccurence() {
		
		this.occurences++;
	}
	
	
	/**
	 * Gets tracked word.
	 * @return Word being tracked.
	 */
	
	public String getWord() {
		return word;
	}

	/**
	 * Sets tracked word.
	 * @param word Word being tracked.
	 */
	
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Gets current number of times this word has appeared.
	 * @return How many times this word has appeared in text.
	 */
	
	public int getOccurences() {
		return occurences;
	}

	/**
	 * Sets current number of times this word has appeared.
	 * @param occurences How many times this word has appeared in text.
	 */
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}

	/**
	 * Makes object comparable based on number of occurrences.
	 * 
	 */

	@Override
	public int compareTo(AnalyzedWord b) {
		
		if(this.occurences < b.getOccurences()) {
			return 1;
		}
		
		if(this.occurences > b.getOccurences()) {
			return -1;
		}
		
		else {
			return 0;
		}
		
	}


	@Override
	public String toString() {
		return "\"" + word + "\"" + " occurs " + occurences + " times.\n";
	}

	
	
	

}
