package com.example.project;
import java.util.*;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}


    public void addCard(Card c){
        hand.add(c);
    }

    // self created "contains" method but for Integers
    public boolean has(int number, ArrayList<Integer> intList) {
        for (int i = 0; i < intList.size(); i++) {
            if (intList.get(i) == number) {
                return true;
            }
        }
        return false;
    }

    // self created "contains" method but for Cards
    public boolean hasCard(Card card, ArrayList<Card> CardList) {
        for (int i = 0; i < CardList.size(); i++) {
            if (CardList.get(i) == card) {
                return true;
            }
        }
        return false;
    }

    // self created "sort" method
    

    public String playHand(ArrayList<Card> communityCards) {
        // clears allCards of any extra cards and adds both hand and communityCards to it
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        ArrayList<Integer> suits = findSuitFrequency();
        ArrayList<Integer> ranks = findRankingFrequency();
        boolean flush = has(5, suits) || has(6, suits) || has(7, suits);
        boolean straight = false;
        boolean royal = false;
        int consecutiveNum = 0;
        // sorts allCards
        sortAllCards();

        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) > 0) {
                consecutiveNum++;                           
                // checks for a straight
                if (consecutiveNum >= 5){
                    straight = true;
                    if (i == 12){
                        // checks if the final card in a straight is a ace 
                        royal = true;
                        break;
                    }
                }
            }
            else {
                // resets counter
                consecutiveNum = 0;
            }
        }

        // Checks what the highest hand the player has. Goes from highest possible hand to lowest possible hand
        if (royal && flush) {return "Royal Flush";}
        if (flush && straight) {return "Straight Flush";}
        if (has(4, ranks)) {return "Four of a Kind";}
        if (has(3, ranks) && has(2, ranks)) {return "Full House";}
        if (flush) {return "Flush";}
        if (straight) {return "Straight";}
        if (has(3, ranks)) {return "Three of a Kind";}
        if (Collections.frequency(ranks, 2) >= 2) {return "Two Pair";}
        if (has(2, ranks)) {return "A Pair";}
        if (hasCard(allCards.get(allCards.size() - 1), communityCards)) {return "Nothing";}
        return "High Card";
    }


    public void sortAllCards() {
        // selection sort
        for (int i = 0; i < allCards.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < allCards.size(); j++) {
                // compares the rank values of the cards and find the minimum 
                if (Utility.getRankValue(allCards.get(min).getRank()) > Utility.getRankValue(allCards.get(j).getRank())) {
                    min = j; 
                }
            } 
            // sorts allCards by rank and suit
            Card temp = allCards.get(i);
            allCards.set(i, allCards.get(min));
            allCards.set(min, temp);
        }
        // sorts hand by comparing rank
        if (hand.get(0).getRank().compareTo(hand.get(1).getRank()) > 0) {
            Card swap = hand.set(0, hand.get(1));
            hand.set(1, swap);
        }
    }
    
    // Iterates through the entire ranks array to check how many times a rank appears.
    // Useful for Pairs, Sets, Quads, and Straights
    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++) {
            String rank = ranks[i];
            frequency.add(0);
            for (Card card : allCards) {
                if (card.getRank() == rank) { // checks if the card's rank is equal to the rank in ranks
                    frequency.set(i, frequency.get(i) + 1);
                }
            }
        }
        return frequency;
    }

    // Iterates through the entire suits array to check how many times a rank appears.
    // Useful for Flushes, Straight Flushes, and Royal Flushes
    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            String suit = suits[i];
            frequency.add(0);
            for (Card card : allCards) {
                if (card.getSuit().equals(suit)) { // checks if the card's suit is equal to the suit in suits
                    frequency.set(i, frequency.get(i) + 1);
                }
            }
        }
        return frequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
