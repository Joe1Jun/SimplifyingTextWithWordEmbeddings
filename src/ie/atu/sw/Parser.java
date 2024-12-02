package ie.atu.sw;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public interface Parser {
	
	
	
    
	public void loadEmbeddings(String filePath) throws IOException ;

	

	public Map<String, List<Double>> getEmbeddingsMap();

		

	
     
	
	
}
