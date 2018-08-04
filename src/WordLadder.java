import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class WordLadder {
	
	String DICTLOCATION = "src/sowpods.txt";
	
	Set<String> wordsSet = new HashSet<String>(); 

	public WordLadder() throws IOException {
		// Populate the wordset
		populateSet();
	
	}

	private void populateSet () throws IOException {
		 BufferedReader br = new BufferedReader(new FileReader(DICTLOCATION));
		 String sCurrentLine;
		 while ((sCurrentLine = br.readLine()) != null) {
			 wordsSet.add(sCurrentLine);
		 }
		 System.out.println("Done");
	}
	
	private List<String> traceBack (WordNode word) {
		List<String> wordLadder = new ArrayList<>();
		while (word != null) {
			wordLadder.add(word.getWord());
			word = word.getPrevious();
		}
		return wordLadder;
	}
	
	public List<WordNode> generateValidPermutation (WordNode input) {
		
		List<WordNode> validPermutations = new ArrayList<>();
		
		char[] inputArray = input.getWord().toCharArray();
		for(int i=0; i<inputArray.length; i++) {
			for (char c = 'A'; c <='Z'; c++) {
				char tmp = inputArray[i];
				if(inputArray[i] != c) {
					inputArray[i] = c;
				}
				String newString = new String(inputArray);
				if (wordsSet.contains(newString)) {
					validPermutations.add(new WordNode(newString, input.getSteps() +1 , input));
					wordsSet.remove(newString);
				}
				inputArray[i] = tmp;
			}
		}
		
//		System.out.println(validPermutations.size());
//		validPermutations.forEach(val->{
//			System.out.println(val.getWord());
//		});
		return validPermutations;
	}
	
	public List<String> generateLadder(String startWord, String endWord){
		//return a list with wordLadder
		Queue<WordNode> queue = new LinkedList<WordNode>();
		
		WordNode top = new WordNode(startWord, 0, null); 
		queue.add(top);
		
		while (!queue.isEmpty()) {
			
			top = queue.poll();
			if (endWord.equals(top.getWord())){
				return traceBack(top);
			}

			List<WordNode> permutation = generateValidPermutation(top);
			queue.addAll(permutation);
		
		}
		
		return new ArrayList();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordLadder wordLadder = null;
		try {
			wordLadder = new WordLadder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		WordNode wordNode = new WordNode("GOLD", 1, null);
//		wordLadder.generateValidPermutation(wordNode);
		
		List<String> answer = wordLadder.generateLadder("CLAY", "GOLDA");
		System.out.println(answer.size());
		answer.forEach(val->System.out.println(val));
		
	}

}
