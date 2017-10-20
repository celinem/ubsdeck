package testubsdeck;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ubsdeck.DeckImpl;
import ubsdeck.Rank;
import ubsdeck.Suit;
import ubsdeck.Card;
import ubsdeck.DeckEmptyException;

public class DeckImplTest {
	
	class CardComparator implements Comparator<Card> {
		public int compare(Card c1, Card c2) {
			Comparator<Card> c = Comparator.comparing( s -> s.getSuit());
			
			c = c.thenComparing(s -> s.getRank());
			return c.compare(c1, c2);
		}
	}
	
	private DeckImpl impl = new DeckImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {		
		impl = new DeckImpl();
	}

	@After
	public void tearDown() throws Exception {
		impl = null;
	}
	
	@Test
	public void testSizeBeforeDraw() {
				
		ArrayDeque<Card> testDeck = impl.getDeckOfCards();		
		assertEquals("must have 52 cards", DeckImpl.NO_OF_CARDS, testDeck.size());		
	}
	
	@Test
	public void testSizeAfterDraw()  {
				
		try {
			boolean cardRemoved  = impl.draw();			
			assertEquals("card must be removed ", true, cardRemoved);
			ArrayDeque<Card> testDeck = impl.getDeckOfCards();		
			assertEquals("must have 51 cards", 51, testDeck.size());
		}
		catch(DeckEmptyException dee) {
			fail("Exception should not be thrown if deck is non empyty");
			
		}	
		
	}
	
	@Test(expected =  DeckEmptyException.class)
	public void testThrowDeckEmpyException()  throws DeckEmptyException {		
		impl.clearDeck();		
		impl.draw();
		
	}
	
	@Test
	public void testSameCardNotDrawnTwice()  {
		
		Random random = new Random();		
		int randomSuit = random.nextInt(4);
		int randomRank = random.nextInt(13);
		Suit suit = Suit.values()[randomSuit];
		Rank rank = Rank.values()[randomRank];
		
		Card randomCard = new Card(suit, rank);
		
		boolean cardRemoved = impl.draw(randomCard);
		assertEquals("card must be removed ", true, cardRemoved);
		
		cardRemoved = impl.draw(randomCard);
		assertEquals("card must be removed ", false, cardRemoved);
		
	}


	@Test
	public void testShuffle() {
		
		//Deck contains 52 cards and the order has changed				
		ArrayDeque<Card> beforeShuffle = impl.getDeckOfCards();			
		assertEquals("must have 52 cards before shuffle ", DeckImpl.NO_OF_CARDS, beforeShuffle.size());
		
		impl.shuffle();
		
		ArrayDeque<Card> afterShuffle = impl.getDeckOfCards();	
		assertEquals("must have 52 cards after shuffle", DeckImpl.NO_OF_CARDS, afterShuffle.size());
		
		boolean sameContents = beforeShuffle.containsAll(afterShuffle);
		assertEquals("deck must contain all cards ", true, sameContents);
		
		sameContents = afterShuffle.containsAll(beforeShuffle);
		assertEquals("deck must contain all cards ", true, sameContents);
		
		Card[] arrayOfCardsBeforeShuffle = beforeShuffle.toArray(new Card[beforeShuffle.size()]);
		Card[] arrayOfCardsAfterShuffle = afterShuffle.toArray(new Card[afterShuffle.size()]);
		
		boolean sameArray = Arrays.equals(arrayOfCardsBeforeShuffle, arrayOfCardsAfterShuffle);		
		assertEquals("they are in the same order before sort ", false, sameArray);
		
				
		Arrays.sort(arrayOfCardsBeforeShuffle, new CardComparator());
		Arrays.sort(arrayOfCardsAfterShuffle, new CardComparator());
		
		sameArray = Arrays.equals(arrayOfCardsBeforeShuffle, arrayOfCardsAfterShuffle);		
		assertEquals("they are in the same order after sort ", true, sameArray);		
	}	
		

	@Test
	public void testHasTopCard() {
		try {
			Card c = impl.getFirstCard();
			assertNotNull(c);
		}
		catch(DeckEmptyException dee) {
			fail("Exception should not be thrown if deck is non empyty");
			
		}	
		
	}

	@Test
	public void testHasBottomCard() {
		try {	
			Card c = impl.getLastCard();
			assertNotNull(c);
		}
		catch(DeckEmptyException dee) {
			fail("Exception should not be thrown if deck is non empyty");
			
		}	
	}
	
	@Test(expected =  DeckEmptyException.class)
	public void testHasTopCardThrowsIfEmpty()  throws DeckEmptyException {
		impl.clearDeck();
		Card c = impl.getFirstCard();
		
	}
	
	@Test(expected =  DeckEmptyException.class)
	public void testHasBottomCardThrowsIfEmpty()  throws DeckEmptyException {
		impl.clearDeck();
		Card c = impl.getLastCard();
		
	}

}
