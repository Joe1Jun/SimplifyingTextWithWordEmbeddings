package ie.atu.sw;

import java.util.List;

public class CosineSimilarityCalculator implements SimilarityCalculator {

	@Override
	public double calculateSimilarityScores(List<Double> vecA, List<Double> vecB) {
		
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
