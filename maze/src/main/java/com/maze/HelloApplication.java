package com.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe per l'avvio dell'applicazione
 */
public class HelloApplication extends Application {

    /**
     * Metodo per l'avvio dell'applicazione
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Caricamento della schermata iniziale
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homepageswarm.fxml"));
        primaryStage.setResizable(false); // Impostazione della finestra non ridimensionabile
        primaryStage.setTitle("MICROROBOT MAZE EXPLORER : DIJKSTRA EDITION"); // Impostazione del titolo della finestra
        primaryStage.setScene(new Scene(fxmlLoader.load())); // Impostazione della scena
        primaryStage.show(); // Visualizzazione della finestra
    }

    /**
    * main per l'avvio dell'applicazione
    */
    public static void main(String[] args) {
        launch(args);
    }
}
