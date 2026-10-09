package main.java.com.johnlab.inventarysystem;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.johnlab.inventarysystem.utils.SceneManager;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showLoginView();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
