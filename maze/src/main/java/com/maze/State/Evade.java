package com.maze.State;

import com.maze.Graph.Graph;
import com.maze.Interactors.Box;
import com.maze.Strategy.IStrategy;
import com.maze.Strategy.RandomMove;

/**
 * Classe che rappresenta lo stato evade del microrobot 
 * che consente al microrobot il movimento in una cella adiacente, in modo casuale.
 * E' anche il context delle Strategie
 */
public class Evade implements IState {

    private IStrategy strategy; // strategia che utilizza il microrobot per muoversi

    /**
     * Costruttore per inizializzare la strategia di movimento del microrobot
     * @param graph grafo del labirinto
     */
    public Evade(Graph<Box> graph){
        this.strategy = new RandomMove(graph);
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
