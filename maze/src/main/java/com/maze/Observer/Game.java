package com.maze.Observer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.Factory.Maze;
import com.maze.Factory.MazeDifficulty;
import com.maze.State.Flee;
import com.maze.State.Microrobot;
import com.maze.State.Pursuit;
import com.maze.State.Seek;
import com.maze.Interactors.Hardships;
import com.maze.Interactors.Box;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;


/**
 * Classe che crea un labirinto, gestisce le mosse del microrobot/posizioni. 
 * Al suo interno ha anche un modulo per consentirgli di notificare le classi 
 * che vogliono conoscere lo stato attuale del labirinto (pattern Observer)
 * @see Observable 
 */
public class Game implements Observable {

    private Maze maze; // labirinto
    
    private Microrobot microrobot; // microrobot - il giocatore all'interno del labirinto

    private ArrayList<PositionSub> observers; // Array list per aggiornare tutte le classi appartenenti alla newsletter

    private Boolean firstMove; //controlla se è il primo movimento del microrobot

    /**
     * Costruttore per inizializzare il labirinto con la difficolta scelta dall'utente
     * @param hardships
     */
    public Game(Hardships hardships) {
        maze = new MazeDifficulty().createMaze(hardships); // crea il labirinto con la difficoltà scelta
        
        assert maze != null; // Assicura che il labirinto sia stato creato

        this.microrobot = new Microrobot(maze.getBoxById(0), new Pursuit(maze.getGraphMaze(), this.maze.getBox(this.maze.getExitMaze().getX(), this.maze.getExitMaze().getY()).getId()));// inizializza la lista dei microrobot
        observers = new ArrayList<>(); // inizializza la lista degli osservatori
        firstMove = true; // inizializza il primo movimento del microrobot
    }

    /**
     * Metodo per iscrivere un osservatore alla newsletter
     * @param observer
     */
    public void subscribe(PositionSub observer) {
        observers.add(observer);
    }

    /**
     * Metodo per notificare tutti gli osservatori iscritti alla newsletter
     */
    public void notifyObservers() {
        for (PositionSub observer : observers) {
            observer.update(this.microrobot.getPosition(), this.maze.getMaze(), this.maze.getDim(),this.microrobot.getMicroRobotState());
        }
    }

    /**
     * Restituisce il labirinto
     * @return labirinto
     */
    public Box[][] getMaze() {
        return maze.getMaze();
    }

    /*
     * Metodo per restituire la posizione di un microrobot
     */
    public Position getMicrorobotPosition(){
        return this.microrobot.getPosition();
    }

    /**
     * Metodo per muovere il microrobot all'interno del labirinto
     */
    public Integer move() {
        return this.microrobot.move(this.microrobot.getActualBox());
    }

    /**
     * Metodo per restituire la posizione dell'uscita del labirinto
     * @return uscita del labirinto
     */
    public Position getExitPosition() {
        return maze.getExitMaze();
    }

    /**
     * Metodo per aggiornare i colori delle celle.
     * Scorre la matrice e assegna, per ogni cella, un nuovo colore, 
     * in base a una probabilita'.*/
    public void updateCells(){
        Box[][] maze = this.maze.getMaze();
        for(int i = 0; i < this.maze.getDim(); i++){
            for(int j = 0; j < this.maze.getDim(); j++) {

                //se e' la prima mossa, non cambiare colore alla cella di partenza
               if (firstMove) {
                    firstMove = false;
                    if( i == 0 && j ==0){
                        continue;
                    }
               }

                if (maze[i][j].getValue() != ValueBox.WALL && maze[i][j] != maze[this.maze.getDim() - 1][this.maze.getDim() - 1]) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 12);
                    if (randomNum < 4){
                        maze[i][j].setValue(ValueBox.EMPTY);
                    }
                    else if (randomNum < 6){
                        maze[i][j].setValue(ValueBox.YELLOW);
                    }
                    else if (randomNum < 8){
                        maze[i][j].setValue(ValueBox.RED);
                    }
                    else if (randomNum < 10){
                        maze[i][j].setValue(ValueBox.CYAN);
                    }
                    else if (randomNum < 12){
                        maze[i][j].setValue(ValueBox.GREEN);
                    }
                }
            }
        }
    }

    /**
     * Metodo per coordinare il movimento del microrobot, avvalendosi anche della funzione move().
     * Sfrutta il colore della cella su cui si trova attualmente il microrobot per determinarne
     * la prossima mossa.
     */
    public void go(){

        //se la cella attuale e' vuota, muoviti tramite lo stato che gia' possiedi;
        //aggiorna poi la posizione corrente del robot.
        if (this.microrobot.getActualValueBox() == ValueBox.EMPTY) {
            this.microrobot.setActualBox(this.maze.getBoxById(move())); //aggiorna la posizione corrente del robot
        }

        //se la cella attuale e' verde, cambia stato in "Pursuit" e calcola la prossima mossa;
        //aggiorna poi la posizione corrente del robot.
        else if (this.microrobot.getActualValueBox() == ValueBox.GREEN) {
            this.microrobot.setMicroRobotStrate(new Pursuit(this.maze.getGraphMaze(), this.maze.getBox(this.maze.getExitMaze().getX(), this.maze.getExitMaze().getY()).getId())); //cambia lo stato del microrobot
            this.microrobot.setActualBox(this.maze.getBoxById(move())); //aggiorna la posizione corrente del robot
        }

        //se la cella attuale e' rossa, cambia stato in "Seek" e calcola la prossima mossa;
        //aggiorna poi la posizione corrente del robot.
        else if (this.microrobot.getActualValueBox() == ValueBox.RED) {
            this.microrobot.setMicroRobotStrate(new Seek(this.maze.getGraphMaze(), this.maze.getBox(this.maze.getExitMaze().getX(), this.maze.getExitMaze().getY()).getId())); //cambia lo stato del microrobot
            this.microrobot.setActualBox(this.maze.getBoxById(move())); //aggiorna la posizione corrente del robot
        }

        //se la cella attuale e' gialla, cambia stato in "Flee" e calcola la prossima mossa;
        //aggiorna poi la posizione corrente del robot.
        else if (this.microrobot.getActualValueBox() == ValueBox.YELLOW) {
            this.microrobot.setMicroRobotStrate(new Flee(this.maze.getGraphMaze(), this.maze.getBox(this.maze.getExitMaze().getX(), this.maze.getExitMaze().getY()).getId())); //cambia lo stato del microrobot
            this.microrobot.setActualBox(this.maze.getBoxById(move())); //aggiorna la posizione corrente del robot
        }
        //se la cella attuale e' ciano, cambia stato in "EvadeState" e calcola la prossima mossa;
        //aggiorna poi la posizione corrente del robot.
        else if (this.microrobot.getActualValueBox() == ValueBox.CYAN) {
            this.microrobot.setMicroRobotStrate(new Flee(this.maze.getGraphMaze(), this.maze.getBox(this.maze.getExitMaze().getX(), this.maze.getExitMaze().getY()).getId())); //cambia lo stato del microrobot
            this.microrobot.setActualBox(this.maze.getBoxById(move())); //aggiorna la posizione corrente del robot
        }
    }
}