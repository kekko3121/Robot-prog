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

    private IStrategy strategy; // strategia che utilizza il microrobot per muoversi

    public Flee(Graph<Box> graph){
        this.strategy = new TwoMove(graph);
    }

    @Override
    public Integer action(Box box) {
        return strategy.nextMove(box);
    }
    
}
