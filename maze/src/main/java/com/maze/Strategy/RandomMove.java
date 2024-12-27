package com.maze.Strategy;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.maze.Graph.Edge;
import com.maze.Graph.Graph;
import com.maze.Interactors.Box;
/**
 * Strategia di movimento casuale per il microrobot, consente al microrobot di
 * muoversi casualmente in una cella adiacente a quella attuale.
 */
public class RandomMove implements IStrategy {

    private Graph<Box> graph; // Grafo del labirinto

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

        // Ottiene la lista degli archi adiacenti alla casella attuale
        ArrayList<Edge> edges = graph.getEdge().get(currentBox.getId());
        // Crea una lista di ID dei nodi adiacenti
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            neighbors.add(edge.getDest()); // Aggiunge l'ID del nodo adiacente
        }

        // Scegli un vicino casuale
        return neighbors.get(ThreadLocalRandom.current().nextInt(0, neighbors.size()));
    }
}