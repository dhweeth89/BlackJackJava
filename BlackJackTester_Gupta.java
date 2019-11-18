import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics;

/**
@author: Shaivya Gupta
Tests out the blackjack game.
*/

public class BlackJackTester_Gupta
{   
   public static void main(String[] args)
   {
      Blackjack game = new Blackjack();
      game.game();      
   }
}   

/**
This class creates a blackjack table with players, dealer,
and deck. It then runs the game.
*/
class Blackjack
{
   private Deck deck;
   private ArrayList<Player> players;
   private Dealer dealer;
   private int playerCount;

/**
Creates the deck and shuffles it, adds player list, asks for # of players,
and creates a blackjack game.
*/   
   public Blackjack()
   {
      deck = new Deck();
      deck.shuffle();
      
      System.out.println("Welcome, everybody, to a wonderful game of 21, or Blackjack!");
      System.out.println("____________________________________________________________");
      System.out.println("How many players? Please enter a positive integer.");
      
      while (playerCount <= 0)
      {
         Scanner scanner = new Scanner(System.in);
      
         if (scanner.hasNextInt())
         {
            playerCount = scanner.nextInt();       
            scanner.nextLine();
         }
         else
         {
            System.out.println("Please enter a positive number");
         }
      }
  
      players = new ArrayList<Player>(); 
      
      for (int i = 0; i < playerCount; i++)
      {
         Scanner scanner = new Scanner(System.in);
         System.out.println("What will your name be, player " + (i+1) + "?");
         String playerName = scanner.nextLine();
         Player player = new Player(playerName);
         players.add(player);
      }
      
      dealer = new Dealer();
   }

/**
Runs the game and all of its logic.
*/   
   public void game()
   {      
      boolean playAgain = true; 
      
      while (playAgain == true)
      {
         
         
         /**
         Decides if deck needs to be remade
         */
         if (deck.size() < 26)
         {
            deck.reinitializeDeck();
         }
         
         
         deck.shuffle();
         
         /**
         Lists out who is playing and their scores
         */
         System.out.println("Dealer wins: " + dealer.getWins());
         
         for (Player player : players)
         {
            System.out.println(player.getName() + " wins: " + player.getWins());
         }
        
         System.out.println();
         Scanner scanner1 = new Scanner(System.in);
         
         /**
         Adds 2 cards to each player's and dealer's hands
         */
         for (int i = 0; i < 2; i++)
         {
            dealer.addCard(deck.deal());
            
            for (Player player : players)
            {
               player.addCard(deck.deal());
            }
         }  
         
         
         /**
         Each player plays his/her hand.
         */
         for (Player player : players)
         {
            System.out.print("Dealer's Hand: ");
            dealer.displayDealerHand();
         
            System.out.println();
            player.displayHand();
         
            while (player.getHandValue() <= 21 && player.getUserChoice() == true)
            {
               player.addCard(deck.deal());
               player.displayHand();
            }
            
            if (player.getHandValue() > 21)
            {
               System.out.println("Ooooo, you busted your hand!");
            }
            
            System.out.println();
         }  
         
         
         /**
         Dealer plays hand
         */
         while (dealer.shouldHit() == true)
         {
            dealer.addCard(deck.deal());
         }
      
         
         /**
         Decides who won
         */
         
         int dealerPoints = dealer.endGameHand();

         for (Player player : players)
         {
            if (dealerPoints < player.getHandValue() && player.getHandValue() <= 21)
            {
               player.isWinner();
               System.out.println(player.getName() + " won!");
            }
            
            else if (dealerPoints == player.getHandValue() && player.getHandValue() <= 21)
            {
               if (dealer.isBlackjack() == true && player.isBlackjack() == false)
               {
                  dealer.isWinner();
               }
               
               else if (dealer.isBlackjack() == false && player.isBlackjack() == true)
               {
                  player.isWinner();
               } 
               
               System.out.println("It's a tie for " + player.getName() + "!");
            }
            
            else if (dealerPoints > player.getHandValue() && dealerPoints <= 21)
            {
               dealer.isWinner();
               System.out.println("Dealer won!");
            }
            
            else if (player.getHandValue() <= 21 && dealerPoints > 21)
            {
               player.isWinner();
               System.out.println(player.getName() + " won!");
            }
            
            else if (dealerPoints <= 21 && player.getHandValue() > 21)
            {
               dealer.isWinner();
               System.out.println("Dealer won!");
            } 
         }
         
         
         
         /**
         Clears the dealer's hand and the player's hand
         */
         for (Player player: players)
         {
            player.clearHand();
         }
         
         dealer.clearHand();
         
         /**
         Asks if person wants to play again
         */
                  
         System.out.println("Would you like to play again? Type Yes or No");
         Scanner scanner = new Scanner(System.in);
         String response = scanner.nextLine();
      
         while (!response.substring(0,1).equals("Y") && !response.substring(0,1).equals("y")
         && !response.substring(0,1).equals("N") && !response.substring(0,1).equals("n"))
         {
            System.out.println("Please enter Yes or No");
            response = scanner.nextLine();
         }
      
         if (response.substring(0,1).equals("N") || response.substring(0,1).equals("n"))
         {
            playAgain = false;
         }
      }
    }
}     
        
      
      
      
         
         
      
      
      
      





