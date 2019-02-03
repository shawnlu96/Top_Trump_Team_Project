package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		Scanner s = new Scanner(System.in);
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			System.out.println("Do you want to see past results or play a game?\n   1: Print Game Statistics\n   2: Play game\n   0: Quit\n");
			String choice;
			while(true) {
				System.out.print("Enter the number for your selection: ");
				choice = s.next();
				if (choice.equals("1")) {
					DbStatic.showStatistics();
					break;
				} else if (choice.equals("2")) {
					TTModel model = new TTModel();
					TTView view = new TTView(model);
					TTController controller = new TTController(model, view);
					break;
				}else if (choice.equals("0")){
					userWantsToQuit = true;
					break;
				}
				else {
					System.out.println("Wrong input!");
				}
			}

		}


	}

}
