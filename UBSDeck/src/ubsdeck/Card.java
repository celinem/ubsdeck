package ubsdeck;


public class Card {
	
	private Suit suit;
	private Rank rank;
	
	
	public Card (Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;	   
	}
	
	public Suit getSuit()
	{
		return this.suit;
	}
	
	public Rank getRank() {
		return this.rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		Card other = (Card) obj;
		if (rank != other.rank) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Card [suit=" + suit + ", rank=" + rank + "]";
	}
		
}
