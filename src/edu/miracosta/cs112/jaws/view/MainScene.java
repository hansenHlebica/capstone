package edu.miracosta.cs112.jaws.view;

import edu.miracosta.cs112.jaws.model.Book;
import edu.miracosta.cs112.jaws.model.Card;
import edu.miracosta.cs112.jaws.model.Product;
import edu.miracosta.cs112.jaws.model.Model;
import edu.miracosta.cs112.jaws.controller.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainScene extends Scene {
    Button mBooksButton = new Button("Books");
    Button mCardButton = new Button("Cards");
    Button mGamesButton = new Button("Games");
    Button mMoviesButton = new Button("Movies");
    Button mToysButton = new Button("Toys");
    Button mMusicButton = new Button("Music");
    Button mCartButton = new Button("Cart");

    private ComboBox<String> locationsCB;
    private ComboBox<String> neighborhoodsCB;
    private Slider minRatingSlider = new Slider(0.0, 5.0, 0.0);
    private Slider maxPriceSlider = new Slider(0.0, 15.0, 15.0);
    private ListView<Product> productsLV = new ListView<>();

    private Controller controller = Controller.getInstance();
    private ObservableList<Product> productsList;
    private Product selectedProduct;

    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the SD products application.
     * It contains filters for location (store), neighborhood, rating and price.
     * The <code>MainScene</code> also allows for a user to add a new burrito review or modify
     * existing reviews.
     */
    public MainScene() throws IOException {
        super(new GridPane(), 850, 400);

        productsList = controller.getAllProducts();
        productsLV.setItems(productsList);
        productsLV.setPrefWidth(850);

        GridPane pane = new GridPane();
        pane.setHgap(30);
        pane.setVgap(20);

        //style
        mBooksButton.setStyle("-fx-font-size: 4em; " );
        mBooksButton.setStyle("-fx-text-fill: #0000ff");
        mGamesButton.setStyle("-fx-font-size: 4em; " );
        mGamesButton.setStyle("-fx-text-fill: #0000ff");
        mToysButton.setStyle("-fx-font-size: 4em; " );
        mToysButton.setStyle("-fx-text-fill: #0000ff");
        mMoviesButton.setStyle("-fx-font-size: 4em; " );
        mMoviesButton.setStyle("-fx-text-fill: #0000ff");
        mCardButton.setStyle("-fx-font-size: 4em; " );
        mCardButton.setStyle("-fx-text-fill: #0000ff");
        mMoviesButton.setStyle("-fx-font-size: 4em; " );
        mMoviesButton.setStyle("-fx-text-fill: #0000ff");
        mMusicButton.setStyle("-fx-font-size: 4em; " );
        mMusicButton.setStyle("-fx-text-fill: #0000ff");
        mCartButton.setStyle("-fx-font-size: 4em; " );
        mCartButton.setStyle("-fx-text-fill: #0000ff");
        pane.setAlignment(Pos.CENTER);

        //buttons
        pane.add(mBooksButton, 0, 0);
        pane.add(mGamesButton, 1, 0);

        pane.add(mToysButton, 0, 1);
        pane.add(mCardButton, 1, 1);

        pane.add(mMoviesButton, 0, 2);
        pane.add(mMusicButton, 1, 2);

        pane.add(mCartButton,0,3);

        //direct to new view for each button
        mToysButton.setOnAction((event -> {
            try {
                toyScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        mCardButton.setOnAction((event -> {
            try {
                cardScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        mMoviesButton.setOnAction((event -> {
            try {
                movieScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        mMusicButton.setOnAction((event -> {
            try {
                musicScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        mGamesButton.setOnAction((event -> {
            try {
                gameScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        mBooksButton.setOnAction((event -> {
            try {
                bookScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        mCartButton.setOnAction((event -> {
            try {
                cartScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        this.setRoot(pane);
    }

    /**
     * refreshes list view
     */
    private void updateDisplay() { productsLV.refresh(); }

    /**
     * methods to load product scenes and refresh list view
     * @throws IOException
     */
    private void bookScene() throws IOException {
        ViewNavigator.loadScene("Books", new ProductScene(this, "Books"));
        updateDisplay();
    }
    private void gameScene() throws IOException {
        ViewNavigator.loadScene("Games", new ProductScene(this, "Games"));
        updateDisplay();
    }
    private void toyScene() throws IOException {
        ViewNavigator.loadScene("Toys", new ProductScene(this, "Toys"));
        updateDisplay();
    }
    private void musicScene() throws IOException {
        ViewNavigator.loadScene("Music", new ProductScene(this, "Music"));
        updateDisplay();
    }
    private void movieScene() throws IOException {
        ViewNavigator.loadScene("Movies", new ProductScene(this, "Movies"));
        updateDisplay();
    }
    private void cardScene() throws IOException {
        ViewNavigator.loadScene("Cards", new ProductScene(this, "Cards"));
        updateDisplay();
    }
    private void cartScene() throws IOException {
        ViewNavigator.loadScene("Shopping Cart", new CartScene(this));
        updateDisplay();
    }

}
