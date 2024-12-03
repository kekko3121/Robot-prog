package com.maze;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ScoreController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label first;

    @FXML
    private Label second;

    @FXML
    private Label third;

    @FXML
    private Label fourth;

    @FXML
    private Label fifth;

    @FXML
    private void initialize() {
        // Inizializza i valori delle etichette e della barra di progresso
        first.setText("Player 1: 100");
        second.setText("Player 2: 90");
        third.setText("Player 3: 80");
        fourth.setText("Player 4: 70");
        fifth.setText("Player 5: 60");
        progressBar.setProgress(0.0);
        startProgressBar();
    }

    public void startProgressBar() {
        // Crea un nuovo Thread per l'incremento automatico
        Thread thread = new Thread(() -> {
            try {
                // Incrementa la ProgressBar ogni secondo fino a raggiungere il massimo
                for (double progress = 0.0; progress <= 1.0; progress += 0.1) {
                    // Aggiorna la ProgressBar sulla JavaFX Application Thread
                    final double currentProgress = progress;
                    javafx.application.Platform.runLater(() -> progressBar.setProgress(currentProgress));

                    // Dormi il Thread per un secondo
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Imposta il thread come daemon per farlo terminare quando l'applicazione termina
        thread.setDaemon(true);

        // Avvia il thread
        thread.start();
    }
}