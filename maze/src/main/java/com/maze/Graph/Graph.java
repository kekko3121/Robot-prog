package com.maze.Graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
/**
 * Classe Grafo generica astratta, contiene i metodi base di gestione
 * di un grafo.
 * Il grafo e' un grafo orientato e ponderato*/
public abstract class Graph<T> {
    
    // Multimap dei vertici con la lista dei loro archi
    private Multimap<Integer, Edge> edges;

    // Costruttore per inizializzare la multimap
    Graph(){
        edges = ArrayListMultimap.create();
    }

    /**
     * Aggiunge un arco orientato e ponderato tra due nodi del grafo.
     * Se i nodi di partenza o destinazione non esistono, vengono aggiunti al grafo.
     * @param source il nodo di partenza dell'arco
     * @param dest il nodo di destinazione dell'arco
     * @param weight il peso dell'arco
     */
    public void addEdge(Integer source, Integer dest, Integer weight){
        edges.put(source, new Edge(dest, weight));
    }

     /**
     * Restituisce la multimap completa dei vertici del grafo con i rispettivi archi di adiacenza.
     * La multimap contiene i nodi del grafo come chiavi (di tipo Integer), ciascuno associato a una lista di archi 
     * (List<Edge>) che rappresenta i collegamenti con i nodi adiacenti.
     * @return una multimap in cui le chiavi sono i nodi del grafo e i valori sono le liste degli archi adiacenti.
     */
    public Multimap<Integer, Edge> getEdges(){
        return this.edges;
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
