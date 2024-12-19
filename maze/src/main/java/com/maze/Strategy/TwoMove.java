package com.maze.Strategy;

import java.util.ArrayList;

import com.maze.Dijkstra.Dijkstra;
import com.maze.Graph.Graph;
import com.maze.Interactors.Box;

public class TwoMove implements IStrategy {

    private Dijkstra dijkstra; // algoritmo Djikstra

    /**
     * Costruttore della classe per passare il grafo e la posizione di uscita.
     * @param maze il grafo del labirinto
     */
    public TwoMove(Graph<Box> graph) {
        dijkstra = new Dijkstra(graph);
    }

    /**
     * Metodo per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Integer nextMove(Box currentBox){
        ArrayList<Integer> pathToExit; // percorso per uscire dal labirinto
        int size = 2;  // prossima posizione del microrobot

        do{
            pathToExit = dijkstra.calculateShortestPath(currentBox.getId(), dijkstra.getNodes() - 1);
        }while(pathToExit.size() == 0); //cercami un cammino fintanto che non ne trovi uno

        if(size < pathToExit.size()){
            return pathToExit.get(size);
        }

        else{
            return pathToExit.get(size - 1);
        }
    }
}
