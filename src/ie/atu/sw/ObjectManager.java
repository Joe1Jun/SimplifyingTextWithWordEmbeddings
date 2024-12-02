package ie.atu.sw;

import java.io.IOException;
import java.util.Scanner;

public class ObjectManager  {
	 private Scanner input = new Scanner(System.in);
	 private AbstractEmbeddingsParser embeddingsParser;
	 private AbstractEmbeddingsParser google1000EmbeddingsParser;
	 private TextParser textParser;
	 private SimilarityCalculator calculate;
	 
	 
	public ObjectManager() {
		
		this.embeddingsParser = new GloveEmbeddingsParser();
		this.google1000EmbeddingsParser = null;
		this.textParser = null;
	
	}
	public void specifyEmbeddingsFile() throws IOException {
		
		embeddingsParser.specifyFilePath();
		embeddingsParser.parseFile();
		this.google1000EmbeddingsParser = new Google1000EmbeddingsParser(embeddingsParser.getEmbeddingsMap());
		
		
	}
	
	
	
	
     public void specifyGoogle1000File() throws IOException {
    	 
    	 if(embeddingsParser.getEmbeddingsMap() == null) {
    		 System.out.println("Please parse embedings file first");
    	 }
    	 google1000EmbeddingsParser.specifyFilePath();
         google1000EmbeddingsParser.parseFile();
         
         
         this.textParser = new TextParser(google1000EmbeddingsParser.getEmbeddingsMap());
		
	}
     
     public void execute() throws IOException {
    	 if(embeddingsParser.getEmbeddingsMap() == null) {
    		 System.out.println("Please parse embeddings file");
    	 }
    	 System.out.println("Specify text to be simplified");
    	 String path = input.nextLine();
    	 
    	 textParser.parseFile(path);
    	 
    	 
     }
	


	
	
	
	
	 
	 
	 
	 

}
