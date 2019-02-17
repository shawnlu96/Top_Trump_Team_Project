package commandline;

import java.util.ArrayList;

public class TTView {
	
	private TTModel model;
	private ArrayList<Card> cards;		//for testing

	//...constructor
	public TTView(TTModel model) {
		this.model = model;
		cards = model.getCards();		//for testing
	}
	
	
	//only for debugging, keep for showing testing in final project
	public void showCards() {
		for(int i=0;i<cards.size();i++) {
			System.out.println(cards.get(i).toString());
		}
	}
	
	public void showScores() {
		System.out.println("Scores:");
		for(int i=0;i<model.getPlayers().size();i++) {
			System.out.println("   " + model.getPlayers().get(i).getPlayerName() + ": " + model.getPlayers().get(i).getGameWon());
		}
	}
//
//	//only for testing
//	public void showCardsOfEachPlayer() {
//		for(int i=0;i<model.getPlayers().size();i++) {
//			System.out.println(model.getPlayers().get(i).getPlayerName() + ":");
//			showCardOfPlayerByIndex(i);
//		}
//	}
//
//	public void showCardOfPlayerByIndex(int index) {
//		for(int i=0;i<model.getPlayers().get(index).getPlayerCards().size();i++) {
//			System.out.println(model.getPlayers().get(index).getPlayerCards().get(i).toString());
//		}
//	}

	public void showCardsThisRound(){
		for(Player p:model.getPlayers()){
			if(p.getPlayerCards().size()!=0) {
				System.out.print(p.getPlayerName() + "\t");
				System.out.println(p.getPlayerCards().get(0).toString());
			}
		}
	}


	public void showPlayerCardOnTop() {
		Card playerCard;
		playerCard = model.getPlayers().get(model.getIndexOfHumanPlayer()).getPlayerCards().get(0);
		System.out.println("You Drew '" + playerCard.getName() + "'");
		for(int i=0;i<playerCard.getAttributes().length;i++) {
			System.out.println("   > " + model.getAttributeNames()[i+1] + ": " + playerCard.getAttributes()[i]);
		}
	}
	
	public void showRoundNumber() {
		System.out.println("Round " + model.getRoundNumber());
		System.out.println("Round " + model.getRoundNumber() + ": Players have drawn their cards");
	}
	
	public void showWinningCard() {
		String s;
		Card winningCard = model.getCardByCardIndex(model.getIndexOfWinningCard());
		System.out.println("The winning card was '" + winningCard.getName() + "':");
		for(int i=1;i<model.getAttributeNames().length;i++) {
			s = "   > " + model.getAttributeNames()[i] + ": " + winningCard.getAttributes()[i-1];
			if((i-1)==model.getIndexOfCurrentAttribute()) {
				s += " <--";
			}
			System.out.println(s);
		}
	}
	
//
	public void showFirstCardOfPlayerByIndex(int index) {
		System.out.print(String.format("%-9s:", model.getPlayers().get(index).getPlayerName()));
		System.out.println(model.getPlayers().get(index).getPlayerCards().get(0).toString());
	}
	
	public void showRoundWinner() {
		System.out.println("Round" + model.getRoundNumber()+ ": "
				+ model.getPlayers().get(model.getIndexOfRoundWinner()).getPlayerName()+" won this round!");
	}
	
	public void showPickingMessage(int indexOfCharacteristic) {
		System.out.println(model.getPlayers().get(model.getIndexOfRoundWinner()).getPlayerName() + 
				" Picked characteristic " + (indexOfCharacteristic+1) + ": " + model.getAttributeNames()[indexOfCharacteristic+1] + ".");
	}
	
	public void showCategories() {
		System.out.println("It is your turn to select a category, the categories are:");
		for(int i=0;i<model.getAttributeNames().length-1;i++) {
			System.out.println("   " + (i+1) + ":" + model.getAttributeNames()[i+1]);
		}
	}
	
	public void showDrawMessage() {
		System.out.println("Round " + model.getRoundNumber() + ": This round was a Draw, common pile now has " + model.getCommunalPile().size() + " cards");
	}
	
	public void showFinalWinner() {
		System.out.println("Game Over! The Final winner is " + model.getPlayers().get(model.getIndexOfFinalWinner()).getPlayerName() + "!");

	}
	
	public void showHumanPlayerCardCount() {
		System.out.println("You now have " + model.getPlayers().get(model.getIndexOfHumanPlayer()).getPlayerCards().size() + " card(s) in your deck.");
	}
	
	public void showAllPlayerCardsCount() {
		System.out.println("Player cards statistics:");
		for(int i=0;i<model.getPlayers().size();i++) {	//for each player
			System.out.print(String.format("%-9s", model.getPlayers().get(i).getPlayerName()));
			System.out.println(":\t" + model.getPlayers().get(i).getPlayerCards().size());
		}
	}

}