/**
The dealer is the class that the rest of the players will be playing against,
run by the game's AI.  It will decide for itself whether to take another card
or to stop.  It will show all of its cards except one while the game is in play,
then it will show its entire hand at the end to compare who won.
*/
class Dealer
{
   private Hand dealerHand;
   private int wins;
/**
Constructs the dealer with a hand and two cards to start off.
*/  
   public Dealer()
   {
      dealerHand = new Hand();
      wins = 0;
   }

/**
The method that will add cards to the dealer's hand.
*/   
   public void addCard(Card card)
   {
      dealerHand.addCard(card);
   }
/**
The method that decides whether the dealer should take another
card or not. It returns true or false, which will then trigger the
addCards method to run if true.
*/  
   public boolean shouldHit()
   {
      int value = dealerHand.getValue();
      if (value <= 16)
      {
         return true;
      }
      else if (value == 17)
      {
         for (Card card : dealerHand.handList())
         {
            if (card.getValue() == 11)
            {
               return true;
            }
         }
      }
      return false;
   }
/**
Shows the dealer's hand minus one, which is hidden from players.
*/   
   public void displayDealerHand()
   {
      for (int i=0; i<dealerHand.size()-1; i++)
      {
         System.out.println(dealerHand.get(i));
      }
   }

/**
Called at the end of a game, where the dealer shows its whole hand
as well as its cumulative point value to decide who won.
*/ 
   public int endGameHand()
   {
      int value = dealerHand.getValue();
           
      System.out.println("Dealer");
      System.out.println("___________________________________________________");
      System.out.println(dealerHand.toString());
      System.out.println(value);
                    
      return value;
   }
   
/**
Empties the dealer's hand
*/
   public void clearHand()
   {
      dealerHand.clear();
   }
   
/**
Returns the number of times dealer has won
*/   
   public int getWins()
   {
      return wins;
   }

/**
Adds a win to the dealer
*/   
   public void isWinner()
   {
      wins += 1;
   }
   
/**
Returns whether hand is a blackjack
*/   
   public boolean isBlackjack()
   {
      return dealerHand.isBlackjack();
   }
}







/**
The player class is essentially the user interface. It maintains the player's hand
and interacts with the player to display the hand to them and then ask if they want
to hit. It also returns the value back to be used later.
*/
class Player
{
   private Hand hand;
   private String name;
   private int win;
   
/**
The constructor creates the hand based off a deck entered into it. (at least for testing)
*/
   public Player(String playerName)
   {
      hand = new Hand();
      name = playerName;
      win = 0;
   }

/**
This method allows for the player to add a card to his deck.
*/
   
