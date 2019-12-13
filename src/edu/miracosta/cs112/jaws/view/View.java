package edu.miracosta.cs112.jaws.view;

import edu.miracosta.cs112.jaws.controller.Controller;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View extends Application {

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Barnes and Noble", new MainScene());
    }

    @Override
    public void stop() throws Exception
    {
        Controller.getInstance().saveData();
    }

}

