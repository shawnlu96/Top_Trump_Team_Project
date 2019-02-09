package commandline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TTControllerTest {
	private static TTController testController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	// make sure each test works with a fresh model and controller
	@BeforeEach
	void setUp() throws Exception {
		TTModel testModel = new TTModel("test");
		TTView testView = new TTView(testModel);
		testController = new TTController(testModel, testView, "test");
	}

	@AfterEach
	void tearDown() throws Exception {
		testController = null;
	}



//	@Test
//	void startGameTest() {
//		testController.getModel().setHumanPlayerEliminated(true);
//		//testController.getModel().setEnd(true);
//		testController.startGame();
//		
//		
//		//set the first player in the list pointing to the highest attribute of their topmost card
//		testController.getModel().setIndexOfCurrentAttribute(testController.getHighestAttributeIndex(0));
//		int[] attributes = new int[5];
//		//get the values of the attributes of the topmost cart in the list of players
//		attributes = testController.getModel().getPlayers().get(0).getPlayerCards().get(0).getAttributes();
//		testController.playRound(testController.getHighestAttributeIndex(0));
//		assertTrue(true);
//	}
	@Test
	void playRoundTest() {
		testController.getModel().setHumanPlayerEliminated(true);

		
		//set the first player in the list pointing to the highest attribute of their topmost card
		int currentAttribute = testController.getHighestAttributeIndex(0);
		testController.getModel().setIndexOfCurrentAttribute(currentAttribute);
		int[] attributes = new int[5];
		//get the values of the attributes of the topmost cart in the list of players
		attributes = testController.getModel().getPlayers().get(0).getPlayerCards().get(0).getAttributes();
		
		
		
		ArrayList<Card> cardsThisRound = new ArrayList<Card>();
		for(int i=0;i<testController.getModel().getPlayers().size();i++) {		//for each player
			if(!testController.getModel().getPlayers().get(i).getPlayerCards().isEmpty()) {					//if the player isn't eliminated
				cardsThisRound.add(testController.getModel().getPlayers().get(i).getPlayerCards().get(0));	//add current card to the array list fot this round
			}
		}
		int max = 0;
		int temp;
		int winnerIndex = testController.getModel().getIndexOfRoundWinner();
		boolean isDraw = false;
		for(int i=0;i<cardsThisRound.size();i++) {
			temp = cardsThisRound.get(i).getAttributes()[currentAttribute];	//get current characteristic
			if(temp>=max) {				
				if(temp==max) {		
					isDraw = true;
					continue;
				}
				max = temp;
				winnerIndex = cardsThisRound.get(i).getPlayerIndex();	//set the winner index
				isDraw = false;
			}
		}
		
		int losingPlayerIndex = 0;
		if(losingPlayerIndex == winnerIndex) {
			losingPlayerIndex++;
		}
		int numberOfCardsBeforeWinning = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards().size();
		int numberOfCardsBeforeLosing = testController.getModel().getPlayers().get(losingPlayerIndex).getPlayerCards().size();
		
		testController.playRound(testController.getHighestAttributeIndex(0));
		
		int numberOfCardsAfterWinning = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards().size();
		int numberOfCardsAfterLosing = testController.getModel().getPlayers().get(losingPlayerIndex).getPlayerCards().size();
		
		if(isDraw) {
			assertFalse(testController.getModel().getCommunalPile().isEmpty(),"test that when the round ends in a draw, cards go to communal pile ");
			assertEquals(testController.getModel().getCommunalPile().size() , testController.getModel().getPlayers().size(),"verify that on a draw, all players put their cards in the communal pile");
		}
		
		//check that the winner gets extra cards to his pack, in the right order and at the end of the pack
		//also check that a losing and a winning player's number of cards in hand changes according to the game logic (i.e. decreases by one, or increases by the number of opponents during that round)
		if(!isDraw) {
			assertTrue(testController.getModel().getCommunalPile().isEmpty(),"verify that when the round doesn't end in a draw, communal pile is empty");
			int numberOfCardsOfWinner = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards().size();
			for(int i = 0; i < cardsThisRound.size() ;i++) {
				Card expectedCard = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards().get(numberOfCardsOfWinner-cardsThisRound.size()+i);
				Card actualCard = cardsThisRound.get(i);
				assertEquals(expectedCard,actualCard, "check that cards won by a player after a round go at the end of that player's hand(deck)");
			}
			//check shift in the winning player's number of cards
			assertTrue(numberOfCardsAfterWinning > numberOfCardsBeforeWinning, "test that after winning a round, the winner has more cards than before winning");
			assertEquals(numberOfCardsBeforeWinning +  testController.getModel().getPlayers().size()-1,numberOfCardsAfterWinning, "test that the winner gets the right number of cards from the other players");
			//check shift in a losing player's number of cards
			assertTrue(numberOfCardsBeforeLosing > numberOfCardsAfterLosing, "test that after losing in a round, losing player has fewer cards than prior to the round");
			assertEquals(numberOfCardsBeforeLosing,numberOfCardsAfterLosing + 1, "test that a losing player loses exactly one card after losing in a round");
		}

	
		
		assertEquals(winnerIndex,testController.getModel().getIndexOfRoundWinner(),"test that the winner of the round is the player who chose highest attribute");
		
		
	}

	@Test
	public void testHighestAttribute() {

		Player testPlayer = new Player("testPlayer");

		int[] testAttributes = { 4, 5, 10, 7, 2 };
		Card testCard = new Card("testCard", testAttributes);
		ArrayList<Card> testCardList = new ArrayList<Card>();
		testCardList.add(testCard);

		testPlayer.setPlayerCards(testCardList);

		ArrayList<Player> testPlayerList = new ArrayList<Player>();
		testPlayerList.add(testPlayer);

		testController.getModel().setPlayers(testPlayerList);

		assertEquals(2, testController.getHighestAttributeIndex(0), "Test if AI chooses highest attribute");
		
		

	}

	@Test
	public void testHumanEliminated() {

		System.out.println("\nPLEASE PRESS ENTER TO FINISH EXECUTING THE TESTS:\n");
		// initiate two players (human and AI)
		Player testPlayer = new Player("You");
		Player testAIPlayer = new Player("AIPlayer");

		// initiate a card and add it to the human player's hand
		int[] testAttributes = { 4, 5, 10, 7, 2 };
		Card testCard = new Card("testCard", testAttributes);
		ArrayList<Card> testCardList = new ArrayList<Card>();
		testCardList.add(testCard);
		testPlayer.setPlayerCards(testCardList);

		// initiate a new playerlist containing our two test players and set the
		// model pointing to it
		ArrayList<Player> testPlayerList = new ArrayList<Player>();
		testPlayerList.add(testPlayer);
		testPlayerList.add(testAIPlayer);
		testController.getModel().setPlayers(testPlayerList);

		// set the index of the human player; remove all of the human player's cards
		// and then ask the model to check if human player is now eliminated
		testController.getModel().setIndexOfHumanPlayer();
		testController.getModel().getPlayers().get(testController.getModel().getIndexOfHumanPlayer()).getPlayerCards()
				.clear();
		testController.checkHumanPlayerEliminated();

		// the checkHumanPlayerEliminated method call should have set the
		// isHumanPlayerEliminated boolean to "true"
		assertTrue(testController.getModel().isHumanPlayerEliminated(), "Test if human player is eliminated");
		

	}
	
	@Test
	public void testHumanPlayerAttributeChoice() {
		System.out.println("\nPlease choose a number between 1 and 5 to simulate a player's choosing a card attribute");
		int attributeChoice = testController.getAttributeIndex();
		testController.getModel().setIndexOfCurrentAttribute(attributeChoice);
		assertEquals(attributeChoice, testController.getModel().getIndexOfCurrentAttribute(),
				"Tests that the player's attribute choice is reflected in the model class");
	}

}

