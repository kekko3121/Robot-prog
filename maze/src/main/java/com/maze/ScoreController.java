package com.maze;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private String difficulty;

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

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

    private void startProgressBar() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                double progress = progressBar.getProgress() + 0.1;
                progressBar.setProgress(progress);
                if (progress >= 0.99) {
                    proceedToMaze();
                }
            })
        );
        timeline.setCycleCount(10);
        timeline.play();
    }

    private void proceedToMaze() {
        System.out.println("Proceeding to Maze scene...");
        try {
            // Load the Maze.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("maze.fxml"));
            Parent root = loader.load();

            // Get the controller of MazeController
            MazeController mazeController = loader.getController();
            mazeController.setDifficulty(difficulty);
            mazeController.initializeGame();

            // Show the Maze scene
            Stage stage = (Stage) progressBar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occurred while loading maze.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}