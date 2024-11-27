package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TextParser {

	private HashMap<String, List<Double>> googleEmbeddingsMap = new HashMap<String, List<Double>>();
	private List<String> simpleText = new ArrayList<String>();
	private List<Double> similarities = new ArrayList<Double>();
	private String word;

	public TextParser(HashMap<String, List<Double>> googleEmbeddingsMap) {

		this.googleEmbeddingsMap = googleEmbeddingsMap;
	}

	public void parseFile(String filePath) {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line;

			while ((line = br.readLine()) != null) {

				String[] words = line.split("\\s+");
				for (int i = 0; i < words.length; i++) {
					word = words[i];
					processWord(word);

				}

			}

			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private void processWord(String word) {

		List<Double> valueOfKey = googleEmbeddingsMap.get(word);

		if (googleEmbeddingsMap.containsKey(word)) {

			for (String key : googleEmbeddingsMap.keySet()) {

				List<Double> value = googleEmbeddingsMap.get(key);

				similarities.add(cosineSimilarity(valueOfKey, value));
			}
		}
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
