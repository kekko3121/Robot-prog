package com.maze;

import java.io.IOException;
import java.util.ArrayList;

import com.maze.Proxy.Classification;

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

    private PlayerProperty playerProperty;

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlayerProperty(PlayerProperty playerProperty) {
        this.playerProperty = playerProperty;
    }

    @FXML
    private void initialize() {
        try {
            // Leggi la classifica dal file
            ArrayList<String[]> scores = new Classification("score.dat").read();

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
                fifth.setText(scores.get(4)[0] + ": " + scores.get(4)[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            mazeController.setPlayerProperty(playerProperty);
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