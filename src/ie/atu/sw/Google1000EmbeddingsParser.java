package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Google1000EmbeddingsParser extends AbstractEmbeddingsParser {
	
	
	private final Map<String, List<Double>> sourceEmbeddings;

    public Google1000EmbeddingsParser(Map<String, List<Double>> sourceEmbeddings) {
        
        this.sourceEmbeddings = sourceEmbeddings;
    }

	
	
	
	
    @Override
    public void parseFile() {
    	specifyFilePath();
        loadFile();
    }
		
		


	


	
	

	@Override
	public void processLine(String line) {
		String word = line.trim().toLowerCase();
		
		
		if(sourceEmbeddings.containsKey(word)) { 
			embeddingsMap.put(word, embeddingsMap.get(word));
			
		}
		
	}





	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
