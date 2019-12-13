package edu.miracosta.cs112.jaws.model;

import edu.miracosta.cs112.jaws.model.Product;

import java.io.Serializable;

public class Toy extends Product implements Serializable {

    public Toy(String title, double price, boolean cart) {
        super(title, price, cart);
    }
}
