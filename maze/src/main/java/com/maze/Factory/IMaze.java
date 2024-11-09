package com.maze.Factory;
/**
 * Interfaccia per la generazione di un labirinto
 */
public interface IMaze {
    /**
     * Metodo per generare il labirinto
     */
    public void generateMaze();

    /**
     * Metodo per costruire il grafo utilizzando il labirinto
     */
    public void createGraph();
}