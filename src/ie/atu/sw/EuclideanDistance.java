package ie.atu.sw;

import java.util.List;

public class EuclideanDistance implements SimilarityCalculator {

	@Override
	public double calculateSimilarityScores(List<Double> vecA, List<Double> vecB) {
		
		
		
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
