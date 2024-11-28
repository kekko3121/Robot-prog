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
        int numNodes = graph.getVertices().size();

        // Inizializzazione delle distanze a infinito
        distances.clear();
        previousNodes.clear();
        for (int i = 0; i < numNodes; i++) {
            distances.add(Integer.MAX_VALUE);
            previousNodes.add(null);
        }
        distances.set(source, 0); // Distanza del nodo sorgente

        // Coda di priorità per gestire i nodi da elaborare, ordinati per distanza
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            int currentNodeId = priorityQueue.poll();

            // Se raggiungiamo il nodo di destinazione, possiamo terminare
            if (currentNodeId == dest) break;

            // Processa ciascun vicino del nodo corrente
            for (Edge edge : graph.getEdges(currentNodeId)) {
                int neighborId = edge.getDest();
                int newDist = distances.get(currentNodeId) + edge.getWeight();

                // Aggiorna la distanza se un percorso più breve viene trovato
                if (newDist < distances.get(neighborId)) {
                    distances.set(neighborId, newDist);
                    previousNodes.set(neighborId, currentNodeId);
                    priorityQueue.add(neighborId);
                }
            }
        }

        return distances;
    }

    /**
     * Restituisce il percorso più breve dal nodo sorgente al nodo di destinazione.
     * @param destination l'id del nodo di destinazione
     * @return una lista di nodi che rappresenta il percorso più breve
     */
    public ArrayList<Integer> getShortestPath(Integer destination) {
        ArrayList<Integer> path = new ArrayList<>();
        for (Integer at = destination; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public int getNodes() {
        return graph.getVertices().size();
    }
}