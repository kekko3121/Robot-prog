package com.maze;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class HomePageController {

    @FXML
    private Button helpButton; // Bottone per mostrare la pagina di aiuto

    @FXML
    private Button quitButton; // Bottone per chiudere l'applicazione

    @FXML
    private Button playButton; // Bottone per iniziare a giocare

    /**
     * Metodo per mostrare la pagina di aiuto
     * @param event
     */
    @FXML
    private void showGithub(ActionEvent event) {
        // Implementa la logica per mostrare la pagina GitHub
        try {
            // url del repository GitHub
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/kekko3121/Robot-prog"));
        } catch (IOException | java.net.URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per chiudere l'applicazione
     * @param event
     */
    @FXML
    void quit(ActionEvent event) {
        Node source = (Node) event.getSource();// Ottiene il nodo sorgente dell'evento
        Stage stage = (Stage) source.getScene().getWindow();// Ottiene lo stage (finestra) corrispondente al nodo sorgente
        stage.close();// Chiude la finestra corrente
    }

    /**
     * Metodo per mostrare la pagina di gioco (playpage) per l'inserimento del nome del giocatore e la scelta del livello
     */
    @FXML
    private void showPlaypage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playpage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) playButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