   public void addCard(Card card)
   {
      hand.addCard(card);
   }
   
      
/**
displayHand shows the user his/her hand and its value.
*/
   public void displayHand()
   {
      System.out.println(name);
      System.out.println("___________________________________________________");
      hand.getValue();
      System.out.println(hand.toString());
      System.out.print("Total points: ");
      System.out.println(hand.getValue());
   }
   
/**
getUserChoice asks the user if he/she wants to hit.
*/   
   public boolean getUserChoice()
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Would you like to get a card? Type Yes or No");
      String response = scanner.nextLine();
      
      while (!response.substring(0,1).equals("Y") && !response.substring(0,1).equals("y")
      && !response.substring(0,1).equals("N") && !response.substring(0,1).equals("n"))
      {
         System.out.println("Please enter Yes or No");
         response = scanner.nextLine();
      }
      
      if (response.substring(0,1).equals("Y") || response.substring(0,1).equals("y"))
      {
         return true;
      }
      
      return false;
   }
   
/**
getHandValue returns the point value of the hand.
*/   
   public int getHandValue()
   {
      return hand.getValue();
   }

/**
Returns the name of the player
*/   
   public String getName()
   {
      return name;
   }

/**
Empties the player's hand
*/   
   public void clearHand()
   {
      hand.clear();
   }

/**
Returns the number of times the player has won
*/   
   public int getWins()
   {
      return win;
   }

/**
Adds a win to the player
*/   
   public void isWinner()
   {
      win += 1;
   }

/**
Returns whether hand is a blackjack
*/      
   public boolean isBlackjack()
   {
      return hand.isBlackjack();
   }   
}  







/**
The card class is what allows each individual card to exist with
its features of number/letter rank, its suit, and its point value.
*/  
class Card
{
 	String suit = "";
  	String rank = "";
  	int pointValue = 0;

/**
Constructs the card with its suit, rank, and pointValue.
*/   
  	public Card(String cardRank, String suitOfCard, int valueOfCard)
  	{
  		suit =  suitOfCard;
  		rank = cardRank;
  		pointValue = valueOfCard;
  	}

/**
Returns the Suit.
*/   
  	public String getSuit()
  	{
  		return suit;
  	}

/**
Returns the Rank (2-10, J, Q, K, A).
*/     	
  	public String getRank()
  	{
  		return rank;
  	}

/**
Returns the point value of the card.
*/  	
  	public int getValue()
  	{
  		return pointValue;
  	}

/**
Prints out the Card (its rank, its suit, and for now its point value.
*/  	
  	public String toString()
  	{
  		String result =  rank + " of " + suit + " (point value = " + pointValue + ")";
  		return result;
  	}

/**
Useful for the Ace cards, it allows for the card
to switch its pointValue to 1.
*/  	
  	public int setValue()
  	{
         pointValue = 1;
  		   return pointValue;
  	}
}







class Deck
{
   private int size = 0;
   private ArrayList<Card> deck = new ArrayList<Card>();
   private String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
   private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

/**
Constructs the deck based off the arrays of suits and ranks,
so each suit gets all the number/letter values.
*/   
   public Deck()
   {
      for (int i=0; i<ranks.length; i++)
      {
         for (int j=0; j<suits.length; j++)
         {
            if (i<9)
            {
               Card card = new Card(ranks[i], suits[j], i+2);
               deck.add(card);
            }
            
            if (i>=9 && i<12)
            {
               Card card = new Card(ranks[i], suits[j], 10);
               deck.add(card);
            }
            
            if (i==12)
            {
               Card card = new Card(ranks[i], suits[j], 11);
               deck.add(card);
            }
         }
      }
   }   

/**
Returns the size of the deck.
*/
   public int size()
   {
      return deck.size();   
   }

/**
Deals a card from the top of deck if there is a card to deal.
Also removes that card from deck so the same card isn't given
to two people.
*/
   public Card deal()
   {
      if (deck.size() > 0)
      {
         Card dealtCard = deck.get(size()-1);
         deck.remove(size()-1);
         return dealtCard;
      }
      
      else
      {
         return null;
      }
   }

/**
Shuffles the deck so the cards aren't in order
and new cards are dealt out.
*/   
   public void shuffle()
   {
      for (int k = deck.size()-1; k >= 0; k--)
      {
         int r = (int) (Math.random()*k) + 1;
         Card temp = deck.get(r);
         Card temp2 = deck.get(k);
         deck.set(r, temp2);
         deck.set(k, temp);
      }
   }

/**
Returns the ArrayList for it to be manipulated, useful for
gaining back cards that are removed.
*/
   
