package com.maze;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * Controller della pagina di gioco che permette all'utente di inserire il proprio nome e cognome
 * e di selezionare il livello di difficoltà
 */
public class PlaypageController {

    @FXML
    private Button readyButton; // Bottone per iniziare il gioco

    @FXML
    private Button returnButton; // Bottone per tornare alla home

    @FXML
    private ChoiceBox<String> level; // Scelta del livello di difficoltà

    @FXML
    private TextField name; // Campo di testo per inserire il nome

    @FXML
    private TextField surname; // Campo di testo per inserire il cognome

    /**
     * Metodo che inizializza la pagina di gioco per mostrare la classifica
     * mentre carica il labirinto per iniziare il gioco
     * @param event Evento che scaturisce dalla pressione del pulsante "Ready"
     */
    @FXML
    private void goReady(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("score.fxml"));
            Parent root = loader.load();

            // Riceve il controller dello score
            ScoreController scoreController = loader.getController();

            // Imposta la difficoltà e i dati del giocatore
            scoreController.setDifficulty(level.getValue());
            scoreController.setPlayerProperty(new PlayerProperty(name.getText(), surname.getText()));
            scoreController.custominitialize();

            // Carica la schermata di classifica
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che permette di tornare alla home
     * @param event Evento che scaturisce dalla pressione del pulsante "Return"
     */
    @FXML
    private void returntoHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) returnButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per controllare se i campi di testo sono compilati
     * e abilitare il pulsante "Ready" in caso affermativo
     */
    @FXML
    private void controllerText1() {
        // Implementa la logica per il campo di testo
        // Puoi aggiungere qui la logica per abilitare il pulsante "Ready" quando i campi di testo sono compilati
        if (!name.getText().isEmpty() && !surname.getText().isEmpty()) {
            readyButton.setDisable(false);
        } else {
            readyButton.setDisable(true);
        }
    }
}