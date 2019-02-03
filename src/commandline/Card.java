package commandline;

public class Card {
	
	private String name;
	private int[] attributes;
	private int playerIndex;
	private int cardIndex;
	public Card(String name, int[] characteristics) {
		this.name = name;
		this.attributes = characteristics;
	}
	
	public String toString() {
		String s = String.format("%13s", name);
		for(int i=0;i<attributes.length;i++) {
			s += "\t" + attributes[i];
		}
		return s;
	}
	
	//...getters
	public String getName() {
		return name;
	}
	
	public int[] getAttributes(){
		return attributes;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getCardIndex() {
		return cardIndex;
	}

	public void setCardIndex(int cardIndex) {
		this.cardIndex = cardIndex;
	}
	
}
