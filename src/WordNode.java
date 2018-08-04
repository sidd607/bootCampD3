
public class WordNode {

	String word;
	Integer numSteps;
	WordNode previous;

	public WordNode(String inputWord, Integer inputSteps, WordNode inputPrev) {
		this.word = inputWord;
		this.numSteps = inputSteps;
		this.previous = inputPrev;
	}
	
	WordNode getPrevious () {
		return this.previous;
	}
	String getWord() {
		return this.word;
	}
	
	Integer getSteps () {
		return this.numSteps;
	}

}
