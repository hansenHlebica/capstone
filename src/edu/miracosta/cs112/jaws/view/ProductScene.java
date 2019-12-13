package edu.miracosta.cs112.jaws.view;

import edu.miracosta.cs112.jaws.controller.Controller;
import edu.miracosta.cs112.jaws.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProductScene extends Scene {

    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();
    private ObservableList<Product> mAllProductsList;
    private ObservableList<Product> mProductList = FXCollections.observableArrayList();
    private ListView<Product> productLV = new ListView<>();

    private Button homeButton = new Button("Home");
    private Button addToCartButton = new Button("Add to Cart");

    private Product selectedProduct;

    // TODO: include Add and Remove buttons that instantiate new product objects / remove them

    public ProductScene(Scene prevScene, String productType) throws IOException {

        super(new GridPane(), 850, 400);

        mAllProductsList = controller.getAllProducts();
        mPrevScene = prevScene;

        // filter product list for desired type
        filterProducts(productType);

        /**
         * listen for selected item to enable 'add to cart' button and set selectedProduct value
         */
        productLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
                addToCartButton.setDisable(newValue == null);
                if (newValue != null)
                    selectedProduct = newValue;
            }
        });

        productLV.setPrefWidth(850);
        productLV.setPrefHeight(200);

        GridPane pane = new GridPane();
        pane.setHgap(30);
        pane.setVgap(20);

        pane.add(productLV,0,0,2,1);
        pane.add(homeButton, 0,1);
        pane.add(addToCartButton, 1,1);

        homeButton.setOnAction(event -> goBackToPrevScene());
        addToCartButton.setDisable(true);
        addToCartButton.setOnAction(event -> addToCart());

        this.setRoot(pane);

    }

    /**
     * filter product list based on type
     * NOTE: there's probably a less verbose way of doing this vis-a-vis OOP and JavaFX
     * @param productType
     */
    private void filterProducts(String productType)
    {
        switch (productType)
        {
            case "Books" :

                for (Product p : mAllProductsList)
                {
                    if (p instanceof Book)
                        mProductList.add(p);
                }
                productLV.setItems(mProductList);
                break;

            case "Cards" :

                for (Product p : mAllProductsList)
                {
                    if (p instanceof Card)
                        mProductList.add(p);
                }
                productLV.setItems(mProductList);
                break;

            case "Toys" :

                for (Product p : mAllProductsList)
                {
                    if (p instanceof Toy)
                        mProductList.add(p);
                }
                productLV.setItems(mProductList);
                break;

            case "Movies" :

                for (Product p : mAllProductsList)
                {
                    if (p instanceof Movie)
                        mProductList.add(p);
                }
                productLV.setItems(mProductList);
                break;

            case "Music" :

                for (Product p : mAllProductsList)
                {
                    if (p instanceof Music)
                        mProductList.add(p);
                }
                productLV.setItems(mProductList);
                break;

            case "Games" :

                for (Product p : mAllProductsList)
                {
                    if (p instanceof Game)
                        mProductList.add(p);
                }
                productLV.setItems(mProductList);
                break;
        }
    }

    // TODO: display "Item added to cart" text
    private void addToCart()
    {
        if (selectedProduct == null)
            return;

        selectedProduct.setmCart(true);

    }

    private void goBackToPrevScene(){
        ViewNavigator.loadScene("Barnes and Noble", mPrevScene);
    }

}
