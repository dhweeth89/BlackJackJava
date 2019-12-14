import java.net.UnknownHostException;
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.QueryBuilder;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.DB;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.DBCursor;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.Node;

/**
 @author: Shaivya Gupta
 Tests out the blackjack game.
 */

public class BlackJackTester_Gupta
{
    enum State
    {
        TITLE_SCREEN,
        CREATE_ACCOUNT,
        MANAGE_ROOMS,
        ROOM_SELECT,
        PLAY_GAME,
        EXIT,
        LOGIN,
        LOGOUT
    }


    public static void main(String[] args) throws NoSuchAlgorithmException
    {



        int money;
        String name;
        String building_name;
        boolean loggedIn = false;
        String usureusure = "";
        boolean isDefault;//mongo is false
        boolean okiedokie = false;
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("Building");
        MongoCollection<Document> collection = database.getCollection("Rooms");

        MongoClient mongoClient2 = new MongoClient( "localhost" , 27017 );
        MongoDatabase database2 = mongoClient2.getDatabase("blackjack");
        MongoCollection<Document> collection2 = database2.getCollection("accounts");


        //note:   b for BACK BUTTON
        //        r for START
        //        c for CREATE
        //        m for MANAGE
        //        x for EXIT
        //        i for LOGIN
        //        o for LOGOUT
        //        l for LIST OPTIONS OF PAGE


        State state = State.TITLE_SCREEN;

        //NICOLE AND MOHNISH
        //In each state, except play game state, remember to check if user presses x, it goes to a popup menu that asks if they are sure
        //If sure == true, then state = exit; then next loop, main is exited

        //System.out.println("Click enter to start the game");

        while (state != State.EXIT)
        {
            System.out.println("Type l to view options for this part of menu");

            switch(state)
            {
                case TITLE_SCREEN:
                    System.out.println("You are in title screen");
                    Scanner scannr = new Scanner(System.in);
                    String state1 = scannr.nextLine();
                    //@MOHNISH THIS ONEʻS FOR YOU BROO
                    //username = ??
                    //money == ??
                    if (loggedIn == true)
                    {
                        System.out.println("Username: " + name);
                        System.out.println("$$$ Balance: " + money);

                        if (state1.equals("r")) //when start button is pressed, returns r for room select
                        {
                            state = State.ROOM_SELECT;
                        }
                        else if (state1.equals("c"))
                        {
                            state = State.CREATE_ACCOUNT;
                        }
                        else if (state1.equals("m"))
                        {
                            state = State.MANAGE_ROOMS;
                        }
                        else if (state1.equals("o"))
                        {
                            state = State.LOGOUT;
                        }
                        else if (state1.equals("x"))
                        {
                            Scanner input = new Scanner(System.in);
                            System.out.println("Are you sure you would like to quit?");
                            usureusure  = input.nextLine();
                            okiedokie = false;
                            while (okiedokie == false)
                            {
                                if (usureusure.equals("y"))
                                {
                                    okiedokie = true;
                                    state = State.EXIT;
                                }
                                else if (usureusure.equals("n"))
                                {
                                    okiedokie = true;
                                    state = State.TITLE_SCREEN;
                                }
                                else
                                {
                                    //Scanner scanner1 = new Scanner(System.in);
                                    System.out.println("Just a simple yes or no would suffice...");
                                    usureusure  = input.nextLine();
                                }
                            }
                        }
                        else if (state1.equals("l"))
                        {
                            System.out.println("Type r for Room Select to play in");
                            System.out.println("Type c for Create Account to make an account");
                            System.out.println("Type m to Manage your Buildings, or buy your own");
                            System.out.println("Type o to log out");
                            System.out.println("Type x to Exit the App");
                            state = State.TITLE_SCREEN;
                        }
                    }
                    else //not logged in, main menue @ author NicoleSUx
                    {
                        if (state1.equals("r"))
                        {
                            state = State.ROOM_SELECT;
                        }
                        else if (state1.equals("c"))
                        {
                            state = State.CREATE_ACCOUNT;
                        }
                        else if (state1.equals("i"))
                        {
                            state = State.LOGIN;
                        }
                        else if (state1.equals("x"))
                        {
                            Scanner input = new Scanner(System.in);
                            System.out.println("Are you sure you would like to quit? Type y or n");
                            usureusure  = input.nextLine();
                            okiedokie = false;
                            while (okiedokie == false)
                            {
                                if (usureusure.equals("y"))
                                {
                                    okiedokie = true;
                                    state = State.EXIT;
                                }
                                else if (usureusure.equals("n"))
                                {
                                    okiedokie = true;
                                    state = State.TITLE_SCREEN;
                                }
                                else
                                {
                                    Scanner input2 = new Scanner(System.in);
                                    System.out.println("Just a simple y or n would suffice...");
                                    usureusure  = input2.nextLine();
                                }
                            }
                        }
                        else if (state1.equals("l"))
                        {
                            System.out.println("Type r for Room Select to play in");
                            System.out.println("Type c for Create Account to make an account");
                            System.out.println("Type i for Logging In to your Account; You must login once you create an account");
                            System.out.println("Type x to Exit the App");
                            state = State.TITLE_SCREEN;
                        }
                    }
                    break;
                //NICOLE AND MOHNISH: Figure out how to enter account creation data to MongoDB collection users
                //Make sure to error check that username doesn't already exist in database
                case CREATE_ACCOUNT:
                    System.out.println("You are in create_account screen");
                    //CREATE ACCOUNT FUNCTION HERE
                    if (loggedIn)
                    {
                        System.out.println("Username: " + name);
                        System.out.println("$$$ Balance: " + money);
                    }


                    Scanner scann = new Scanner(System.in);
                    state1 = scann.nextLine();

                    if (state1.equals("x"))
                    {
                        Scanner input = new Scanner(System.in);
                        System.out.println("Are you sure you would like to quit?");
                        usureusure  = input.nextLine();
                        okiedokie = false;
                        while (okiedokie == false)
                        {
                            if (usureusure.equals("y"))
                            {
                                okiedokie = true;
                                state = State.EXIT;
                            }
                            else if (usureusure.equals("n"))
                            {
                                okiedokie = true;
                                state = State.CREATE_ACCOUNT;
                            }
                            else
                            {
                                Scanner input3 = new Scanner(System.in);
                                System.out.println("Just a simple yes or no would suffice...");
                                usureusure  = input3.nextLine();
                            }
                        }
                    }
                    else if (state1.equals("b"))
                    {
                        state = State.TITLE_SCREEN;
                    }
                    else if (state.1.equals("c"))
                {
                    //do the stuff pls @mohniSH
                    Signup signup = new Signup();


                    System.out.println("Please create a new username");
                    Scanner userscanner = new Scanner(System.in);
                    String username = userscanner.nextLine();


                    System.out.println("Please create a password");
                    Scanner passscanner = new Scanner(System.in);
                    String password = passscanner.nextLine();

                    if (username.length() < 1 || username.length() > 10 || password.length() < 6
                            || password.length() > 12 )
                    {
                        //Insert alert flash messages for username/password length incorrect
                        state = State.CREATE_ACCOUNT;
                    }
                    else
                    {
                        boolean isValid = signup.MakeAccount(username, password);

                        if (isValid)
                        {
                            state = State.TITLE_SCREEN;
                        }
                        else
                        {
                            //Insert alert message for username being taken
                            state = State.CREATE_ACCOUNT;
                        }
                    }
                }
                    else if (state1.equals("l"))
                {
                    System.out.println("Type c for Create Account to make an account");
                    System.out.println("Type x to Exit the App");
                    System.out.println("Type b to go back to Title Screen");
                    state = State.CREATE_ACCOUNT;
                }
                break;

                case MANAGE_ROOMS: //MOHNISH  ierate thrpigh rpoms of user
                    // ??????????????? @moHNIsh

                    Block<Document> printBlock = new Block<Document>() {
                        @Override
                        public void apply(final Document document) {
                            System.out.println(document.toJson());
                        }
                    };

                    collection.find(gt("owner", name).forEach(printBlock);

                    Scanner scannr3 = new Scanner(System.in);
                    state1 = scannr3.nextLine();



                    if (state1.equals("x"))
                    {
                        Scanner input = new Scanner(System.in);
                        System.out.println("Are you sure you would like to quit?");
                        usureusure  = input.nextLine();
                        okiedokie = false;
                        while (okiedokie == false)
                        {
                            if (usureusure.equals("y"))
                            {
                                okiedokie = true;
                                state = State.EXIT;
                            }
                            else if (usureusure.equals("n"))
                            {
                                okiedokie = true;
                                state = State.MANAGE_ROOMS;
                            }
                            else
                            {
                                Scanner input4 = new Scanner(System.in);
                                System.out.println("Just a simple yes or no would suffice...");
                                usureusure  = input4.nextLine();
                            }
                        }
                    }
                    else if (state1.equals("b"))
                    {
                        state = State.TITLE_SCREEN;
                    }
                    else if (state1.equals("r"))
                    {
                        if ()
                        {

                        }
                    }

                    else if (state.equals("l"))
                    {
                        System.out.println("Type a number to index a room for Create Account to make an account");
                        System.out.println("Type x to Exit the App");
                        System.out.println("Type b to go back to Title Screen");
                        System.out.println("Type r to purchase a new room for $20000")
                        state = State.MANAGE_ROOMS;
                    }

                    break;
                case ROOM_SELECT: //MOHNISH
                    System.out.println("You are in room select screen");

                    Block<Document> printBlock2 = new Block<Document>() {
                        @Override
                        public void apply(final Document document) {
                            System.out.println(document.toJson());
                        }
                    };

                    collection.find(gt("owner", user)).forEach(printBlock2);


                    Scanner scannr5 = new Scanner(System.in);
                    state1 = scannr5.nextLine();

                    if (state1.equals("x"))
                    {
                        Scanner input = new Scanner(System.in);
                        System.out.println("Are you sure you would like to quit?");
                        usureusure  = input.nextLine();
                        okiedokie = false;
                        while (okiedokie == false)
                        {
                            if (usureusure.equals("y"))
                            {
                                okiedokie = true;
                                state = State.EXIT;
                            }
                            else if (usureusure.equals("n"))
                            {
                                okiedokie = true;
                                state = State.ROOM_SELECT;
                            }
                            else
                            {
                                Scanner input6 = new Scanner(System.in);
                                System.out.println("Just a simple yes or no would suffice...");
                                usureusure  = input6.nextLine();
                            }
                        }
                    }
                    else if (state1.equals("b"))
                    {
                        state = State.TITLE_SCREEN;
                    }
                    /**
                     *
                     *

                     */


                    break;
                case PLAY_GAME: //NICOLE
                    System.out.println("You are in play_game screen");
                    //select room
                    Scanner scannr7 = new Scanner(System.in);
                    state1 = scannr7.nextLine();

                    if (state1.equals("x"))
                    {
                        Scanner input = new Scanner(System.in);
                        System.out.println("Are you sure you would like to quit?");
                        usureusure  = input.nextLine();
                        okiedokie = false;
                        while (okiedokie == false)
                        {
                            if (usureusure.equals("y"))
                            {
                                okiedokie = true;
                                state = State.EXIT;
                            }
                            else if (usureusure.equals("n"))
                            {
                                okiedokie = true;
                                state = State.PLAY_GAME;
                            }
                            else
                            {
                                Scanner input9 = new Scanner(System.in);
                                System.out.println("Just a simple yes or no would suffice...");
                                usureusure  = input9.nextLine();
                            }
                        }
                    }
                    else if (state1.equals("b"))
                    {
                        state = State.ROOM_SELECT;
                    }

                    //Blackjack game = new Blackjack();
                    //game.game();
                    //state = State.TITLE_SCREEN;

                case EXIT:
                    break;
                case LOGIN: //@uthor nicOLE
                    System.out.println("You are in the login page");

                    Scanner scannr9 = new Scanner(System.in);
                    Scanner scanner1 = new Scanner(System.in);
                    Scanner scanner2 = new Scanner(System.in);

                    state1 = scannr9.nextLine();

                    if (state1.equals("s")) //to sign in
                    {
                        System.out.println("Please enter your username:");
                        String usernameEntered = scanner1.nextLine();
                        System.out.println("Please enter your password:");
                        String passwordEntered = scanner2.nextLine();

                        Login login = new Login();

                        if (login.LoginSuccessOrNah(usernameEntered, passwordEntered) == true)
                        {
                            name = usernameEntered;
                            Document document = collection
                                    .find(new BasicDBObject("username", usernameEntered))
                                    .projection(Projections.fields(Projections.include("money"), Projections.excludeId())).first();
                            money = document.getInteger("money");
                            usernameEntered = name;
                        }
                        //Check with MongoDB to ensure that both username entered and password entered are correct
                        //if (userNameEntered == )
                    }

                    if (state1.equals("l"))
                    {
                        System.out.println("Type b to go back to the title screen");
                        System.out.println("Type f to pay respects");
                        System.out.println("Type s to sign in");
                        System.out.println("Type x to exit");
                    }

                    if (state1.equals("f"))
                    {
                        System.out.println("Respects paid.");
                    }

                    if (state1.equals("x"))
                    {
                        Scanner input = new Scanner(System.in);
                        System.out.println("Are you sure you would like to quit?");
                        usureusure  = input.nextLine();
                        okiedokie = false;
                        while (okiedokie == false)
                        {
                            if (usureusure.equals("y"))
                            {
                                okiedokie = true;
                                state = State.EXIT;
                            }
                            else if (usureusure.equals("n"))
                            {
                                okiedokie = true;
                                state = State.LOGIN;
                            }
                            else
                            {
                                Scanner input9 = new Scanner(System.in);
                                System.out.println("Just a simple yes or no would suffice...");
                                usureusure  = input9.nextLine();
                            }
                        }
                    }
                    else if (state1.equals("b"))
                    {
                        state = State.TITLE_SCREEN;
                    }
                case LOGOUT:
                    System.out.println("You arein logout screen");
                    Scanner input = new Scanner(System.in);
                    System.out.println("Are you sure you would like to logout? (yes or no)");
                    usureusure  = input.nextLine();
                    Scanner scannr10 = new Scanner(System.in);
                    state1 = scannr10.nextLine();

                    if (state1.equals("x"))
                    {
                        Scanner input11 = new Scanner(System.in);
                        System.out.println("Are you sure you would like to quit?");
                        usureusure  = input11.nextLine();
                        okiedokie = false;
                        while (okiedokie == false)
                        {
                            if (usureusure.equals("y"))
                            {
                                okiedokie = true;
                                state = State.EXIT;
                            }
                            else if (usureusure.equals("n"))
                            {
                                okiedokie = true;
                                state = State.LOGOUT;
                            }
                            else
                            {
                                Scanner input12 = new Scanner(System.in);
                                System.out.println("Just a simple yes or no would suffice...");
                                usureusure  = input12.nextLine();
                            }
                        }
                    }
                    okiedokie = false;
                    while (okiedokie == false)
                    {
                        if (usureusure.equals("y"))
                        {
                            okiedokie = true;
                            loggedIn = false;
                            name = "";
                            money = 0;
                            state = State.TITLE_SCREEN;
                        }
                        else if (usureusure.equals("n"))
                        {
                            okiedokie = true;
                            loggedIn = true;
                            state = State.TITLE_SCREEN;
                        }
                        else
                        {
                            Scanner input12 = new Scanner(System.in);
                            System.out.println("Just a simple yes or no would suffice...");
                            usureusure  = input12.nextLine();
                        }
                    }
                    break;
            }
        }


    }
}

//# END OF MAIN






/**
 Blackjack game = new Blackjack();
 game.game();
 */
/**
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
 }
 */

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
    private int roomMoney;
    private boolean isDefault;
    private String buildingName;

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
        buildingName = "Bot Room";
        deck = new Deck();
        deck.shuffle();
        roomMoney = 200000;
        isDefault = true;

