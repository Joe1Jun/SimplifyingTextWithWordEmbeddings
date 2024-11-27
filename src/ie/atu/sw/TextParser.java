package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TextParser {

	private HashMap<String, List<Double>> googleEmbeddingsMap = new HashMap<String, List<Double>>();
	private HashMap<String, List<Double>> embeddingsMap = new HashMap<String, List<Double>>();
	private List<String> simpleText = new ArrayList<String>();
	private StringBuilder swappedText = new StringBuilder();
	

	public TextParser(HashMap<String, List<Double>> googleEmbeddingsMap, HashMap<String, List<Double>> embeddingsMap) {
		super();
		this.googleEmbeddingsMap = googleEmbeddingsMap;
		this.embeddingsMap = embeddingsMap;
	}

	

	public void parseFile(String filePath) {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;

			while ((line = br.readLine()) != null) {

				String[] words = line.split("\\s+");
				for(String word : words) {
					 word = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Normalize
					String swappedWord = processWord(word);
					swappedText.append(swappedWord + " ");
					
				}
				swappedText.append("\n");
				
			}

			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println(swappedText);

	}

	

	private String processWord(String word) {
		// if no word in map default to original word
		
		if(!embeddingsMap.containsKey(word)) {
			 System.out.println("Word not found in embeddings: " + word);
			return word;
		}
		if(googleEmbeddingsMap.containsKey(word)) {
			return word;
		}
		 List<Double> originalEmbedding = embeddingsMap.get(word);
		 if (originalEmbedding == null) {
		        return word; // Return unchanged if embedding is null
		    }
		
         String highestScoreWord = word;
         // adjust the similarity score threshhold
         double highestSimilarity = 0.7;
         
         for(String key : googleEmbeddingsMap.keySet()) {
        	
        	 
        	 if(!key.equals(word)) {
        		 List<Double> loopEmbedding = googleEmbeddingsMap.get(key);
        		 if(loopEmbedding == null) {
        			 continue;
        		 }
        		 double similarity = cosineSimilarity(originalEmbedding, loopEmbedding);
        		 
        		 
        		 if(similarity > highestSimilarity) {
        			 highestSimilarity = similarity;
        			 highestScoreWord = key;
        		 }
        	 }
         }
         System.out.println("Replaced '" + word + "' with '" + highestScoreWord + "'");
         return highestScoreWord;
         
		
			
	}

	private double cosineSimilarity(List<Double> vecA, List<Double> vecB) {

		// Ensure that both vectors have the same length.
		if (vecA.size() != vecB.size()) {
			throw new IllegalArgumentException("Vectors must be of the same length.");
		}

		// Variables to store the dot product and the norms of the vectors.
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;

		// Calculate the dot product and the norms for vecA and vecB.
		for (int i = 0; i < vecA.size(); i++) {
			// Sum of products for dot product.
			dotProduct += vecA.get(i) * vecB.get(i);
			// Sum of squares for vecA.
			normA += Math.pow(vecA.get(i), 2);
			// Sum of squares for vecB.
			normB += Math.pow(vecB.get(i), 2);
		}

		// Compute the norms by taking the square root of the summed squares.
		normA = Math.sqrt(normA);
		normB = Math.sqrt(normB);

		// Return the cosine similarity, which is the dot product divided by the
		// product of the magnitudes (norms) of the two vectors.
		return dotProduct / (normA * normB);
	}
	
	
	

}