   public ArrayList<Card> deckList()
   {
      return deck;
   } 

/**
Reinitializes deck so it's a full deck once again
*/   
   public void reinitializeDeck()
   {
      int deckSize = deck.size();
      
      for (int i=0; i<deckSize; i++)
      {
         deck.remove(0);
      }
      
      for (int i=0; i<ranks.length; i++)
      {
         for (int j=0; j<suits.length; j++)
         {
            if (i<9)
            {
               Card card = new Card(ranks[i], suits[j], i+2);
               deck.add(card);
            }
            
            if (i>=9 && i<12)
            {
               Card card = new Card(ranks[i], suits[j], 10);
               deck.add(card);
            }
            
            if (i==12)
            {
               Card card = new Card(ranks[i], suits[j], 11);
               deck.add(card);
            }
         }
      }            
   }
         
}





class Hand
{
   ArrayList<Card> cards;
   int sumOfValues = 0;
   int aceCounter = 0;
   
/**
Constructs a Hand, an arrayList of cards.
*/   
   public Hand()
   {
     cards = new ArrayList<Card>();
   }

/**
When called, it adds a card to the hand.
*/   
   public void addCard (Card card)
   {
      cards.add(card);
      
      if (card.getRank().equals("Ace"))
         {
            aceCounter++;
         }  
   }

/**
WHen called, it gets the value of the hand and checks
to see if Ace should change from 11 points to 1 point.
*/   
   public int getValue()
   {      
      sumOfValues = 0;
      
      for (Card card : cards)
      {             
         sumOfValues = sumOfValues + card.getValue();
      }
      
      
      if (aceCounter > 0)
      {
         if (sumOfValues > 21)
         {
            sumOfValues = 0;
         
            for (Card card : cards)
            {
               if (card.getRank().equals("Ace"))
               {
                  if (card.getValue() + (aceCounter*11) > 21)
                  {   
                     card.setValue();
                     aceCounter--;
                  }
               }
                     
               sumOfValues = sumOfValues + card.getValue();
            }
         }
      }    
         
      return sumOfValues;
   }
/**
Checks if a hand is busted (over 21 points)
*/   
   public boolean isBusted()
   {
      if (sumOfValues > 21)
      {
         return true;
      }
      
      return false;
   }     

/**
Returns the size of the hand.
*/   
   public int size()
   {
      return cards.size();
   }

/**
Returns a card from the hand.
*/   
   public Card get(int i)
   {
      return cards.get(i);
   }
   
/**
Returns hand in String form.
*/   
   public String toString()
   {  
      String results = "";
      
      for (Card card : cards)
      {
         results += card + "  ";
      }
      
      return results;
   }

/**
Returns the array that makes up the hand so it
can be manipulated. To remove cards from hand to
deck or check if hand has ace.
*/   
   public ArrayList<Card> handList()
   {
      return cards;      
   }

/**
Clears the hand so the hand is empty and ready
to be filled again
*/
   
   public void clear()
   {
      cards.clear();
   }
   
   public boolean isBlackjack()
   {
      if (sumOfValues == 21 && cards.size() == 2)
      {
         return true;
      }
      return false;
   }
}