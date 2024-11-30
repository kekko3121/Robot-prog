package com.maze.Observer;

import com.maze.Interactors.Position;
import com.maze.State.IState;
import com.maze.Interactors.Box;

/**
 * Interfaccia che rappresenta il Subscriber nel pattern Observer
 */
public interface PositionSub {
    /**
     * Chiamato dal publisher su ogni oggetto di questo tipo,
     * aggiorna lo stato attuale del gioco 
     * @param pos posizione attuale del microrobot
     * @param maze stato attuale del labirinto
     * @param dim dimensione del labirinto
     * @param state stato attuale del microrobot
     */
    public void update(Position pos, Box[][] maze, int dim, IState state);
}
