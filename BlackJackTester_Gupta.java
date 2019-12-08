/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohnish
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mohnish
 */
enum State 
{
    TITLE_SCREEN, CREATE_ACCOUNT, LOGIN, LOGOUT, ROOM_SELECT, PLAY_GAME, EXIT
}


public class BlackJackTester_Gupta
{   
   public static void main(String[] args)
   {
      MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
      MongoDatabase database = mongoClient.getDatabase("blackjack");
      MongoCollection<Document> collection = database.getCollection("accounts");
      Document user = new Document("username", "MongoDB")
                .append("password", "database")
                .append("money", 10);
      collection.insertOne(user);
      String username = "";
      boolean loggedIn = false;
      
      
      State state = State.TITLE_SCREEN;
      
      //NICOLE AND MOHNISH
      //In each state, except play game state, remember to check if user presses x, it goes to a popup menu that asks if they are sure
      //If sure == true, then state = exit; then next loop, main is exited
      while (state != State.EXIT)
      {
        switch(state)
        {
            case TITLE_SCREEN:
                if (loggedIn == true)
                {
                    
                }
                else
                {
                    
                }
                break;
            //NICOLE AND MOHNISH: Figure out how to enter account creation data to MongoDB collection users
            //Make sure to error check that username doesn't already exist in database
            case CREATE_ACCOUNT:
                break;
            case ROOM_SELECT:
                
                
                //if 
                break;
            
            case PLAY_GAME: 
               Blackjack game = new Blackjack();    
               game.game();
            case EXIT:
                break;
            case LOGIN:
                Scanner scanner1 = new Scanner(System.in);
                Scanner scanner2 = new Scanner(System.in);
                
                String usernameEntered = scanner1.nextLine();
                String passwordEntered = scanner2.nextLine();
                
                //Check with MongoDB to ensure that both username entered and password entered are correct
                //if (userNameEntered == )
                  
                
                
                break;
            case LOGOUT:
                break;
        }
      }
    
            
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
   
   void GetRoom(Player player)
   {
       if (player.getMoney() >= 20000)
               {
                   //Fill with MongoDB Info: Create a new building in "Buildings" collection with username stored
                   player.buyGameRoom(20000);
                   
               }
   }
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
            if (playerCount <= 0)
            {
               System.out.println("Please enter a positive number");      
            }
            scanner.nextLine();
         }
         else
         {
            System.out.println("Please enter a number");
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
            System.out.println("$" + player.getMoney() + " remaining");
            System.out.println();
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
         Each players places their bets.
         */
         
         /**
Puts money amount that player enters into pot and removes
from player
   
   public void putMoneyInPot(int betAmount)
   {
      pot = betAmount;
      money -= betAmount;
   }
*/

         System.out.println("Onto betting");

         for (Player player : players)
         {
            System.out.println("How much would you like to bet, " + player.getName() + "?");
            int bettingAmount = 0;
            int amountOfMoneyHad = player.getMoney();
            
            System.out.println(player.getName() + ": " + amountOfMoneyHad);
            
            
            
            while (bettingAmount <= 0 || bettingAmount > amountOfMoneyHad)
            {
               Scanner scanner = new Scanner(System.in);
            
               if (scanner.hasNextInt())
               {
                  bettingAmount = scanner.nextInt();
                  scanner.nextLine();
                  
                  if (bettingAmount <= 0)
                  {
                     System.out.println("Please enter a positive number");
                  }
                  else if (bettingAmount > amountOfMoneyHad)
                  {
                     System.out.println("You do not have enough money for this. Please enter a new value.");
                  }
                  else
                  {  
                     player.putMoneyInPot(bettingAmount);
                  }
               }
               else
               {
                  System.out.println("Please enter a number");
               }
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
            //Case that player has more points, within accepted range, than dealer
            if (dealerPoints < player.getHandValue() && player.getHandValue() <= 21)
            {
               player.isWinner();
               System.out.println(player.getName() + " won!");
               
               if (player.isBlackjack() == true)
               {
                  player.blackjackWin();
               }
               else
               {
                  player.regularWin();
               }
               
               player.resetPot();
               
            }
            
            //Case that both players have same point values
            else if (dealerPoints == player.getHandValue() && player.getHandValue() <= 21)
            {
               //If dealer has blackjack but player doesn't, dealer gets priority
               if (dealer.isBlackjack() == true && player.isBlackjack() == false)
               {
                  System.out.println(player.getName() + " lost.");
                  dealer.isWinner();
                  player.resetPot();
               }
               //If player has priority and dealer doesn't, player has priority
               else if (dealer.isBlackjack() == false && player.isBlackjack() == true)
               {
                  System.out.println(player.getName() + " won!");
                  player.isWinner();
                  player.blackjackWin();
                  player.resetPot();
               }
               //If both have blackjack or neither has blackjack, it's a tie
               else
               { 
                  System.out.println("It's a tie for " + player.getName() + " and Dealer!");
                  player.tieMoney();
                  player.resetPot();
               }
            }
            //If dealer has more points, within accepted range, than player
            else if (dealerPoints > player.getHandValue() && dealerPoints <= 21)
            {
               dealer.isWinner();
               System.out.println(player.getName() + " lost.");
               player.resetPot();
            }
            
            //If dealer busts and player does not
            else if (player.getHandValue() <= 21 && dealerPoints > 21)
            {
               player.isWinner();
               
               if (player.isBlackjack() == true)
               {
                  player.blackjackWin();
               }
               else
               {
                  player.regularWin();
               }
               
               player.resetPot();            
               
               System.out.println(player.getName() + " won!");
            }
            //If player busts and dealer does not
            else if (dealerPoints <= 21 && player.getHandValue() > 21)
            {
               dealer.isWinner();
               System.out.println(player.getName() + " lost!");
               player.resetPot();
            }
            //If everyone busts, nobody wins but player loses money
            else if (dealerPoints > 21 && player.getHandValue() > 21)
            {
               System.out.println("Both " + player.getName() + " and Dealer lost.");
               player.resetPot();                
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
         
         for (Player player : new ArrayList<Player>(players))
         {
            if (player.getMoney() <= 0)
            {
               System.out.println(player.getName() + " has run out of money like the idiot (s)he is. Okay, byebye.");
               players.remove(player);
            }
         }
         
         /*
         for (Player player : players)
         {
            if (player.getMoney() <= 0)
            {
               System.out.println(player.getName() + " has run out of money like the idiot (s)he is. Okay, byebye.");
               players.remove(player);
            }
         }
         **/      
         
         
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
   private int money;
   private int pot;
   private boolean playing;
   
   
/**
The constructor creates the hand based off a deck entered into it. (at least for testing)
*/
   public Player(String playerName)
   {
      hand = new Hand();
      name = playerName;
      win = 0;
      money = 1000;
      playing = false;
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
Returns the amount of money a player has
*/

   public int getMoney()
   {
      return money;
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

/**
Tells whether player is out of money
*/
   public boolean isOutOfMoney()
   {
      if (money <= 0)
      {   
         return true;
      }
      else
      {
         return false;
      }
   }

/**
Puts money amount that player enters into pot and removes
from player
*/
   
   public void putMoneyInPot(int betAmount)
   {
      pot = betAmount;
      money -= betAmount;
   }

/**
Resets a player's bet to 0 to use at the end of each round
*/
   
   public void resetPot()
   {
      pot = 0;
   }
   
/**
Adds 1.5x money for a player who has a blackjack when dealer does not. Logic applied in the game function
*/   
   public void blackjackWin()
   {
      money += (pot + 1.5 * pot);
   }
   
/**
Adds money for a player who has won. Logic of when used applied in the game function
*/   
   
   public void regularWin()
   {
      money += (2 * pot);
   }

/**
Returns money to player from pot when it is a tie
*/

   public void tieMoney()
   {
      money += pot;
   }
   
   public void buyGameRoom(int money)
   {
       this.money -= money;
   }
   
   public void addMoneyToGameRoom(int money)
   {
       this.money -= money;       
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
   boolean isFaceUp;

/**
Constructs the card with its suit, rank, and pointValue.
*/   
  	public Card(String cardRank, String suitOfCard, int valueOfCard)
  	{
  		suit =  suitOfCard;
  		rank = cardRank;
  		pointValue = valueOfCard;
      isFaceUp = false;
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
   
   public void makeFaceUp()
   {
      isFaceUp = true;
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
      
      //Checks for aces to change their values if necessary
      if (aceCounter > 0)
      {
         
         //Only need to change if the sumOfValues is > 21
         if (sumOfValues > 21)
         {
         
            //For each card in the hand
            for (Card card : cards)
            {
               
               //Check if this card is an ace and that it hasn't already been set to a value of 1
               if (card.getRank().equals("Ace") && card.getValue() != 1)
               {
                  
                  //If the current sum is > 21, set the Ace to a value of 1 and decrease sum by 10.
                  //Reduce aceCounter by 1 so that when no more aces can be changed again, this part of code doesn't run
                  if (sumOfValues > 21)
                  {   
                     card.setValue();
                     aceCounter--;
                     sumOfValues -= 10;
                  }
               }
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
    /**
     * @param args the command line arguments
     */

        // TODO code application logic here
    
    


