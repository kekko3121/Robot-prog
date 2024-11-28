package com.maze.Strategy;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.Graph.Edge;
import com.maze.Graph.Graph;
import com.maze.Graph.GraphMaze;
import com.maze.Interactors.Box;
/**
 * Strategia di movimento casuale per il robot, consente al robot di
 * muoversi casualmente in una cella adiacente a quella attuale.
 */
public class RandomMove implements IStrategy {

    private final Graph<Box> graph;

    /**
     * Costruttore per inizializzare la strategia con il grafo.
     * @param graph il grafo del labirinto
     */
    public RandomMove(Graph<Box> graph) {
        this.graph = graph;
    }

    /**
     * Calcola la prossima mossa casuale verso una cella adiacente.
     * @param currentBox la casella attuale del robot
     * @return la prossima casella scelta casualmente tra le adiacenti
     */
    @Override
    public Integer nextMove(Box currentBox) {

        // Ottiene la posizione attuale del robot e calcola i vicini della casella attuale
        ArrayList<Edge> edges = graph.getEdges(currentBox.getPosition().getX() * ((GraphMaze) graph).getDim() + currentBox.getPosition().getY());
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            neighbors.add(edge.getDest());
        }

        // Scegli un vicino casuale
        int randomIndex = ThreadLocalRandom.current().nextInt(0, neighbors.size());

        // Restituisci l'ID del nodo scelto
        return neighbors.get(randomIndex);
    }
}