        System.out.println("Welcome, everybody, to a wonderful game of 21, or Blackjack!");
        System.out.println("____________________________________________________________");
        System.out.println("How many players? 1-4 players.");

        while (playerCount <= 0 || playerCount > 4)
        {
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt())
            {
                playerCount = scanner.nextInt();
                if (playerCount <= 0)
                {
                    System.out.println("Please enter a positive number");
                }
                if (playerCount > 4)
                {
                    System.out.println("Max 4 players. Enter a new number.");
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


    public Blackjack(String roomName, int moneys) throws NoSuchAlgorithmException
    {
        buildingName = roomName;
        roomMoney = moneys;
        deck = new Deck();
        deck.shuffle();
        isDefault = false;

        System.out.println("Welcome, everybody, to a wonderful game of 21, or Blackjack!");
        System.out.println("____________________________________________________________");
        System.out.println("How many players? 1-4 players.");

        while (playerCount <= 0 || playerCount > 4)
        {
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt())
            {
                playerCount = scanner.nextInt();
                if (playerCount <= 0)
                {
                    System.out.println("Please enter a positive number");
                }
                if (playerCount > 4)
                {
                    System.out.println("Max 4 players. Enter a new number.");
                }
                scanner.nextLine();
            }
            else
            {
                System.out.println("Please enter a number");
            }
        }

        players = new ArrayList<Player>();
/**
 for (int i = 0; i < playerCount; i++)
 {
 Scanner scanner = new Scanner(System.in);
 System.out.println("What will your name be, player " + (i+1) + "?");
 String playerName = scanner.nextLine();
 Player player = new Player(playerName);
 players.add(player);
 }
 */

        dealer = new Dealer();

        for (int i = 0; 1 < playerCount; i++)
        {
            Login login = new Login();
            boolean condition = false;

            while(condition == false)
            {
                Scanner user = new Scanner(System.in);
                System.out.print("Enter your Username:");
                String name = user.nextLine();


                Scanner pass = new Scanner(System.in);
                System.out.print("Enter your Password:");
                String password = pass.nextLine();

                if (login.LoginSuccessOrNah(name, password) == true)
                {
                    condition = true;
                    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
                    MongoDatabase database = mongoClient.getDatabase("blackjack");
                    MongoCollection<Document> collection = database.getCollection("accounts");

                    Document document = collection
                            .find(new BasicDBObject("username", name))
                            .projection(Projections.fields(Projections.include("money"), Projections.excludeId())).first();

                    int moneyStored = document.getInteger("money");

                    Player player = new Player(name, moneyStored); //(playername , //moneyStored);
                    players.add(player);
                }
                else
                    condition = false;
            }
        }
    }




    /**
     Runs the game and all of its logic.
     */
    public void game()
    {
        boolean playAgain = true;

        /**
         int temp;
         int temp2 = 30;
         double temp3;

         temp3 = (1.5) * temp2;
         temp = (int)temp3;

         System.out.println(temp3);
         System.out.println(temp);
         */

        while (playAgain == true)
        {

            boolean blackjackState = false;

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
            System.out.println();
            System.out.println(this.buildingName);
            System.out.println("__________________________________________________________");


            //System.out.println("Dealer wins: " + dealer.getWins());
            System.out.println("Dealer money: $" + this.roomMoney);

            for (Player player : players)
            {
                //System.out.println(player.getName() + " wins: " + player.getWins());
                System.out.println(player.getName() + ": $" + player.getMoney() + " remaining");
                System.out.println();
            }

            System.out.println();
            //Scanner scanner1 = new Scanner(System.in);

            /**
             Adds 2 cards to each player's and dealer's hands
             */
            for (int i = 0; i < 2; i++)
            {
                dealer.addCard(deck.deal());
            }

            for (Player player : players)
            {
                Hand hand = new Hand();
                {
                    for (int i = 0; i < 2; i++)
                    {
                        player.addCard(deck.deal(), hand, 0);
                    }
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
            System.out.println("Dealer");
            dealer.displayDealerHand();

            System.out.println("__________________________________________________________");

            System.out.println("Onto betting");

            for (Player player : players)
            {
                System.out.println("How much would you like to bet, " + player.getName() + "?");
                int bettingAmount = 0;
                int amountOfMoneyHad = player.getMoney();

                System.out.println(player.getName() + ": $" + amountOfMoneyHad);



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
                            player.putMoneyInPot(bettingAmount, 0);
                        }
                    }
                    else
                    {
                        System.out.println("Please enter a number");
                    }
                }
            }

            /**
             System.out.print("Dealer's Hand: ");
             dealer.displayDealerHand();
             System.out.println();

             for (Player player : players)
             {
             player.displayHand();
             System.out.println();
             }
             */

            dealer.getHandValue();

            //System.out.println("dealer.isBlackjack == " + dealer.isBlackjack());

            if (dealer.isBlackjack() == true)
            {
                dealer.endGameHand();
                blackjackState = true;

                for (Player player : players)
                {
                    int count = 0;
                    for (Hand hand : player.getHandArray())
                    {
                        System.out.println(player.getName() + "'s Hand #" + (count + 1) + " of " + player.getHandArray().size());
                        player.getHandValue(hand);

                        //System.out.println("player.isBlackjack == " + player.isBlackjack());
                        if (player.isBlackjack(hand))
                        {
                            player.displayHand(hand);
                            System.out.println("It's a tie for " + player.getName() + " and Dealer!");
                            player.tieMoney(count);
                            player.resetPot(count);
                        }
                        else
                        {
                            player.displayHand(hand);
                            System.out.println(player.getName() + " lost, very sad day.");
                            roomMoney += player.getPot(count);
                            player.resetPot(count);
                        }

                        count++;

                    }

                }
            }
            else
            {
                for (Player player : players)
                {
                    //int count = 0;

                    //System.out.println("player.isBlackjack == " + player.isBlackjack());
                    int counter = 0;

                    for (Hand hand : player.getHandArray())
                    {
                        player.getHandValue(hand);


                        if (player.isBlackjack(hand) == true)
                        {
                            //if (count == 0)
                            //{
                            //    dealer.endGameHand();
                            //}

                            //blackjackState = true;
                            //System.out.println(player.getName() + " won! Get that fat stack of cash!");
                            player.displayHand(hand);
                            player.blackjackWin(counter);
                            player.resetPot(counter);

                            double rmtemp = (1.5) * player.getPot(counter);

                            roomMoney -= (int)rmtemp;
                            System.out.println(player.getName() + " has Blackjack so won't hit this round");
                        }
                    }
                }




                /**
                 Each player plays his/her hand.
                 */
                if (blackjackState == false)
                {
                    for (Player player : players)
                    {
                        for (Hand hand : player.getHandArray())
                        {
                            player.displayHand(hand);

                            int count = 1;

                            if (hand.get(0).getValue() == hand.get(1).getValue() && count == 1 && player.isBlackjack(hand) == false)
                            {
                                Scanner scanner = new Scanner(System.in);

                                if (count == 1)
                                {
                                    System.out.println("Would you like to split your hand? Type Yes or No");
                                    String response = scanner.nextLine();

                                    while (!response.substring(0,1).equals("Y") && !response.substring(0,1).equals("y")
                                            && !response.substring(0,1).equals("N") && !response.substring(0,1).equals("n"))
                                    {
                                        System.out.println("Please enter Yes or No");
                                        response = scanner.nextLine();
                                    }

                                    if (response.substring(0,1).equals("Y") || response.substring(0,1).equals("y"))
                                    {
                                        player.split(deck, hand);
                                        player.putMoneyInPot(player.getPot(0), 1);
                                        count++;
                                    }


                                }
                            }

                            if (count > 1 && hand.get(0).getValue() == hand.get(1).getValue() && player.isBlackjack(hand) == false)
                            {
                                System.out.println("You can only split once per round");
                            }
                        }

                        boolean canDoubleDown;
                        boolean doublingDown = false;

                        int handIndex = 0;

                        int counting = 0;
                        for (Hand hand : player.getHandArray())
                        {
                            int counter = 1;



                            if (player.isBlackjack(hand) == false || player.isSplit())
                            {
                                if (player.getMoney() >= player.getPot(counting))
                                {
                                    canDoubleDown = true;
                                }
                                else
                                {
                                    canDoubleDown = false;
                                }

                                //System.out.println("Is " + player.getName() + "'s hand a blackjack? " + player.isBlackjack());

                                System.out.println();
                                System.out.print("Dealer's Hand: ");
                                dealer.displayDealerHand();

                                if (player.isSplit())
                                {
                                    System.out.println();
                                    player.displayHand(hand);
                                }

                                if (canDoubleDown && counter == 1)
                                {
                                    doublingDown = player.DoublingDown(counting);
                                    counter++;
                                }
                                else
                                {
                                    System.out.println(player.getName() + " cannot doubledown for (s)he is but a peasant");
                                    counter++;
                                }


                                if (doublingDown == true)
                                {

                                    player.addCard(deck.deal(), hand, handIndex);
                                    player.displayHand(hand);
                                }

                                else
                                {
                                    while (player.getHandValue(hand) <= 21 && player.getUserChoice() == true)
                                    {
                                        player.addCard(deck.deal(), hand, handIndex);
                                        player.displayHand(hand);
                                    }

                                    if (player.getHandValue(hand) > 21)
                                    {
                                        System.out.println("Ooooo, you busted your hand!");
                                    }

                                    System.out.println();
                                }

                                doublingDown = false;
                            }

                            counting++;
                        }
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
                        int counter = 0;


                        for (Hand hand : player.getHandArray())
                        {
                            System.out.println(player.getPot(counter));
                            System.out.println(player.getHandValue(hand));

                            if (player.isBlackjack(hand) == false || player.isSplit() == true)
                            {

                                //Case that player has more points, within accepted range, than dealer
                                if (dealerPoints < player.getHandValue(hand) && player.getHandValue(hand) <= 21)
                                {
                                    player.isWinner();
                                    System.out.println(player.getName() + " won!");
                                    player.regularWin(counter);
                                    roomMoney -= player.getPot(counter);
                                    //player.resetPot();
                                }

                                //Case that both players have same point values and is not blackjack, it is a tie
                                else if (dealerPoints == player.getHandValue(hand) && player.getHandValue(hand) <= 21)
                                {
                                    System.out.println("It's a tie for " + player.getName() + " and Dealer!");
                                    player.tieMoney(counter);
                                    //player.resetPot();
                                }

                                //If dealer has more points, within accepted range, than player
                                else if (dealerPoints > player.getHandValue(hand) && dealerPoints <= 21)
                                {
                                    dealer.isWinner();
                                    roomMoney += player.getPot(counter);
                                    System.out.println(player.getName() + " lost.");
                                    //player.resetPot();
                                }

                                //If dealer busts and player does not
                                else if (player.getHandValue(hand) <= 21 && dealerPoints > 21)
                                {
                                    player.isWinner();

                                    /**
                                     if (player.isBlackjack() == true)
                                     {
                                     player.blackjackWin();
                                     }
                                     else
                                     */
                                    player.regularWin(counter);
                                    roomMoney -= player.getPot(counter);
                                    //player.resetPot();
                                    System.out.println(player.getName() + " won!");
                                }
                                //If player busts and dealer does not
                                else if (dealerPoints <= 21 && player.getHandValue(hand) > 21)
                                {
                                    dealer.isWinner();
                                    roomMoney += player.getPot(counter);
                                    System.out.println(player.getName() + " lost!");
                                    //player.resetPot();
                                }
                                //If everyone busts, nobody wins but player loses money
                                else if (dealerPoints > 21 && player.getHandValue(hand) > 21)
                                {
                                    System.out.println("Both " + player.getName() + " and Dealer lost.");
                                    roomMoney += player.getPot(counter);
                                    //player.resetPot();
                                }
                            }
                            player.resetPot(counter);
                            counter++;
                        }
                    }
                }
            }


            /**
             Clears the dealer's hand and the player's hand
             */
            for (Player player: players)
            {
                player.clearHand();
                player.resetSplit();
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

            //this.defaultReset();

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

            if (players.size() == 0)
            {
                System.out.println("This room has gotten lonely because everyone became too poor to play.");
                System.out.println("Bye-bye losers.");
                playAgain = false;
            }
            else
            {
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

    public void defaultReset()
    {
        if (isDefault)
        {
            roomMoney = 200000;
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

    public int getHandValue()
    {
        return dealerHand.getValue();
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
    private ArrayList<Hand> hand;
    private Hand initialHand;
    private String name;
    private int win;
    private int money;
    private int pot[];
    private int pot1[];
    private boolean playing;
    private boolean isSplit;

    /**
     The constructor creates the hand based off a deck entered into it. (at least for testing)
     */
    public Player(String playerName)
    {
        hand = new ArrayList<Hand>();
        initialHand = new Hand();
        hand.add(initialHand);
        name = playerName;
        win = 0;
        pot = new int[2];
        pot1 = new int[2];
        money = 1000;
        playing = false;
    }

    public Player(String username, int moneyStored)
    {
        hand = new ArrayList<Hand>();
        initialHand = new Hand();
        hand.add(initialHand);
        name = username;
        win = 0;
        pot = new int[2];
        pot1 = new int[2];
        money = moneyStored;
        playing = false;
    }

    /**
     This method allows for the player to add a card to his deck.
     */
    public void addCard(Card card, Hand hand, int index)
    {
        hand.addCard(card);
        this.hand.set(index, hand);
    }


    /**
     displayHand shows the user his/her hand and its value.
     */
    public void displayHand(Hand hand)
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

    public boolean DoublingDown(int i)
    {
        //System.out.println("Is " + player.getName() + "'s hand a blackjack? " + player.isBlackjack());


        Scanner scannering = new Scanner(System.in);
        System.out.println("Would you like to double down? Type Yes or No");
        String response = scannering.nextLine();

        while (!response.substring(0,1).equals("Y") && !response.substring(0,1).equals("y")
                && !response.substring(0,1).equals("N") && !response.substring(0,1).equals("n"))
        {
            System.out.println("Please enter Yes or No");
            response = scannering.nextLine();
        }

        if (response.substring(0,1).equals("Y") || response.substring(0,1).equals("y"))
        {
            money -= pot[i];
            pot[i] *= 2;
            return true;
        }

        return false;
    }

    /**
     getHandValue returns the point value of the hand.
     */
    public int getHandValue(Hand hand)
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

    public int getPot(int i)
    {
        return pot[i];
    }

    /**
     Empties the player's hand
     */
    public void clearHand()
    {
        Hand handtemp = new Hand();
        this.hand.clear();
        hand.add(handtemp);
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
    public boolean isBlackjack(Hand hand)
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

    public void putMoneyInPot(int betAmount, int i)
    {
        pot[i] = betAmount;
        pot[1] = betAmount;
        money -= betAmount;
    }

    /**
     Resets a player's bet to 0 to use at the end of each round
     */

    public void resetPot(int i)
    {
        pot[i] = 0;
    }

    public void resetSplit()
    {
        isSplit = false;
    }

    /**
     Adds 1.5x money for a player who has a blackjack when dealer does not. Logic applied in the game function
     */
    public void blackjackWin(int i)
    {
        double temp = (2.5) * pot[i];
        money += (int)temp;
    }

    /**
     Adds money for a player who has won. Logic of when used applied in the game function
     */

    public void regularWin(int i)
    {
        money += (2 * pot[i]);
    }

    /**
     Returns money to player from pot when it is a tie
     */

    public void tieMoney(int i)
    {
        money += pot[i];
    }

    public void buyGameRoom(int money)
    {
        this.money -= money;
    }

    public void addMoneyToGameRoom(int money)
    {
        this.money -= money;
    }

    public ArrayList<Hand> getHandArray()
    {
        return this.hand;
    }

    public void split(Deck deck, Hand hand)
    {
        Hand newHand = new Hand();
        newHand.addCard(hand.get(1));
        hand.remove(1);

        hand.addCard(deck.deal());
        newHand.addCard(deck.deal());

        ArrayList<Hand> HandList = new ArrayList<Hand>();

        HandList.add(hand);
        HandList.add(newHand);

       /*
       pot[1] = pot[0];

       for (int i = 0; i <= 1; i++)
       {
           pot1[i] = pot[i];
       }
       */

        this.hand = HandList;

        isSplit = true;
    }

    public void setHand(ArrayList<Hand> handsy)
    {
        this.hand = handsy;
    }

    public boolean isSplit()
    {
        return isSplit;
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
     Removes a card from the hand
     */
    public Card remove(int i)
    {
        return cards.remove(i);
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



class Signup
{
    private int money;

    public Signup() throws NoSuchAlgorithmException
    {
        money = 1000;
    }

    public boolean MakeAccount(String user, String pass) throws NoSuchAlgorithmException
    {
        if (user.length() < 1 || user.length() > 10 || pass.length() < 6 || pass.length() > 12)
        {
            return false;
        }

        if (this.Authentication(user))
        {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongoClient.getDatabase("blackjack");
            MongoCollection<Document> collection = database.getCollection("accounts");



            Document username = new Document("username", Authentication(user))
                    .append("password", toHexString(getSHA(pass)))
                    .append("money", money);
            collection.insertOne(username);

            System.out.println("Your account has been successfully created!");
            return true;
        }
        else
        {
            return false;
        }

    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public boolean Authentication(String input)
    {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("blackjack");
        MongoCollection<Document> collection = database.getCollection("accounts");

        Document document = collection
                .find(new BasicDBObject("username", input))
                .projection(Projections.fields(Projections.include("username"), Projections.excludeId())).first();


        if (document != null)
        {
            return false;
        }
        else
        {
            return true;
        }
                /*
                x = document.getString("username");
                System.out.println(x.getClass());

                if (x.equals(input))
                {
                    Scanner newuser = new Scanner(System.in);
                    System.out.println("This username is taken");
                    System.out.println("Please enter another username:");
                    input = newuser.nextLine();
                    x = input;

                    Scanner newpassword = new Scanner(System.in);
                    System.out.println("Enter a new password");
                    */
    }

    public String getStringFromDocument(Document doc)
    {
        try
        {
            DOMSource domSource = new DOMSource((Node) doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

}

class Login
{
    public Login()
    {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("blackjack");
        MongoCollection<Document> collection = database.getCollection("accounts");
    /*
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter your username:");
    String username  = input.nextLine();
    Scanner pw = new Scanner(System.in);
    System.out.println("Please enter your password:");
    String password = pw.nextLine();
    */
    }

    public boolean LoginSuccessOrNah(String username, String password) throws NoSuchAlgorithmException
    {
        if (this.AuthenticateInputs(username, password))
        {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongoClient.getDatabase("blackjack");
            MongoCollection<Document> collection = database.getCollection("accounts");

            System.out.println("Login Successful!");
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean AuthenticateInputs(String user, String pass) throws NoSuchAlgorithmException
    {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("blackjack");
        MongoCollection<Document> collection = database.getCollection("accounts");

        // Document myDoc = (Document) collection.find().projection(fields(include("username", user), excludeId()));
        //String x = getStringFromDocument(myDoc);

        Document document = collection
                .find(new BasicDBObject("username", user))
                .projection(Projections.fields(Projections.include("password"), Projections.excludeId())).first();

        String x = document.getString("password");

        //Document myDoc2 = (Document) collection.find().projection(fields(include("password", pass), excludeId()));
        //String y = getStringFromDocument(myDoc2);
        if (document == null)
        {
            System.out.println("Incorrect!");
            return false;
        }
        else if (x.equals(toHexString(getSHA(pass))))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public String getStringFromDocument(Document doc)
    {
        try
        {
            DOMSource domSource = new DOMSource((Node) doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }



}

class Building
{
    public Building()
    {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("Building");
        MongoCollection<Document> collection = database.getCollection("Rooms");
    }

    public boolean CreateRoom(String owner)
    {
        if (this.UserRichEnough(owner))
        {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongoClient.getDatabase("Building");
            MongoCollection<Document> collection = database.getCollection("Rooms");
            int money = 20000;

            Scanner input = new Scanner(System.in);
            System.out.println("What would you like your room to named?:");
            String RoomName  = input.nextLine();


            Document Room = new Document("owner", owner)
                    .append("Room Name", RoomName)
                    .append("money", money);
            collection.insertOne(Room);

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean UserRichEnough(String username)
    {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("blackjack");
        MongoCollection<Document> collection = database.getCollection("accounts");

        Document document = collection
                .find(new BasicDBObject("username", username))
                .projection(Projections.fields(Projections.include("money"), Projections.excludeId())).first();

        if(document == null)
        {
            return false;
        }
        else if(document != null)
        {
            int money = document.getInteger("money");

            if (money >= 20000)
            {
                money = money - 20000;
                collection.updateOne(eq("username", username), new Document("$set", new Document("money", money)));
                return true;
            }
            else
            {
                return false;
            }
        }
        else
            return false;
    }

}
