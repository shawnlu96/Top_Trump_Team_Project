import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import commandline.*;

class TTModelTest {
	private static TTModel testModel;

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.print(
				"__Tests complete!__");
		testModel = new TTModel("test");
	}

	@AfterAll
	static void tearDownAfterClass() {
		testModel = null;
	}

	@Test
	public void testCardsRead() {

		// testModel.readCards( new File("DotaDeck.txt"));
		assertEquals(false, testModel.getCards().isEmpty(), "Check that Card objects are created and put into array");
		assertEquals(6, testModel.getAttributeNames().length,
				"Check that method reads attributes and stores them into array of size 5");
		assertEquals(40, testModel.getCards().size(), "Check that exactly 40 cards are read");
	}

	@Test
	public void testPlayersInitilised() {
		assertFalse(testModel.getPlayers().isEmpty(), "check players initilised (list not empty)");
	}

	@Test
	public void testCardsDistributed() {
		// test that the total number of players is within constraints
		int numberOfPlayers = testModel.getAIPlayerNumber() + 1;
		if (numberOfPlayers > 5 || numberOfPlayers < 2) {
			fail("Wrong number of players (bad user input)");
		}

		ArrayList<Player> players = testModel.getPlayers();
		if (numberOfPlayers == 3) {
			int playersWith13Cards = 0;
			int playersWith14Cards = 0;
			for (Player p : players) {
				if (p.getPlayerCards().size() == 14) {
					playersWith14Cards++;
				} else {
					playersWith13Cards++;
				}
			}
			assertTrue(playersWith14Cards == 1, "there is just 1 player with more cards");
			assertTrue(playersWith13Cards == 2, "the other 2 players have just 13 cards");
		} else {
			for (Player p : players) {
				assertTrue(p.getPlayerCards().size() == 40 / numberOfPlayers,
						"otherwise each players gets 40/total players cards");
			}
		}

	}

	@Test
	public void testIndexOfHumanPlayer() {
		assertEquals("You", testModel.getPlayers().get(testModel.getIndexOfHumanPlayer()).getPlayerName(),
				"check that index points to human player");
	}

	@Test
	public void testNumberOfAIsChoice() {
		assertEquals(testModel.getAIPlayerNumber(), testModel.getPlayers().size() - 1,
				"test that the number of players in the game corresponds to the player's choice");
	}
}
