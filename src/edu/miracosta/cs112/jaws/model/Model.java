package edu.miracosta.cs112.jaws.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Model {

    public static final String BINARY_FILE = "nobleJaws.dat";

    /**
     * a wrapper for scrape method
     * Note: for better naming (I suppose -- basically unnecessary)
     * @return
     * @throws IOException
     */
    public static ObservableList<Product> populateList() throws IOException {

        ObservableList<Product> allProducts = FXCollections.observableArrayList();

        scrape(allProducts);

        return allProducts;
    }

    /**
     * calls fetch method for each url then instantiates product objects using scraped data
     * Note: could use refactoring
     * @param allProducts
     * @throws IOException
     */
    public static void scrape(ObservableList<Product> allProducts) throws IOException {

        String rootUri = "http://barnesandnoble.com/";
        String booksUri = "b/this-seasons-biggest-books/_/N-2ro4;jsessionid=65EA21A56FB19F45E082EFE5ED1296F2.prodny_store02-atgap07";
        String cardsUri = "b/30-off-holiday-boxed-cards/_/N-2thj;jsessionid=89389870B5FC7A07648BB9351588E517.prodny_store01-atgap01";
        String toysUri = "b/top-toys-of-this-season-ages-8/_/N-2obc;jsessionid=2DA056A8B6487DA615C402F0F018E13E.prodny_store01-atgap07";
        String gamesUri = "b/new-games/family-classic-games/_/N-2nogZ1hn9";
        String moviesUri = "b/movies-tv/_/N-1rZ8qh";
        String musicUri = "b/the-years-best-music/_/N-2tgy";

        /**
         * fetch books grid then add book data to pair list
         */
        List<Pair<String,Double>> bookData =
                fetchProducts(rootUri + booksUri);

        /**
         * fetch cards grid then add card data to pair list
         */
        List<Pair<String,Double>> cardData =
                fetchProducts(rootUri + cardsUri);

        /**
         * fetch toys grid then add toy data to pair list
         */
        List<Pair<String,Double>> toysData =
                fetchProducts(rootUri + toysUri);

        /**
         * fetch games grid then add game data to pair list
         */
        List<Pair<String,Double>> gamesData =
                fetchProducts(rootUri + gamesUri);

        /**
         * fetch movies grid then add movie data to pair list
         */
        List<Pair<String,Double>> moviesData =
                fetchProducts(rootUri + moviesUri);

        /**
         * fetch music grid then add music data to pair list
         */
        List<Pair<String,Double>> musicData =
                fetchProducts(rootUri + musicUri);

        /**
         * populate observable list with new products using pair lists
         */

        for (Pair b : bookData)
            allProducts.add(new Book((String)b.getL(), (Double)b.getR(), false));

        for (Pair c : cardData)
            allProducts.add(new Card((String)c.getL(), (Double)c.getR(), false));

        for (Pair t : toysData)
            allProducts.add(new Toy((String)t.getL(), (Double)t.getR(), false));

        for (Pair g : gamesData)
            allProducts.add(new Game((String)g.getL(), (Double)g.getR(), false));

        for (Pair mo : moviesData)
            allProducts.add(new Movie((String)mo.getL(), (Double)mo.getR(), false));

        for (Pair mu : musicData)
            allProducts.add(new Music((String)mu.getL(), (Double)mu.getR(), false));
    }

    /**
     * fetches HTML over HTTP for given url and returns a list of product data pairs
     * @param url
     * @return
     * @throws IOException
     */
    public static List<Pair<String,Double>> fetchProducts(String url) throws IOException
    {
        List<Pair<String,Double>> data = new ArrayList<Pair<String,Double>>();
        String title;
        String rawPrice;
        double price;

        Document page = Jsoup.connect(url).get();
        Element body = page.select("body").first();
        Elements grid = body.select("section[id=\"gridView\"]");
        Elements infos = grid.select("div[class=\"product-shelf-info product-info-view text--left\"]");

        for (Element info : infos)
        {
            title = info.select("a").first().attr("title");
            rawPrice = info.select("div.product-shelf-pricing").select("a.link > span:eq(1)").text();
            price = Double.parseDouble(rawPrice.substring(1));

            data.add(new Pair<>(title, price));
        }

        return data;
    }

    public static boolean writeDataToBinaryFile(ObservableList<Product> allProductsList)
    {
        File binaryFile = new File(BINARY_FILE);

        try {

            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));

            Product[] temp = new Product[allProductsList.size()];

            for (int i = 0; i < temp.length; i++)
            {
                temp[i] = allProductsList.get(i);
            }

            fileWriter.writeObject(temp);
            fileWriter.close();

            return true;

        } catch (IOException e)
        {
            return false;
        }
    }

    public static boolean binaryFileHasData()
    {
        File binaryFile = new File(BINARY_FILE);

        return (binaryFile.exists() && binaryFile.length() > 0);
    }

    public static ObservableList<Product> populateListFromBinaryFile()
    {
        ObservableList<Product> allProducts = FXCollections.observableArrayList();

        File binaryFile = new File(BINARY_FILE);

        if (binaryFileHasData())
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                Product[] temp = (Product[]) fileReader.readObject();
                allProducts.addAll(temp);
                System.out.println("Products");
                System.out.println(allProducts);
                fileReader.close();

            } catch (Exception e) {
                System.err.println("Error reading from binary file");
            }
        }

        return allProducts;
    }

}
