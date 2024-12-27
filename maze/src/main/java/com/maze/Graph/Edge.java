package com.maze.Graph;

/**
 * Classe per rappresentare un singolo arco in un grafo orientato e ponderato
 */
public class Edge {
    private final Integer dest; // Nodo di destinazione
    private final Integer weight; // Peso dell'arco

    /**
     * Costruttore della classe
     * @param dest nodo di destinazione
     * @param weight peso dell'arco
     */
    public Edge(Integer dest, Integer weight){
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Metodo per ottenere il nodo di destinazione
     * @return il nodo di destinazione
     */
    public Integer getDest(){
        return dest;
    }

    /**
     * Metodo per ottenere il peso dell'arco
     * @return il peso dell'arco
     */
    public Integer getWeight(){
        return weight;
    }
}
