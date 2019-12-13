package edu.miracosta.cs112.jaws.controller;

import edu.miracosta.cs112.jaws.model.Model;
import edu.miracosta.cs112.jaws.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Controller {

    private static Controller theInstance;
    private ObservableList<Product> mAllProductsList;

    private Controller() {}

    public static Controller getInstance() throws IOException {
        if (theInstance == null) {
            theInstance = new Controller();

            if (Model.binaryFileHasData())
                theInstance.mAllProductsList = Model.populateListFromBinaryFile();
            else
                theInstance.mAllProductsList = Model.populateList();

        }
        return theInstance;
    }

    public ObservableList<Product> getAllProducts() {return mAllProductsList;}

    public void saveData() {
        Model.writeDataToBinaryFile(mAllProductsList);
    }

}
