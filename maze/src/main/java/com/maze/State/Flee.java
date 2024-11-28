package com.maze.State;

import com.maze.Graph.Graph;
import com.maze.Interactors.Box;
import com.maze.Strategy.IStrategy;
import com.maze.Strategy.TwoMove;

/**
 * Classe che rappresenta lo stato flee del microrobot 
 * che consente al robot il movimento fino a due cella adiacente, tramite Dijkstra.
 * E' anche il context delle Strategies
 */
public class Flee implements IState {

    private Box box; // cella in cui si trova il microrobot per calcolare la prossima mossa
    private IStrategy strategy; // strategia che utilizza il microrobot per muoversi

    public Flee(Graph<Box> graph, Box box){
        this.strategy = new TwoMove(graph);
        this.box = box;
    }

    @Override
    public Integer action(Box box) {
        return strategy.nextMove(box);
    }
    
}
