package ie.atu.sw;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EmbeddingsParser {
	
	private HashMap<String, List<Double>> embeddingsMap = new HashMap<String, List<Double>>();

	public void parseEmbeddingsFile(String filePath) throws IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			
			String line;
			
			
			while((line = br.readLine()) != null) {
				String [] parsedWords = line.split("\\s+");
				String word = parsedWords[0].replace(",","").toLowerCase();
				List<Double> embeddingsValues  = new ArrayList<Double>();
			    
				for(int i = 1; i < parsedWords.length; i++) {
					
					double embeddingValue = Double.parseDouble(parsedWords[i].replace(",", ""));
					
					embeddingsValues.add(embeddingValue);
					
					
					
				}
				
				embeddingsMap.put(word, embeddingsValues);
				
				for (Map.Entry<String, List<Double>> entry : embeddingsMap.entrySet()) {
				    String key = entry.getKey();
				    List<Double> value = entry.getValue();
				    System.out.println("Key: " + key + ", Value: " + value);
				}
				
			  }
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}

	public void populateMap() {
		
		
	}

	
	
	

}
