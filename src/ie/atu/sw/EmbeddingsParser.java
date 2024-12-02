package ie.atu.sw;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EmbeddingsParser extends AbstractEmbeddingsParser {
	
	 private Scanner input;
	
	
	public EmbeddingsParser(Map<String, List<Double>> embeddingsMap, String filePath, Scanner input) {
		super(embeddingsMap, filePath);
		this.input = input;
	}


	
	
    

	

	

       @Override
     public void loadEmbeddings(String filePath) throws IOException {
		
		if(filePath == null) {
			specifyFilePath();
		}
		
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
				

				
			  }
			
			System.out.println(embeddingsMap.size());
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		}

	

	@Override
	 public Map<String, List<Double>> getEmbeddingsMap() {
		return embeddingsMap;
	}

	
	public void specifyFilePath() {
		System.out.println("Specify embeddings file path");
		filePath = input.nextLine();
		
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public String getFilePath() {
		return filePath;
	}










	@Override
	protected void processLine(String line) {
		// TODO Auto-generated method stub
		
	}
	
	

}
