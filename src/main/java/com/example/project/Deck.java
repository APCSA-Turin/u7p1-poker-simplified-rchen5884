package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void initializeDeck() { // initializes the deck
        for (String suit : Utility.getSuits()) {
            for (String rank : Utility.getRanks()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffleDeck() { // uses the shuffle method in collections
        Collections.shuffle(cards); 
    }

    public Card drawCard() {
        // checks if isEmpty is true. If it is, returns null. Otherwise, removes and returns the first card from the list.
        // ? is a shorthand way of a if statement
        return (isEmpty()) ? null : cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}