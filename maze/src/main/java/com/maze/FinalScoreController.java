package com.maze;

import java.io.IOException;
import java.util.ArrayList;

import com.maze.Proxy.Classification;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Classe per la gestione della schermata di fine partita
 */
public class FinalScoreController {
    @FXML
    private Label first; // etichetta per il primo posto

    @FXML
    private Label second; // etichetta per il secondo posto

    @FXML
    private Label third; // etichetta per il terzo posto

    @FXML
    private Label fourth; // etichetta per il quarto posto

    @FXML
    private Label fifth; // etichetta per il quinto posto

    /**
     * Metodo di inizializzazione della classe.
     * Legge la classifica dal file e aggiorna i campi di testo con i valori letti.
     */
    @FXML
    public void viewScore(String difficulty) {
        try {
            // Leggi la classifica dal file
            ArrayList<String[]> scores = new Classification(difficulty + ".dat").read();

            // Aggiorna i campi di testo con i valori letti
            if (scores.size() > 0) {
                first.setText(scores.get(0)[0] + " " + scores.get(0)[1]);
            }
            if (scores.size() > 1) {
                second.setText(scores.get(1)[0] + " " + scores.get(1)[1]);
            }
            if (scores.size() > 2) {
                third.setText(scores.get(2)[0] + " " + scores.get(2)[1]);
            }
            if (scores.size() > 3) {
                fourth.setText(scores.get(3)[0] + " " + scores.get(3)[1]);
            }
            if (scores.size() > 4) {
                fifth.setText(scores.get(4)[0] + " " + scores.get(4)[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}