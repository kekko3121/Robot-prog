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

public class PlaypageController {

    @FXML
    private Button readyButton;

    @FXML
    private Button returnButton;

    @FXML
    private ChoiceBox<String> level;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private void goReady(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("score.fxml")); // Use the correct FXML file
            Parent root = loader.load();

            // Riceve il controller dello score
            ScoreController scoreController = loader.getController();

            // Crea un oggetto PlayerProperty con i dati inseriti dall'utente
            PlayerProperty playerProperty = new PlayerProperty(name.getText() + " " + surname.getText(), "0");
            scoreController.setDifficulty(level.getValue());

            // Show the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void returntoHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepageswarm.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) returnButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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