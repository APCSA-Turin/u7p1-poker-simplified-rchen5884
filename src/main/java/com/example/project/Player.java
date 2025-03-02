package com.example.project;
import java.util.ArrayList;
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

    public String playHand(ArrayList<Card> communityCards){      
        for (Card c : communityCards) {
            allCards.add(c);
        }

        for (Card c : hand) {
            allCards.add(c);
        }



        return "Nothing";
    }

    public void sortAllCards() {
        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(i).getRank()) > Utility.getRankValue(allCards.get(j).getRank())) {
                    Card temp = allCards.get(i);
                    allCards.set(i, allCards.get(j));
                    allCards.set(j, temp);
                }
            }
        }
    }
    
    

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++) {
            String rank = ranks[i];
            frequency.add(0);
            for (Card card : allCards) {
                if (card.getRank().equals(rank)) {
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
