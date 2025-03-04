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

    public String playHand(ArrayList<Card> communityCards) {
        // clears allCards of any extra cards and adds both hand and communityCards to it
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        ArrayList<Integer> suits = findSuitFrequency();
        ArrayList<Integer> ranks = findRankingFrequency();
        boolean flush = suits.contains(5) || suits.contains(6) || suits.contains(7);
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
                        royal = true;
                        break;

                    // checks if the final card in a straight is a ace 
                    }
                }
            }
            else {
                consecutiveNum = 0;
                //resets counter
            }
        }

        if (royal && flush) {return "Royal Flush";}
        if (flush && straight) {return "Straight Flush";}
        if (ranks.contains(4)) {return "Four of a Kind";}
        if (ranks.contains(3) && ranks.contains(2)) {return "Full House";}
        if (ranks.contains(3)) {return "Three of a Kind";}
        if (flush) {return "Flush";}
        if (straight) {return "Straight";}
        if (ranks.contains(3)) {return "Three of a Kind";}
        if (Collections.frequency(ranks, 2) >= 2) {return "Two Pair";}
        if (ranks.contains(2)) {return "A Pair";}
        if (communityCards.contains(allCards.get(allCards.size() - 1))) {return "Nothing";}
        return "High Card";
    }


    public void sortAllCards() {
        // Sorts allCards list first by rank, then by suit if ranks are equal
        Collections.sort(allCards, new Comparator<Card>() {
            public int compare(Card one, Card two) {
                List<String> transition = Arrays.asList(ranks); // Convert ranks array into a list for indexing
                // Compare cards based on their rank positions in the ranks list
                int result = Integer.compare(transition.indexOf(one.getRank()), transition.indexOf(two.getRank()));
                // If ranks are equal, compare by suit positions in the ranks list
                if (result == 0) {
                    return Integer.compare(transition.indexOf(one.getSuit()), transition.indexOf(two.getSuit()));
                }
                return result; // Return the final comparison result
            }
        });
        // Ensures hand list (first two cards) is sorted by rank
        if (hand.get(0).getRank().compareTo(hand.get(1).getRank()) > 0) {
            Card swap = hand.set(0, hand.get(1)); // Swap the first two cards if they are out of order
            hand.set(1, swap);
        }
    }    
    

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++) {
            String rank = ranks[i];
            frequency.add(0);
            for (Card card : allCards) {
                if (card.getRank() == rank) {
                    frequency.set(i, frequency.get(i) + 1);
                }
            }
        }
        return frequency;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            String suit = suits[i];
            frequency.add(0);
            for (Card card : allCards) {
                if (card.getSuit().equals(suit)) {
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
