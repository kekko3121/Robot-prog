package com.maze.Graph;

/**
 * Classe per rappresentare un singolo arco in un grafo orientato e ponderato
 */
public class Edge {
    private final int dest; // Nodo di destinazione
    private final int weight; // Peso dell'arco

    public Edge(int dest, int weight){
        this.dest = dest;
        this.weight = weight;
    }

    public int getDest(){
        return dest;
    }

    public int getWeight(){
        return weight;
    }
}
