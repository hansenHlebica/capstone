package edu.miracosta.cs112.jaws.model;

import java.io.Serializable;

public class Music extends Product implements Serializable {

    public Music(String title, double price, boolean cart) {
        super(title, price, cart);
    }
}
