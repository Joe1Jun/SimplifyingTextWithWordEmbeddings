package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextParser extends AbstractEmbeddingsParser {

	private Map<String, List<Double>> googleEmbeddingsMap = new HashMap<String, List<Double>>();
	private Map<String, List<Double>> gloveEmbeddingsMap = new HashMap<String, List<Double>>();
	private StringBuilder swappedText = new StringBuilder();
	
	

	public TextParser(Map<String, List<Double>> googleEmbeddingsMap, Map<String, List<Double>> gloveEmbeddingsMap) {
		
		this.googleEmbeddingsMap = googleEmbeddingsMap;
		this.gloveEmbeddingsMap = gloveEmbeddingsMap;
	}

	
     @Override
	public void parseFile() {
    	System.out.println(googleEmbeddingsMap.size());
    	System.out.println(gloveEmbeddingsMap.size());
    	 specifyFilePath();
    	 loadFile();

         System.out.println(swappedText);

	}
     
     
     @Override
 	protected void processLine(String line) {
    	 String[] words = line.split("\\s+");
			for(String word : words) {
				 word = word.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Normalize
				String swappedWord = processWord(word);
				swappedText.append(swappedWord + " ");
				
			}
			swappedText.append("\n");
 		
 	}
     
     
     
     

	

	private String processWord(String word) {
		// if no word in map default to original word
		
		if(!gloveEmbeddingsMap.containsKey(word)) {
			 System.out.println("Word not found in embeddings: " + word);
			return word;
		}
		if(googleEmbeddingsMap.containsKey(word)) {
			return word;
		}
		 List<Double> originalEmbedding = gloveEmbeddingsMap.get(word);
		 if (originalEmbedding == null) {
		        return word; // Return unchanged if embedding is null
		    }
		
         String highestScoreWord = word;
         // adjust the similarity score threshhold
         double highestSimilarity = 0.6;
         
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
	
	public double euclideanDistance(List<Double> vecA, List<Double> vecB) {
		// Validate that the vectors have the same length.
		if (vecA.size() != vecB.size()) {
			throw new IllegalArgumentException("Vectors must be of the same length.");
		}

		double sumSquaredDifferences = 0.0;

		// Calculate the sum of the squared differences between corresponding elements.
		for (int i = 0; i < vecA.size(); i++) {
			double difference = vecA.get(i) - vecB.get(i);
			sumSquaredDifferences += Math.pow(difference, 2);
		}

		// Compute and return the square root of the sum of squared differences,
		// which is the Euclidean distance.
		return Math.sqrt(sumSquaredDifferences);
	}


	
	
	
	

}
