package ubsdeck;

public interface Deck {
	
	public void shuffle();
	
	public boolean draw() throws DeckEmptyException;

}
