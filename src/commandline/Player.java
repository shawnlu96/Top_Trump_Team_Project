package commandline;
import java.util.ArrayList;

public class Player {
	
	private String playerName;
	private ArrayList<Card> playerCards;
	private boolean isEliminated = false;
	private int gameWon;
	public Player(String playerName){
		 	this.playerName = playerName;
	}
	
	public void setPlayerCards(ArrayList<Card> playerCards) {
		this.playerCards = playerCards;
	}
	
	public ArrayList<Card> getPlayerCards(){
		return playerCards;
	}
	
	public String getPlayerName() {
		return playerName;
	}
//	public int whateverInt()
//	{
//		int i = 0;
//		i = (int) Math.ceil(Math.random());
//		return i;
//		
//	}

	public boolean isEliminated() {
		return isEliminated;
	}

	public void setEliminated(boolean isEliminated) {
		this.isEliminated = isEliminated;
	}

	public int getGameWon() {
		return gameWon;
	}

	public void setGameWon(int gameWon) {
		this.gameWon = gameWon;
	}
}
