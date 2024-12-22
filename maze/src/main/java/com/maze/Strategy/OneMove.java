package com.maze.Strategy;

import java.util.ArrayList;

import com.maze.Dijkstra.Dijkstra;
import com.maze.Graph.Graph;
import com.maze.Interactors.Box;

/**
 * Classe per calcolare il prossimo movimento del microrobot, implementa Dijkstra per
 * consentire al robot di muoversi in una cella adiacente a quella attuale
 * @see IStrategy  
 */
public class OneMove implements IStrategy{
    
    private Dijkstra dijkstra; // algoritmo Djikstra

    private Integer exitMazeId; // id della cella di uscita

    /**
     * Costruttore della classe per passare il grafo e la posizione di uscita.
     * @param maze il grafo del labirinto
     */
    public OneMove(Graph<Box> graph, Integer exitMazeId){
        dijkstra = new Dijkstra(graph);
        this.exitMazeId = exitMazeId;
    }

     /**
     * Metodo per calcolare il prossimo movimento del microrobot con l'algoritmo ABC.
     * @param currentPosition
     * @return la nuova posizione del microrobot
     */
    public Integer nextMove(Box currentBox){
        ArrayList<Integer> pathToExit; // percorso per uscire dal labirinto

        do{
            pathToExit = dijkstra.calculateShortestPath(currentBox.getId(), exitMazeId);
        }while(pathToExit.size() == 0); //cercami un cammino fintanto che non ne trovi uno
        
        return pathToExit.get(1); // Rimuove e restituisce il primo elemento del percorso
    }
}
