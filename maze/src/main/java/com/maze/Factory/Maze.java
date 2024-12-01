package com.maze.Factory;

import java.util.Random;

import com.maze.Graph.Graph;
import com.maze.Graph.GraphMaze;
import com.maze.Interactors.*;

/**
 * Classe astratta che rappresenta un labirinto.
 * Implementa l'interfaccia IMaze
 * @see IMaze
 */
public abstract class Maze implements IMaze {

    private Box [][] maze; // matrice di caselle che rappresenta il labirinto
    
    private int dim; // dimensione del labirinto

    private Position exitMaze; // posizione dell'uscita del labirinto

    private Graph<Box> emptyBox; // grafo contente le caselle del labirinto senza muri

   /**
    * Costruttore della classe Maze che inizializza la matrice del labirinto con caselle vuote
    * @param dim
    */
    public Maze(int dim){

        maze = new Box[dim][dim]; // inizializza la matrice del labirinto

        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                maze[i][j] = new Box(ValueBox.EMPTY, new Position(i,j)); // inizializza la casella con valore EMPTY
            }
        }
        
        this.dim = dim; // inizializza la dimensione del labirinto
        generateExit(); // genera l'uscita del labirinto
        this.emptyBox = new GraphMaze(this.maze, this.dim);
    }

    /**
     * Ritorna il labirinto
     * @return
     */
    public Box[][] getMaze(){
        return maze;
    }

    /**
     * Ritorna la dimensione del labirinto
     * @return
     */
    public int getDim(){
        return dim;
    }

    /**
     * Genera l'uscita del labirinto
     */
    private void generateExit(){
        exitMaze = new Position(new Random().nextInt(dim), 0); // genera la posizione dell'uscita
    }

    /**
     * Ritorna la posizione dell'uscita del labirinto
     * @return uscita del labirinto
     */
    public Position getExitMaze(){
        return exitMaze;
    }

    /**
     * Ritorna la casella del labirinto in posizione x,y
     * @param x
     * @param y
     * @return
     */
    public Box getBox(int x, int y){
        return maze[x][y];
    
    }

    /**
     * Restituisce il labirinto sotto forma di grafo senza muri
     * @return Graph<box> il grafo del labirinto
     */
    public Graph<Box> getGraphMaze(){
        return this.emptyBox;
    }

    /**
     * Metodo per restituire una casella del labirinto in base all'id
     * @param id
     * @return la casella del labirinto
     */
    public Box getBoxById(int id){
        return this.emptyBox.getNode(id);
    }

    public void createGraph(){
        this.emptyBox.graphGen();
    }

    /**
     * Metodo astratto che genera il labirinto
     * Verrà implementato dalle classi figlie
     */
    public abstract void generateMaze();
}