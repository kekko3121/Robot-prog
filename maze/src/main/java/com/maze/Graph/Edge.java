package com.maze.Graph;

/**
 * Classe per rappresentare un singolo arco in un grafo orientato e ponderato
 */
public class Edge {
    private final Integer dest; // Nodo di destinazione
    private final Integer weight; // Peso dell'arco

    public Edge(Integer dest, Integer weight){
        this.dest = dest;
        this.weight = weight;
    }

    public Integer getDest(){
        return dest;
    }

    public Integer getWeight(){
        return weight;
    }
}
