package com.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;

import com.maze.Observer.Game;
import com.maze.Observer.UpdateGame;
import com.maze.Proxy.Classification;
import com.maze.Interactors.Box;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.ValueBox;

public class MazeController {

    @FXML
    private Pane mazePane;

    @FXML
    private Label Name;

    @FXML
    private Label Surname;

    @FXML
    private Label RobotState;

    @FXML
    private Label Time;

    @FXML
    private ArrayList<Rectangle> mazeView;

    @FXML
    private Rectangle microrobot;

    private Box[][] maze;

    private Game instance;

    private UpdateGame gameStateSub;
    
    private Timeline timeline;

    private Timeline timerTimeline;

    private String playerTime;

    private String difficulty;
    
    private PlayerProperty playerProperty;

    private long startTime;

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlayerProperty(PlayerProperty playerProperty) {
        this.playerProperty = playerProperty;
    }

    public void initializeGame() {

        Name.setText(playerProperty.getPlayerName());
        Surname.setText(playerProperty.getPlayerSurname());

        // Inizializza il labirinto e il gioco
        if (difficulty.equals("Easy")) {
            instance = new Game(Hardships.EASY);
        } else if (difficulty.equals("Medium")) {
            instance = new Game(Hardships.MEDIUM);
        } else if (difficulty.equals("Hard")) {
            instance = new Game(Hardships.HARD);
        }
        gameStateSub = new UpdateGame();
        maze = instance.getMaze();

        // Inizializza la vista del labirinto
        buildMaze();
        
        // Crea e imposta il rettangolo del microrobot con l'immagine
        microrobot = new Rectangle(37, 37);
        microrobot.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/images/microrobot.png"))));
        mazePane.getChildren().add(microrobot);

        // Posiziona il microrobot all'inizio del labirinto
        microrobot.setLayoutX(instance.getMicrorobotPosition().getX() * 37);
        microrobot.setLayoutY(instance.getMicrorobotPosition().getY() * 37);

        // Inizia il gioco
        startGame();
    }

    private void startGame() {
        instance.subscribe(gameStateSub); // Iscrive l'osservatore al gioco

        // Salva il tempo di inizio in millisecondi
        startTime = System.currentTimeMillis();

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            instance.notifyObservers();
            instance.go();
            instance.updateBoxs();
            maze = gameStateSub.getMaze();
            this.updateMazeView(); // Aggiorna la vista del labirinto
            instance.notifyObservers();
            RobotState.setText(gameStateSub.getState());

            if (instance.getMicrorobotPosition().getX() == instance.getExitPosition().getX() && instance.getMicrorobotPosition().getY() == instance.getExitPosition().getY()) {
                try {
                    this.afterGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            microrobot.setLayoutX(microrobot.getLayoutX() + (gameStateSub.getUpdate().getY() * 37)); // Aggiorna la posizione del microrobot sull'asse x
            microrobot.setLayoutY(microrobot.getLayoutY() + (gameStateSub.getUpdate().getX() * 37)); // Aggiorna la posizione del microrobot sull'asse y
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Timeline per aggiornare il timer
        timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Calcola il tempo trascorso
            long elapsedMillis = System.currentTimeMillis() - startTime;
            long elapsedSeconds = elapsedMillis / 1000;
            long hours = elapsedSeconds / 3600;
            long minutes = (elapsedSeconds % 3600) / 60;
            long seconds = elapsedSeconds % 60;

            playerTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
 
            // Aggiorna la label del tempo
            Time.setText(playerTime);
        }));

        timerTimeline.setCycleCount(Animation.INDEFINITE);
        timerTimeline.play();
    }

    private void updateMazeView() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Rectangle cell = mazeView.get(i * maze.length + j);
                if(i == instance.getExitPosition().getX() && j == instance.getExitPosition().getY()) {
                    cell.setFill(Color.WHITE);
                }
                switch (maze[i][j].getValue()) {
                    case WALL:
                        cell.setFill(Color.BLACK);
                        break;
                    case EMPTY:
                        cell.setFill(Color.WHITE);
                        break;
                    case RED:
                        cell.setFill(Color.RED);
                        break;
                    case GREEN:
                        cell.setFill(Color.GREEN);
                        break;
                    case YELLOW:
                        cell.setFill(Color.YELLOW);
                        break;
                    case CYAN:
                        cell.setFill(Color.CYAN);
                        break;
                }
            }
        }
    }

    private void afterGame() throws IOException {
        timeline.stop();
        timerTimeline.stop();
        insertPlayerData(playerProperty.getPlayerName(), playerProperty.getPlayerSurname(), playerTime);
    }

    private void insertPlayerData(String name, String surname, String time) {
        try {
            Classification classification = new Classification("score.dat", name, surname, time);
            classification.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildMaze() {
        int size = maze.length;
        mazeView = new ArrayList<>();
        // Disegna il labirinto interno
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle rect = new Rectangle(37, 37);
                if (maze[i][j].getValue() == ValueBox.WALL) {
                    rect.setFill(Color.BLACK); // Parete interna
                    rect.setStroke(Color.BLACK); // Bordo nero
                    rect.setStrokeWidth(1.0);
                } else {
                    rect.setFill(Color.WHITE); // Passaggio
                    rect.setStroke(Color.BLACK); // Bordo nero
                    rect.setStrokeWidth(1.0);
                }
                mazeView.add(rect);
                mazePane.getChildren().add(rect);
                rect.setX(j * 37);
                rect.setY(i * 37);
            }
        }
        // Disegna i muri esterni attorno al labirinto interno
        for (int i = -1; i <= size; i++) {
            for (int j = -1; j <= size; j++) {
                if ( (i == -1 || i == size || j == -1 || j == size) && !(i == instance.getExitPosition().getX() - 1 && j == instance.getExitPosition().getY() - 1)) {
                    Rectangle rect = new Rectangle(37, 37);
                    rect.setFill(Color.GRAY); // Colore dei muri esterni
                    rect.setStroke(Color.BLACK); // Bordo nero
                    rect.setStrokeWidth(1.0);
                    mazePane.getChildren().add(rect);
                    rect.setX(j * 37);
                    rect.setY(i * 37);
                }
            }
        }
    }
}