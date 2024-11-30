package com.maze.State;

import com.maze.Interactors.Box;
import com.maze.Interactors.Position;
import com.maze.Interactors.ValueBox;

/**
 * Classe che rappresenta un microrobot nel labirinto.
 */
public class Microrobot {
    
    private IState microRobotState; // Stato attuale del microrobot

    private Box box; // cella attuale del microrobot

    /**
     * Costruttore della classe per passare la posizione attuale e lo stato del microrobot.
     * @param box cella attuale del microrobot
     * @param microRobotState stato attuale del microrobot
     */
    public Microrobot(Box box, IState microRobotState){
        this.box = box;
        this.microRobotState = microRobotState;
    }

    /**
     * Metodo per cambiare lo stato del microrobot.
     * @param microRobotState nuovo stato del microrobot
     */
    public void setActualBox(Box actualBox){
        this.box = actualBox;
    }

    /**
     * Restituisce la posizione attuale del microrobot.
     * @return
     */
    public Position getPosition(){
        return this.box.getPosition();
    }

    /**
     * Restituisce la cella attuale del microrobot.
     * @return
     */
    public Box getActualBox(){
        return this.box;
    }

    /**
     * Restituisce il valore della cella attuale del microrobot.
     * @return
     */
    public ValueBox getActualValueBox(){
        return this.box.getValue();
    }

    /**
     * Cambia lo stato del microrobot.
     * @param microRobotState
     */
    public void setMicroRobotStrate(IState microRobotState){
        this.microRobotState = microRobotState;
    }

    /**
     * Restituisce la stato del microrobot.
     * @return stato del microrobot
     */
    public IState getMicroRobotState(){
        return this.microRobotState;
    }

    /**
     * Metodo per richiamare action dello stato del microrobot,
     * che permette di calcolare la prossima mossa.
     * @param box cella da cui calcolare la prossima mossa
     * @return Integer l'id della prossima cella in cui muoversi
     */
    public Integer move(Box box){
        return this.microRobotState.action(box);
    }
}