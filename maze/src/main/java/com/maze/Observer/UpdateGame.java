package com.maze.Observer;

import com.maze.Interactors.Position;
import com.maze.Interactors.Box;
import com.maze.State.IState;
import com.maze.State.Pursuit;
import com.maze.State.Seek;
import com.maze.State.Flee;

/**
 * Classe che rappresenta il ConcreteSubscriber nel pattern
 * Observer. Si occupa di contenere gli aggiornamenti inviati
 * dal game, in merito a posizione e stato del robot, stato del
 * labirinto e dimensione dello stesso*/
public class UpdateGame implements PositionSub {
    
    private Position pos; // posizione attuale del microrobot

    private Position expos; // posizione precedente del microrobot

    private Box[][] maze; // labirinto

    private int dim; // dimensione del labirinto

    private IState state; // stato attuale del microrobot

    /**
     * Costruttore per inizializzare le liste dei microrobot e il labirinto
     */
    public UpdateGame(){
        this.expos = new Position(0,0);
        this.pos = null;
        this.maze = null;
    }

    /**
     * Aggiorna lo stato attuale del gioco
     * @param microrobots lista dei microrobot
     * @param maze labirinto
     */
    public void update(Position pos, Box[][] maze, int dim, IState state){
        this.expos = this.pos;
        this.pos = pos;
        this.maze = maze;
        this.dim = dim;
        this.state = state;
    }

    /**
     * Calcola qual e' lo shifting tra la posizione attuale e quella precedente
     * @return Position un oggetto che contiene la differenza tra le coordinate
     * attuali e quelle precedenti*/
    public Position getUpdate(){
        Position newPos = new Position(0,0);

        newPos.setX(pos.getX() - expos.getX());
        newPos.setY(pos.getY() - expos.getY());

        return newPos;
    }

    /**
     * Restituisce la dimensione del labirinto
     * @return
     */
    public int getDim(){
        return this.dim;
    }

    /**
     * Restituisce il labirinto
     * @return
     */
    public Box[][] getMaze(){
        return this.maze;
    }

    public String getState() {
        if(state instanceof Pursuit)
            return "Robot state: Pursuit";
        else if(state instanceof Seek)
            return "Robot state: Seek";
        else if(state instanceof Flee)
            return "Robot state: Flee";
        else
            return "Robot state: Evade";
    }
}
