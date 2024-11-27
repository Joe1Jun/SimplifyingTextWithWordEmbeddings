package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Google1000EmbeddingsParser {
	
	private HashMap<String, List<Double>> googleEmbeddingsMap = new HashMap<String, List<Double>>();
	private HashMap<String, List<Double>> wordEmbeddingsMap = new HashMap<String, List<Double>>();
	
	
	public Google1000EmbeddingsParser(HashMap<String, List<Double>> wordEmbeddingsMap) {
		
		this.wordEmbeddingsMap = wordEmbeddingsMap;
		
		}
	
	
	public void parseFile(String filePath) {
		
		List<String> wordsNot = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
		
			String line;
			while((line = br.readLine()) != null) {
				String word = line.trim().toLowerCase();
				
				
				if(wordEmbeddingsMap.containsKey(word)) {
					googleEmbeddingsMap.put(word, wordEmbeddingsMap.get(word));
				}else {
					wordsNot.add(word);
				}
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		

//		for (Map.Entry<String, List<Double>> entry : googleEmbeddingsMap.entrySet()) {
//		    String key = entry.getKey();
//		    List<Double> value = entry.getValue();
//		    System.out.println("Key: " + key + ", Value: " + value);
//		}
//		
//		for(String word : wordsNot) {
//			System.out.println(word);
//		}
//		
//		
//		System.out.println(googleEmbeddingsMap.size());
	}


	public HashMap<String, List<Double>> getGoogleEmbeddingsMap() {
		return googleEmbeddingsMap;
	}


	public void setGoogleEmbeddingsMap(HashMap<String, List<Double>> googleEmbeddingsMap) {
		this.googleEmbeddingsMap = googleEmbeddingsMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
