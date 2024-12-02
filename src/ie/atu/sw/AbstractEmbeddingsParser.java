package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class AbstractEmbeddingsParser implements Parser {

	 private Scanner input = new Scanner(System.in);
	 protected String filePath;
	 protected Map<String, List<Double>> embeddingsMap = new HashMap<>();
   
    
    

	
	
	    protected void loadFile() {
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                processLine(line);
	            }
	            System.out.println(embeddingsMap.size());
	           
	        } catch (IOException e) {
	            System.out.println("Error loading file: " + e.getMessage());
	        }
	    }


	    
	    protected void specifyFilePath() {
	        if (filePath == null || filePath.isEmpty()) {
	            System.out.println("Specify file path:");
	            filePath = input.nextLine().trim();
	        } else {
	            System.out.println("File path already specified: " + filePath);
	        }
	    }

	

	@Override
	public Map<String, List<Double>> getEmbeddingsMap() {
		
		return embeddingsMap;
	}
	
	// Template method for parsing with hooks
    protected abstract void processLine(String line);


    
    protected void printEmbeddingsMap() {
        System.out.println("Embeddings Map:");
        for (Map.Entry<String, List<Double>> entry : embeddingsMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("Total entries: " + embeddingsMap.size());
    }
	
    
    
    


	

    
    
	
	
	
}
