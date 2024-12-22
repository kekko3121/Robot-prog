package com.maze.Dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;

import com.maze.Graph.Edge;
import com.maze.Graph.Graph;
import com.maze.Interactors.Box;

/**
 * Implementazione dell'algoritmo di Dijkstra per trovare il percorso più breve in un grafo orientato e ponderato.
 */
public class Dijkstra {

    private Graph<Box> graph;
    private ArrayList<Integer> previousNodes; // Lista dei predecessori
    private ArrayList<Integer> distances; // Lista delle distanze minime

    /**
     * Costruttore della classe Dijkstra che accetta un grafo orientato e ponderato.
     * Inizializza il grafo, la lista dei predecessori e la lista delle distanze minime.
     * @param graph il grafo orientato e ponderato su cui eseguire l'algoritmo di Dijkstra
     */
    public Dijkstra(Graph<Box> graph) {
        this.graph = graph;
        this.previousNodes = new ArrayList<>();
        this.distances = new ArrayList<>();
    }

    /**
     * Calcola il percorso più breve da un nodo sorgente a tutti i nodi nel grafo.
     * @param source l'id del nodo sorgente
     * @param dest l'id del nodo di destinazione
     * @return una lista contenente la distanza minima dal nodo sorgente a ciascun nodo
     */
    public ArrayList<Integer> calculateShortestPath(Integer source, Integer dest) {
        int n = graph.getEdge().size();
        distances = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
        previousNodes = new ArrayList<>(Collections.nCopies(n, -1));
        distances.set(source, 0);
    
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        pq.add(source);
    
        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (current == dest) break;
    
            for (Edge edge : graph.getEdge().get(current)) {
                int neighbor = edge.getDest();
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.set(neighbor, newDist);
                    previousNodes.set(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    
        return getShortestPath(dest);
    }    

    /**
     * Restituisce il percorso più breve dal nodo sorgente al nodo di destinazione.
     * @param destination l'id del nodo di destinazione
     * @return una lista di nodi che rappresenta il percorso più breve
     */
    public ArrayList<Integer> getShortestPath(Integer destination) {
        ArrayList<Integer> path = new ArrayList<>();
        if (destination < 0 || destination >= previousNodes.size()) {
            return path; // Restituisce un percorso vuoto se l'indice è fuori dai limiti
        }
        for (Integer at = destination; at != -1; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public int getNodes() {
        return graph.getNodes();
    }
}