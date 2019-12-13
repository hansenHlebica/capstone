package edu.miracosta.cs112.jaws.model;

import java.io.Serializable;

public class Card extends Product implements Serializable {

    public Card(String title, double price, boolean cart) {
        super(title, price, cart);
    }

}
