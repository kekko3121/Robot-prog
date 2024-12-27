package com.maze.State;

import com.maze.Graph.Graph;
import com.maze.Interactors.Box;
import com.maze.Strategy.IStrategy;
import com.maze.Strategy.TwoMove;

/**
 * Classe che rappresenta lo stato flee del microrobot 
 * che consente al microrobot il movimento fino a due cella adiacente, tramite Dijkstra.
 * E' anche il context delle Strategie
 */
public class Flee implements IState {

    private IStrategy strategy; // strategia che utilizza il microrobot per muoversi

    /**
     * Costruttore per inizializzare la strategia di movimento del microrobot
     * @param graph grafo del labirinto
     */
    public Flee(Graph<Box> graph, Integer exitMazeId){
        this.strategy = new TwoMove(graph, exitMazeId);
    }

    /**
     * Metodo per ottenere la prossima mossa del microrobot
     * @param box cella in cui si trova il microrobot
     * @return la prossima mossa del microrobot
     */
    @Override
    public Integer action(Box box) {
        return strategy.nextMove(box);
    }
    
}
