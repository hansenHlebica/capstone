package edu.miracosta.cs112.jaws.model;

import java.io.Serializable;

public class Game extends Product implements Serializable {

    public Game(String title, double price, boolean cart) {
        super(title, price, cart);
    }
}
