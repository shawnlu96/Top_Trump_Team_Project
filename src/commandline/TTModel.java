package commandline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TTModel {
	
	private static final String DECK_PATH = "StarCitizenDeck_Original.txt";
	
	private int AIPlayerNumber;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Card> communalPile = new ArrayList<Card>();
	private ArrayList<Player> players;
	private String[] attributeNames;
	private boolean isEnd = false;
	private boolean isDraw = false;
	private boolean isHumanPlayerEliminated = false;
	private int indexOfRoundWinner = 0;
	private int indexOfWinningCard;
	private int indexOfCurrentAttribute;
	private int indexOfHumanPlayer;
	private int indexOfFinalWinner;
	private int roundNumber = 0;			//round number initialised as 0
	
	//...Constructor
	public TTModel() {
		readCards(new File(DECK_PATH));
		setNumbersOfAIPlayers();
		Collections.shuffle(cards);			//cards randomly shuffled
		initialisePlayers();				//initialise the player objects
		distributePlayerCards();			//initialise cards for each player
		setIndexOfHumanPlayer();
	}
	
	public void setNumbersOfAIPlayers() {
		int number;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the number of AI players");
		number = s.nextInt();
		AIPlayerNumber = number;
	}
	
	public void initialisePlayers() {
		players = new  ArrayList<Player>();
		players.add(new Player("You"));			//add user in first
		for(int i=1;i<AIPlayerNumber+1;i++){	//then generate the AI players, depending on the number user inputed
			players.add(new Player("AIPlayer" + i));
		}
		Collections.shuffle(players);			//shuffle the players in the array list
	}
	
	//initialise cards for all players
	public void distributePlayerCards() {
		int cardIndex = 0;						//the index of cards in the cards array list
		for(int i=0;i<players.size();i++) {		//for each player
			ArrayList<Card> playerCards = new ArrayList<Card>();	//temp array list for player cards initialisation created
			for(int j=0;j<(40/players.size());j++){
				playerCards.add(cards.get(cardIndex));				//add card to the temp array list
				cards.get(cardIndex).setPlayerIndex(i);				//set card's player index
				cards.get(cardIndex).setCardIndex(cardIndex);		//set card's index
				cardIndex++;										//increment the card index
				if(players.size()==3 && i==2 && cardIndex==39) {	//if there are 3 players and current player is the last one and current card is the last in the deck
					playerCards.add(cards.get(cardIndex));			//give one more card to the last player
					cards.get(cardIndex).setPlayerIndex(i);			//set card's player index
					cards.get(cardIndex).setCardIndex(cardIndex);		//set card's index
				}
			}
			players.get(i).setPlayerCards(playerCards);
		}
	}
	
	public void readCards(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String[] cardInfo = null;			//temp String array for storing the info of each card
			String line = null;					//temp String for storing each line read
			line = br.readLine();						//read the first line
			attributeNames = line.split(" ");			//get characteristic name
			while((line = br.readLine())!=null) {
				cardInfo = line.split(" ");
				int[] characteristics = new int[5];		//temp int array for creating Card objects
				for(int i=1;i<=5;i++) {
					characteristics[i-1] = Integer.parseInt(cardInfo[i]);
				}
				cards.add(new Card(cardInfo[0], characteristics));
			}
			br.close();							//buffered reader closed
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setIndexOfHumanPlayer() {
		for(int i=0;i<players.size();i++) {
			if(players.get(i).getPlayerName().equals("You")) {
				indexOfHumanPlayer = i;
				break;
			}
		}
	}
	
	public void setAIPlayerNumber(int playerNumber) {
		this.AIPlayerNumber = playerNumber;
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean isDraw() {
		return isDraw;
	}

	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}

	public int getIndexOfRoundWinner() {
		return indexOfRoundWinner;
	}

	public void setIndexOfRoundWinner(int indexOfRoundWinner) {
		this.indexOfRoundWinner = indexOfRoundWinner;
	}

	public int getIndexOfCurrentAttribute() {
		return indexOfCurrentAttribute;
	}

	public void setIndexOfCurrentAttribute(int indexOfCurrentAttribute) {
		this.indexOfCurrentAttribute = indexOfCurrentAttribute;
	}

	public ArrayList<Card> getCommunalPile() {
		return communalPile;
	}

	public void setCommunalPile(ArrayList<Card> communalPile) {
		this.communalPile = communalPile;
	}

	public int getIndexOfFinalWinner() {
		return indexOfFinalWinner;
	}

	public void setIndexOfFinalWinner(int indexOfFinalWinner) {
		this.indexOfFinalWinner = indexOfFinalWinner;
	}

	public String[] getAttributeNames() {
		return attributeNames;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public int getIndexOfHumanPlayer() {
		return indexOfHumanPlayer;
	}

	public int getIndexOfWinningCard() {
		return indexOfWinningCard;
	}

	public void setIndexOfWinningCard(int indexOfWinningCard) {
		this.indexOfWinningCard = indexOfWinningCard;
	}

	public boolean isHumanPlayerEliminated() {
		return isHumanPlayerEliminated;
	}

	public void setHumanPlayerEliminated(boolean isHumanPlayerEliminated) {
		this.isHumanPlayerEliminated = isHumanPlayerEliminated;
	}
	
}
