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

    private Graph<Box> graph; // grafo orientato e ponderato per trovare il percorso più breve
    private ArrayList<Integer> previousNodes; // Lista dei predecessori
    private ArrayList<Integer> distances; // Lista delle distanze minime

    /**
     * Costruttore della classe Dijkstra che accetta un grafo orientato e ponderato.
     * Inizializza il grafo, la lista dei predecessori e la lista delle distanze minime.
     * @param graph il grafo orientato e ponderato su cui eseguire l'algoritmo di Dijkstra
     */
    public Dijkstra(Graph<Box> graph) {
        this.graph = graph;
    }

    /**
     * Calcola il percorso più breve da un nodo sorgente a tutti i nodi nel grafo.
     * @param source l'id del nodo sorgente
     * @param dest l'id del nodo di destinazione
     * @return una lista contenente la distanza minima dal nodo sorgente a ciascun nodo
     */
    public ArrayList<Integer> calculateShortestPath(Integer source, Integer dest) {
        int n = graph.getEdge().size(); // Numero di nodi nel grafo
        distances = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE)); // Inizializza la lista delle distanze minime
        previousNodes = new ArrayList<>(Collections.nCopies(n, -1)); // Inizializza la lista dei predecessori
        distances.set(source, 0); // La distanza dal nodo sorgente a se stesso è 0s
        
        // Coda di priorità per mantenere i nodi in ordine crescente di distanza
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        pq.add(source); // Aggiunge il nodo sorgente alla coda di priorità
    
        while (!pq.isEmpty()) { // Finché la coda di priorità non è vuota
            int current = pq.poll(); // Estrae il nodo con la distanza minima
            if (current == dest) break; // Se il nodo corrente è il nodo di destinazione, esce dal ciclo
    
            for (Edge edge : graph.getEdge().get(current)) { // Per ogni arco uscente dal nodo corrente
                int neighbor = edge.getDest(); // Nodo di destinazione dell'arco
                int newDist = distances.get(current) + edge.getWeight(); // Calcola la nuova distanza
                if (newDist < distances.get(neighbor)) { // Se la nuova distanza è minore della distanza attuale
                    distances.set(neighbor, newDist); // Aggiorna la distanza minima
                    previousNodes.set(neighbor, current); // Aggiorna il predecessore
                    pq.add(neighbor); // Aggiunge il nodo alla coda di priorità
                }
            }
        }
    
        return getShortestPath(dest); // Restituisce il percorso più breve dal nodo sorgente al nodo di destinazione
    }    

    /**
     * Restituisce il percorso più breve dal nodo sorgente al nodo di destinazione.
     * @param destination l'id del nodo di destinazione
     * @return una lista di nodi che rappresenta il percorso più breve
     */
    public ArrayList<Integer> getShortestPath(Integer destination) {
        ArrayList<Integer> path = new ArrayList<>(); // Inizializza il percorso più breve
        if (destination < 0 || destination >= previousNodes.size()) { // Se l'indice è fuori dai limiti
            return path; // Restituisce un percorso vuoto se l'indice è fuori dai limiti
        }
        for (Integer at = destination; at != -1; at = previousNodes.get(at)) { // Per ogni nodo nel percorso
            path.add(at); // Aggiunge il nodo al percorso
        }
        Collections.reverse(path); // Inverte il percorso
        return path; // Restituisce il percorso più breve
    }
}