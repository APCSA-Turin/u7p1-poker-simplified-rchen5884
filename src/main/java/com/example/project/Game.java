package com.example.project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Game {
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {   
        int p1Score = Utility.getHandRanking(p1Hand);
        int p2Score = Utility.getHandRanking(p2Hand);

        //compares player 1 and 2 best hand 
        if (p1Score > p2Score) {return "Player 1 wins!";} 
        if (p1Score < p2Score) {return "Player 2 wins!";} 

        ArrayList<Card> p1Cards = p1.getHand();
        ArrayList<Card> p2Cards = p2.getHand();

        Card p1HighestCard = p1Cards.get(p1Cards.size() - 1);
        Card p2HighestCard = p2Cards.get(p2Cards.size() - 1);

        int p1Highest = Utility.getRankValue(p1HighestCard.getRank());
        int p2Highest = Utility.getRankValue(p2HighestCard.getRank());
        // if the best hand is equal then we compare the highest card in player hand
        if (p1Highest > p2Highest) {
            return "Player 1 wins!";
        }
        else if (p1Highest < p2Highest) {
            return "Player 2 wins!";
        }
        // if both are equal then return tie
        return "Tie!";
    }

    public static void play() { 
        Scanner scanner = new Scanner(System.in);
        boolean play = true;
        int player1Win = 0;
        int player2Win = 0;
        int tie = 0;

        while (play) {
            Deck deck = new Deck(); 
            Player player1 = new Player(); 
            Player player2 = new Player();
            ArrayList<Card> communityCards = new ArrayList<>();
            communityCards.clear();

            System.out.println("Poker but without the river, turn, and gambling!");
            System.out.println("Shuffling the deck...");
            System.out.println("");

            // Delay for dramatic effects
            try {Thread.sleep(2500);} 
            catch (InterruptedException e) {e.printStackTrace();} // Catches and prints details if the sleep is interrupted. (Literally will never happen)
           
            for (int i = 0; i < 2; i++) {  // draws two cards for each player
                player1.addCard(deck.drawCard());
                player2.addCard(deck.drawCard());
            }
            for (int i = 0; i < 5; i++) {
                communityCards.add(deck.drawCard());
            }

            
            System.out.println("Player 1's Hand: " + player1.getHand());
            System.out.println("Player 2's Hand: " + player2.getHand());
            System.out.println("Community Cards: " + communityCards);

            try {Thread.sleep(2000);}
            catch (InterruptedException e) {e.printStackTrace();}
           
            String p1Hand = player1.playHand(communityCards);
            String p2Hand = player2.playHand(communityCards);

            System.out.println("");
            System.out.println("Player 1's Hand: " + p1Hand + ", " + player1.getHand().get(1));
            System.out.println("Player 2's Hand: " + p2Hand + ", " + player2.getHand().get(1));
            System.out.println("");

            try {Thread.sleep(1000);} 
            catch (InterruptedException e) {e.printStackTrace();}
           
            String result = determineWinner(player1, player2, p1Hand, p2Hand, communityCards);
            System.out.println(result);
            System.out.println("");
            
            if (result.equals("Player 1 wins!")) {player1Win++;}
            else if (result.equals("Player 2 wins!")) {player2Win++;}
            else {tie++;}

            try {Thread.sleep(1500);} 
            catch (InterruptedException e) {e.printStackTrace();}

            System.out.print("Play again? (yes/no): ");
            String input = scanner.nextLine().toLowerCase();

            if (!input.equals("yes")) {
                play = false;
                System.out.println("Player 1 Wins: " + player1Win);
                System.out.println("Player 2 Wins: " + player2Win);
                System.out.println("Tie Count: " + tie);
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game.play();
    }
}