package com.maze.Factory;

import com.maze.Interactors.Hardships;

/**
 * Classe astratta che implementa il creator del labirinto.
 */
public abstract class MazeCreator {
    /**
     * Metodo che crea il labirinto in base alla difficoltà passata.
     * @param hardship difficoltà del labirinto
     * @return il labirinto creato
     */
    public abstract Maze createMaze(Hardships hardship);
}
