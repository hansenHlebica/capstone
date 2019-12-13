package edu.miracosta.cs112.jaws.model;

import java.io.Serializable;

public class Book extends Product implements Serializable {

    public Book(String title, double price, boolean cart) {
        super(title, price, cart);
    }

}
