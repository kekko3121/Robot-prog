package com.maze.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Classe Grafo generica astratta, contiene i metodi base di gestione
 * di un grafo.
 * Il grafo e' un grafo orientato e pesato*/
public abstract class Graph<T> {
    
    // Mappa dei vertici con la lista dei loro archi
    private Map<Integer, ArrayList<Edge>> edge; //

    // Costruttore per inizializare la mappa
    Graph(){
        edge = new HashMap<>();
    }

    /**
     * Aggiunge un arco orientato e pesato tra due nodi del grafo.
     * Se i nodi di partenza o destinazione non esistono, vengono aggiunti al grafo.
     * @param source il nodo di partenza dell'arco
     * @param dest il nodo di destinazione dell'arco
     * @param weight il peso dell'arco
     */
    public void addEdge(Integer source, Integer dest, Integer weight){
        // Inizializza la lista di adiacenza per source se non esiste già
        edge.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(dest, weight));
        // Inizializza la lista di adiacenza per dest se non esiste già
        edge.putIfAbsent(dest, new ArrayList<>());
    }

    /**
     * Restituisce la mappa completa dei vertici del grafo con i rispettivi archi di adiacenza.
     * La mappa contiene i nodi del grafo come chiavi (di tipo Integer), ciascuno associato a una lista di archi 
     * (List<Edge>) che rappresenta i collegamenti con i nodi adiacenti.
     * @return una mappa in cui le chiavi sono i nodi del grafo e i valori sono le liste degli archi adiacenti.
     */
    public Map<Integer, ArrayList<Edge>> getEdge(){
        return this.edge;
    }

    /**
     * Metodo astratto per restituire il nodo da ridefinire
     * @param index
     * @return nodi del grafo del tipo scelto
     */
    public abstract T getNode(Integer index);

    /**
     * Metodo per generare un grafo da ridefinire
     */
    public abstract void graphGen();
}
