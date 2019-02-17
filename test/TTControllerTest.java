import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import commandline.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TTControllerTest {
	private static TTController testController;

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

	@Test
	void playRoundTest() {
		testController.getModel().setHumanPlayerEliminated(true);

		// set the first player in the list pointing to the highest attribute of their
		// topmost card
		int currentAttribute = testController.getHighestAttributeIndex(0);
		testController.getModel().setIndexOfCurrentAttribute(currentAttribute);
		
		//take all the cards in play this round and add them to the cardsThisRound arrayList
		ArrayList<Card> cardsThisRound = new ArrayList<Card>();
		for (int i = 0; i < testController.getModel().getPlayers().size(); i++) { // for each player
			if (!testController.getModel().getPlayers().get(i).getPlayerCards().isEmpty()) { // if the player isn't eliminated
				cardsThisRound.add(testController.getModel().getPlayers().get(i).getPlayerCards().get(0)); // add current card to the arraylist for this round
			}
		}
		//initialize variables which are relevant to the logic of playing a round
		int max = 0;
		int temp;
		int winnerIndex = testController.getModel().getIndexOfRoundWinner();
		boolean isDraw = false;
		for (int i = 0; i < cardsThisRound.size(); i++) {
			temp = cardsThisRound.get(i).getAttributes()[currentAttribute]; // get current characteristic
			if (temp >= max) {
				if (temp == max) {
					isDraw = true;
					continue;
				}
				max = temp;
				winnerIndex = cardsThisRound.get(i).getPlayerIndex(); // set the winner index
				isDraw = false;
			}
		}
		//there are at least 2 players in this round. Assume the first one is the losing one, if the
		//first player is in fact the winning of the round, then the second player is a loser this round
		int losingPlayerIndex = 0;
		if (losingPlayerIndex == winnerIndex) {
			losingPlayerIndex++;
		}
		//looking at players a player who will lose this round and the one who will win it, 
		//count how many cards they have before actually playing the round
		int numberOfCardsBeforeWinning = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards()
				.size();
		int numberOfCardsBeforeLosing = testController.getModel().getPlayers().get(losingPlayerIndex).getPlayerCards()
				.size();
		
		//after all the setup is complete and we took note of the initial state, actually play the round
		testController.playRound(testController.getHighestAttributeIndex(0));

		int numberOfCardsAfterWinning = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards().size();
		int numberOfCardsAfterLosing = testController.getModel().getPlayers().get(losingPlayerIndex).getPlayerCards()
				.size();

		if (isDraw) {
			assertFalse(testController.getModel().getCommunalPile().isEmpty(),
					"test that when the round ends in a draw, cards go to communal pile ");
			assertEquals(testController.getModel().getCommunalPile().size(),
					testController.getModel().getPlayers().size(),
					"verify that on a draw, all players put their cards in the communal pile");
		}

		// check that the winner gets extra cards to his pack, in the right order and at the end of the pack
		// also check that a losing and a winning player's number of cards in hand changes according to the game 
		// logic (i.e. decreases by one, or increases by the number of opponents during that round)
		if (!isDraw) {
			assertTrue(testController.getModel().getCommunalPile().isEmpty(),
					"verify that when the round doesn't end in a draw, communal pile is empty");
			int numberOfCardsOfWinner = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards().size();
			for (int i = 0; i < cardsThisRound.size(); i++) {
				Card expectedCard = testController.getModel().getPlayers().get(winnerIndex).getPlayerCards()
						.get(numberOfCardsOfWinner - cardsThisRound.size() + i);
				Card actualCard = cardsThisRound.get(i);
				assertEquals(expectedCard, actualCard,
						"check that cards won by a player after a round go at the end of that player's hand(deck)");
			}
			// check shift in the winning player's number of cards
			assertTrue(numberOfCardsAfterWinning > numberOfCardsBeforeWinning,
					"test that after winning a round, the winner has more cards than before winning");
			assertEquals(numberOfCardsBeforeWinning + testController.getModel().getPlayers().size() - 1,
					numberOfCardsAfterWinning,
					"test that the winner gets the right number of cards from the other players");
			// check shift in a losing player's number of cards
			assertTrue(numberOfCardsBeforeLosing > numberOfCardsAfterLosing,
					"test that after losing in a round, losing player has fewer cards than prior to the round");
			assertEquals(numberOfCardsBeforeLosing, numberOfCardsAfterLosing + 1,
					"test that a losing player loses exactly one card after losing in a round");
		}

		assertEquals(winnerIndex, testController.getModel().getIndexOfRoundWinner(),
				"test that the winner of the round is the player who chose highest attribute");

	}

	@Test
	public void testHighestAttribute() {

		Player testPlayer = new Player("testPlayer");

		int[] testAttributes = { 4, 5, 10, 7, 2 };
		Card testCard = new Card("testCard", testAttributes,1);
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

		// initiate two players (human and AI)
		Player testPlayer = new Player("You");
		Player testAIPlayer = new Player("AIPlayer");

		// initiate a card and add it to the human player's hand
		int[] testAttributes = { 4, 5, 10, 7, 2 };
		Card testCard = new Card("testCard", testAttributes, 1);
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
		
		testController.checkHumanPlayerEliminated("test");
	
		// the checkHumanPlayerEliminated method call should have set the
		// isHumanPlayerEliminated boolean to "true"
		assertTrue(testController.getModel().isHumanPlayerEliminated(), "Test if human player is eliminated");
		System.out.println("\n____Tests completed____");
		}
		
	@Test
	public void testHumanPlayerAttributeChoice() {
		int attributeChoice = 3; //random (valid) value
		testController.getModel().setIndexOfCurrentAttribute(attributeChoice);
		assertEquals(attributeChoice, testController.getModel().getIndexOfCurrentAttribute(),
				"Tests that the player's attribute choice is reflected in the model class");
	}

}
