package com.maze.State;

import com.maze.Graph.Graph;
import com.maze.Interactors.Box;
import com.maze.Strategy.IStrategy;
import com.maze.Strategy.RandomMove;

/**
 * Classe che rappresenta lo stato evade del microrobot 
 * che consente al robot il movimento in una cella adiacente, in modo casuale.
 * E' anche il context delle Strategies
 */
public class Evade implements IState {

    private Box box; // cella in cui si trova il microrobot per calcolare la prossima mossa
    private IStrategy strategy; // strategia che utilizza il microrobot per muoversi

    public Evade(Graph<Box> graph, Box box){
        this.strategy = new RandomMove(graph);
        this.box = box;
    }

    @Override
    public Integer action(Box box) {
        return strategy.nextMove(box);
    }
    
}
