package com.maze.Strategy;

import java.util.ArrayList;

import com.maze.Dijkstra.Dijkstra;
import com.maze.Graph.Graph;
import com.maze.Interactors.*;

/**
 * Classe per calcolare il prossimo movimento del microrobot, implementa Dijkstra per
 * consentire al robot di muoversi in una cella adiacente a quella attuale
 * @see IStrategy  
 */
public class OneMove implements IStrategy{
    
    private Dijkstra dijkstra; // algoritmo Djikstra

    /**
     * Costruttore della classe per passare il grafo e la posizione di uscita.
     * @param maze il grafo del labirinto
     */
    public OneMove(Graph<Box> graph){
        dijkstra = new Dijkstra(graph);
    }

     /**
     * Metodo per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Integer nextMove(Box currentBox){
        ArrayList<Integer> pathToExit; // percorso per uscire dal labirinto
        int solution;  // prossima posizione del microrobot

        do{
            pathToExit = dijkstra.calculateShortestPath(currentBox.getId(), dijkstra.getNodes() - 1);
        }while(!pathToExit.isEmpty()); //cercami un cammino fintanto che non ne trovi uno

        solution = pathToExit.get(1); // Rimuove e restituisce il primo elemento del percorso
        return solution;
    }
}
