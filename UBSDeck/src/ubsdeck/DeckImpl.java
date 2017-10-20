package ubsdeck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.*;

public class DeckImpl  implements Deck {
	
	public static int NO_OF_CARDS = 52;
	private ArrayDeque<Card> deckOfCards = new ArrayDeque<>();
	
	
	public DeckImpl() {		
		//set up deck of cards.		
		setupDeck();
	}
	
	protected void setupDeck() {		
		deckOfCards.clear();
		for(Suit suit: Suit.values()) {
			for(Rank rank: Rank.values()) {
				Card c = new Card(suit, rank);
				deckOfCards.add(c);
			}
		}
	}
	
	public ArrayDeque<Card> getDeckOfCards() {
		return deckOfCards.clone();
	}
	
	public void clearDeck() {
		deckOfCards.clear();
	}	

	
	public void shuffle() {		
		
		deckOfCards.clear();	
		
		ArrayList<Suit> suitArray = new ArrayList<> (Arrays.asList(Suit.values()));				
		ArrayList<Rank> rankArray = new ArrayList<> (Arrays.asList(Rank.values()));		
		
						
		Collections.shuffle(suitArray);				
		Collections.shuffle(rankArray);
		
		
		for(Suit suit : suitArray) {
			for (Rank rank : rankArray) {				
								
				Card card = new Card(suit, rank);
				deckOfCards.add(card);
			}
		}		
			
	}	
	
	public boolean draw() throws DeckEmptyException {
		
		if(deckOfCards.isEmpty()) {
			throw new DeckEmptyException("No cards left");
		}					
		
		Card removeRandomCard = this.getRandomCard();		
		return this.draw(removeRandomCard);				
	}
	
	private Card getRandomCard() {
		
		Random random = new Random();		
		int randomSuit = random.nextInt(4);
		int randomRank = random.nextInt(13);
		Suit suit = Suit.values()[randomSuit];
		Rank rank = Rank.values()[randomRank];
		
		Card randomCard = new Card(suit, rank);
		
		return randomCard;		
	}
	
	public boolean draw(Card card) {
		
		boolean cardRemoved = false;
		
		if(deckOfCards.contains(card)) {			
			cardRemoved = deckOfCards.remove(card);
		}
		
		return cardRemoved;
		
	}
	
	public Card getFirstCard() throws DeckEmptyException {
		if(!deckOfCards.isEmpty()) {
			return deckOfCards.getFirst();
		}
		else {
			throw new DeckEmptyException("No cards left");
		}
	}
	
	
	public Card getLastCard() throws DeckEmptyException {
		if(!deckOfCards.isEmpty()) {
			return deckOfCards.getLast();
		}
		else {
			throw new DeckEmptyException("No cards left");
		}
		
	}	
	
	public static void main(String args[]) {
	
		DeckImpl d = new DeckImpl();
		try {
			//d.shuffle();		
			boolean cardRemoved = d.draw();
			System.out.println("card has been removed "+cardRemoved);
			
		}
		catch(DeckEmptyException dee) {
			dee.printStackTrace();
		}
	}

}
