package ie.atu.sw;

import java.io.IOException;
import java.util.*;

public abstract class AbstractEmbeddingsParser implements Parser {

	protected Map<String, List<Double>> embeddingsMap;
    protected String filePath;
   
    
    
	
    
    public AbstractEmbeddingsParser(Map<String, List<Double>> embeddingsMap, String filePath) {
		
		this.embeddingsMap = embeddingsMap;
		this.filePath = filePath;
		
	}
	
	
	@Override
	public abstract void loadEmbeddings(String filePath) throws IOException;
		
		

	

	@Override
	public Map<String, List<Double>> getEmbeddingsMap() {
		
		return embeddingsMap;
	}
	
	// Template method for parsing with hooks
    protected abstract void processLine(String line);
    
    
    


	

    
    
	
	
	
}
