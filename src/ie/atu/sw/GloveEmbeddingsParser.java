package ie.atu.sw;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GloveEmbeddingsParser extends AbstractEmbeddingsParser {
	
	 private Map<String, List<Double>> gloveEmbeddingsMap = new HashMap<>();


    @Override
    public void parseFile() {
    	
		specifyFilePath();
		loadFile();
		
    }
	
	
    public Map<String, List<Double>> getGloveEmbeddingsMap() {
		return gloveEmbeddingsMap;
	}



	@Override
	protected void processLine(String line) {
		String [] parsedWords = line.split("\\s+");
		String word = parsedWords[0].replace(",","").toLowerCase();
		List<Double> embeddingsValues  = new ArrayList<Double>();
	    
		for(int i = 1; i < parsedWords.length; i++) {
			
			double embeddingValue = Double.parseDouble(parsedWords[i].replace(",", ""));
			
			embeddingsValues.add(embeddingValue);
			
		}
		
		embeddingsMap.put(word, embeddingsValues);
		
		
		
	  }
	
	
	
    	
		
	}
	
	


