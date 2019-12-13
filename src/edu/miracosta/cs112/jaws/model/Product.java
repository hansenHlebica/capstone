package edu.miracosta.cs112.jaws.model;

import java.io.Serializable;

public abstract class Product implements Serializable {

    String mTitle;
    double mPrice;
    boolean mCart;

    public Product(String title, double price, boolean cart)
    {
        mTitle = title;
        mPrice = price;
        mCart = cart;
    }

    public String getmTitle() {
        return mTitle;
    }

    public double getmPrice() { return mPrice; }

    public boolean getmCart() { return mCart; }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public void setmCart(boolean mCart) {
        this.mCart = mCart;
    }

    // TODO: Override in children, format price, and make prettier
    @Override
    public String toString() {
        return "Product{ " +
                "Title='" + mTitle + '\'' +
                ", Price=" + mPrice +
                '}';
    }
}
