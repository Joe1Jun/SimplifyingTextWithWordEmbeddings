package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	
	private boolean keepRunning = true;
	
	
	private ObjectManager objectManager;
	private Scanner input;
	
	
	public Menu(ObjectManager objectManager) {
		
		this.objectManager = objectManager;
	}

	public void start () throws Exception {
		
		input = new Scanner(System.in);
		
		 while (keepRunning) {
	        	// The while loop will continue to run until the user selects the quit option
	        	// which sets the keepRunning boolean value to false.
	            showOptions();
	            
	            try {
	            	// Read the user's choice from the console input.
	                // Integer.parseInt converts the string input to an integer.
	                // This integer determines the action to be taken by the processChoice method.
	            	// Using the same Scanner instance ensures consistency across an application.
	       //         int choice = Integer.parseInt(objectManager.getInput().next());
	                
	            	int choice = input.nextInt();
	                // Consume the leftover newline
	        //        objectManager.getInput().nextLine();
	                // processChoice method is called to process the user input.
	                processChoice(choice);
	                // Catch block will print a error message to the user if they don't enter a valid input.
	            } catch (NumberFormatException e) {
	                ConsoleUtils.printError("Invalid input. Please enter a number between 1 and 8.");
	            }
	            
	        }
		
}
	
	private void showOptions() {
		
		
		
		
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             Virtual Threaded Text Simplifier             *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Embeddings File");
		System.out.println("(2) Specify Google 1000 File");
		System.out.println("(3) Specify an Output File (default: ./out.txt)");
		System.out.println("(4) Execute, Analyse and Report");
		System.out.println("(5) Optional Extras...");
		System.out.println("(?) Quit");
		
		//Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-4]>");
		System.out.println();
	}
	
	
	 private void processChoice(int choice) throws Exception {
	        switch (choice) {
	            case 1 -> objectManager.specifyEmbeddingsFile();
	            case 2 -> objectManager.specifyGoogle1000File();
	            
	            case 4 -> objectManager.execute();
	          //  case 4 -> objectManager.execute();
	           
	            
	            default -> ConsoleUtils.printError("Invalid input. Please select a number from 1 to 8.");
	        }
	    }
	
	
	
	
	

}
