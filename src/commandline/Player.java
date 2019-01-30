package commandline;
import java.util.ArrayList;

import javax.xml.stream.events.Characters;

public class Player {
	
	private String playerName;
	private ArrayList<Card> playerCards;
	private boolean isEliminated = false;
	private int gameWon = 0;
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

	public void addGameWon() {
		gameWon++;
	}
	
	public int hashCode() {
		if(playerName.equals("You")) {
			return 1;
		}else {
			return (int)(playerName.charAt(playerName.length()-1) - '0' + 1);
		}
	}
}
