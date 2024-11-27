package ie.atu.sw;

import java.io.IOException;
import java.util.Scanner;

public class ObjectManager  {
	 private Scanner input = new Scanner(System.in);
	 private EmbeddingsParser embeddingsParser;
	 private Google1000EmbeddingsParser google1000EmbeddingsParser;
	 private TextParser textParser;
	 
	 
	public ObjectManager() {
		
		this.embeddingsParser = new EmbeddingsParser();
		this.google1000EmbeddingsParser = null;
		this.textParser = null;
	}
	
	
	public void specifyEmbeddingsFile() throws IOException {
		
		System.out.println("Specify embeddings file path");
		String path = input.nextLine();
		embeddingsParser.parseEmbeddingsFile(path);
		this.google1000EmbeddingsParser = new Google1000EmbeddingsParser(embeddingsParser.getEmbeddingsMap());
		
		
	}
	
	
     public void specifyGoogle1000File() throws IOException {
    	 
    	 if(embeddingsParser.getEmbeddingsMap() == null) {
    		 System.out.println("Please parse embedings file first");
    	 }
		
		 System.out.println("Specify embeddings file path");
		 String path = input.nextLine();
         google1000EmbeddingsParser.parseFile(path);
         
         
         this.textParser = new TextParser(google1000EmbeddingsParser.getGoogleEmbeddingsMap(), embeddingsParser.getEmbeddingsMap());
		
	}
     
     public void execute() {
    	 if(embeddingsParser.getEmbeddingsMap() == null) {
    		 System.out.println("Please parse embeddings file");
    	 }
    	 System.out.println("Specify text to be simplified");
    	 String path = input.nextLine();
    	 
    	 textParser.parseFile(path);
    	 
    	 
     }
	


	
	
	
	
	 
	 
	 
	 

}
