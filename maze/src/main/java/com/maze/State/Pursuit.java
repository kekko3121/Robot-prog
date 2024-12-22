package com.maze.State;

import com.maze.Graph.Graph;
import com.maze.Interactors.Box;
import com.maze.Strategy.IStrategy;
import com.maze.Strategy.OneMove;

/**
 * Classe che rappresenta lo stato pursuit del microrobot 
 * che consente al robot il movimento in una cella adiacente, tramite Dijkstra.
 * E' anche il context delle Strategies
 */
public class Pursuit implements IState {

    private IStrategy strategy; // strategia che utilizza il microrobot per muoversi

    public Pursuit(Graph<Box> graph, Integer exitMazeId) {
        this.strategy = new OneMove(graph, exitMazeId);
    }

    @Override
    public Integer action(Box box) {
        return strategy.nextMove(box);
    }
}
