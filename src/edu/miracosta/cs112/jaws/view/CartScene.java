package edu.miracosta.cs112.jaws.view;

import edu.miracosta.cs112.jaws.controller.Controller;
import edu.miracosta.cs112.jaws.model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CartScene extends Scene {

    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();
    private ObservableList<Product> mAllProductsList;
    private ObservableList<Product> mCartList = FXCollections.observableArrayList();
    private ListView<Product> cartLV = new ListView<>();

    private Button removeFromCartButton = new Button("Remove From Cart");
    private Button homeButton = new Button("Home");

    private Product selectedProduct;

    // TODO: add label which shows total price
    // TODO: add 'buy' button which makes visible label "this is a scraper, not a store!"

    public CartScene(Scene prevScene) throws IOException {

        super(new GridPane(), 850, 400);

        mAllProductsList = controller.getAllProducts();
        mPrevScene = prevScene;

        for (Product p : mAllProductsList)
        {
            if (p.getmCart())
                mCartList.add(p);
        }
        cartLV.setItems(mCartList);

        /**
         * listen for selected item to enable 'remove from cart' button and set selectedProduct value
         */
        cartLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
                removeFromCartButton.setDisable(newValue == null);
                if (newValue != null)
                    selectedProduct = newValue;
            }
        });

        cartLV.setPrefWidth(850);
        cartLV.setPrefHeight(200);

        GridPane pane = new GridPane();
        pane.setHgap(30);
        pane.setVgap(20);

        pane.add(cartLV,0,0,2,1);
        pane.add(homeButton, 0,1);
        pane.add(removeFromCartButton, 1,1);

        homeButton.setOnAction(event -> goBackToPrevScene());
        removeFromCartButton.setDisable(true);
        removeFromCartButton.setOnAction(event -> removeFromCart());

        this.setRoot(pane);

    }

    private void removeFromCart()
    {
        if (selectedProduct == null)
            return;

        mCartList.remove(selectedProduct);
        selectedProduct.setmCart(false);

        updateDisplay();
    }

    private void updateDisplay()
    {
        cartLV.refresh();
    }

    private void goBackToPrevScene(){
        ViewNavigator.loadScene("Barnes and Noble", mPrevScene);
    }

}
