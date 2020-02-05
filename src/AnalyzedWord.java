
public class AnalyzedWord implements Comparable<AnalyzedWord> {
	
	private String word;
	private int occurences;
	
	public AnalyzedWord(String word) {
		
		this.word = word;
		this.occurences = 1;
	}
	
	
	public void addOccurence() {
		
		this.occurences++;
	}
	
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getOccurences() {
		return occurences;
	}

	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}


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
