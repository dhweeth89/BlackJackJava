# BlackJackJava
For EC327 Project

Because everybody loves gambling.


Hello WORLD! And welcome to a wonderful game of 21, or Blackjack!
This modern take on a classic game explores themes of strategy, comedy, and Java through the power of MongoDB, JavaFX, IntelliJ, and six young engineers with a penchant for healthy competition!


To run this game, it is requried to have an up-to-date version of Java (preferably JDK 13). MongoDB driver and compass are also required to play. These can be downloaded through their respective links:

      driver: https://www.mongodb.com/download-center/community
      
      compass: https://www.mongodb.com/download-center/compass
      
      installation guide (driver): https://github.com/dhweeth89/BlackJackJava/blob/master/Mon-god-help-me%20copy.pdf
      
      installation guide (compass): https://docs.mongodb.com/compass/master/install/


JavaFX is also used as the gameʻs GUI!

We used JavaFX 13. After JDK 10 JavaFX became a seperate SDK that one must install seperatly to get started with JavaFX. 
The one must link the library for JavaFX and its runtime to the project in order for the GUI to run. Sadely we did not have enough time to implement the GUI with the backend code so for now its just a pretty show of what could have been.


A couple notes for users, since there's a couple features that haven't been ironed out:

Pls don't try to play a game in Default or an owned room until you create an account; with more time, we would have built quick error checks to make sure an account exists before you can play in one of those rooms; otherwise, you'll be stuck in an infinite loop of logging in.

Pls don't name rooms the same because we didn't yet implement a feature to ensure no two rooms by the same person are the same. Also, with more time, we would add an extra phase in room selection where you'd have to type in both the name of the owner and the name of the room (so that multiple people can have the same room name). For the case of 'Default' or 'Guest' you would put in the owner's name as 'game' then, which works because all "actual" user usernames are at least 5 characters so nobody can also call themselves 'game'.

And, yeah, those are our two notes for users until we fix them but we don't want to push anymore for now (except for this note).
