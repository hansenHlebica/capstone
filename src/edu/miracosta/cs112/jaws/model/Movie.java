package edu.miracosta.cs112.jaws.model;

import java.io.Serializable;

public class Movie extends Product implements Serializable {

    public Movie(String title, double price, boolean cart) {
        super(title, price, cart);
    }
}
