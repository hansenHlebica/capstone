package edu.miracosta.cs112.jaws.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator {

    private static Stage mainStage;

    /**
     * Sets the main stage when the application first loads
     * @param stage The main stage
     */
    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    /**
     * Navigates from one scene to another
     * @param title The title to display on the stage
     * @param scene The new scene to load
     */
    public static void loadScene(String title, Scene scene) {
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
