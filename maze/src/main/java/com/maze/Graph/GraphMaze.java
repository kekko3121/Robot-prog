package com.maze.Graph;

import com.maze.Interactors.ValueBox;
import com.maze.Interactors.Box;

/**
 * Classe per la generazione del grafo a partire dal labirinto generato
 * estende la classe Graph astratta
 */
public class GraphMaze extends Graph<Box>{
    
    private Box[][] maze; // labirinto

    private int dim; // dimensione del labirinto

    /**
     * Costruttore della classe per passare il labirinto e la dimensione
     * @param maze
     * @param dim
     */
    public GraphMaze(Box[][] maze, int dim){
        super(); // chiamata al costruttore della classe madre
        this.maze = maze; // inizializzazione del labirinto
        this.dim = dim; // inizializzazione della dimensione
    }

    /**
     * Metodo per ottenere il nodo a partire dall'indice
     * @param index
     * @return
     */
    @Override
    public Box getNode(Integer index) {
        for (int i = 0; i < this.dim; i++){
            for (int j = 0; j < this.dim; j++) {
                if (this.maze[i][j].getId() == index){
                    return maze[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Metodo per generare il grafo a partire dal labirinto
     */
    public void graphGen(){
        //settaggio degli id dei nodi - solo i nodi non "wall" hanno un id
        int id = 0;
        for (int i = 0; i < this.dim; i++){
            for (int j = 0; j < this.dim; j++){
                if (this.maze[i][j].getValue() != ValueBox.WALL){
                    this.maze[i][j].setId(id++);
                }
            }
        }

        //Si aggiungono gli archi in base ai vicini - le Box muro non hanno
        //archi entranti / archi uscenti
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (this.maze[i][j].getValue() == ValueBox.WALL){
                    continue;
                }

                //se esiste la Box superiore-sx e se non e' un muro -> aggiungi l'arco corrispondente
                if (i - 1 >= 0 && j - 1 >= 0){
                    if (this.maze[i-1][j-1].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i-1][j-1].getId(), 1);
                    }
                }

                //se esiste la Box laterale-sx e se non e' un muro -> aggiungi l'arco corrispondente
                if (j - 1 >= 0){
                    if (this.maze[i][j-1].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i][j-1].getId(), 1);
                    }
                }

                //se esiste la Box inferiore-sx e se non e' un muro -> aggiungi l'arco corrispondente
                if (j - 1 >= 0 && j - 1 <= this.dim - 1 && i + 1 <= this.dim - 1){
                    if (this.maze[i+1][j-1].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i+1][j-1].getId(), 1);
                    }
                }

                //se esiste la Box inferiore e se non e' un muro -> aggiungi l'arco corrispondente
                if (i + 1 <= this.dim - 1 && j <= this.dim - 1){
                    if (this.maze[i+1][j].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i+1][j].getId(), 1);
                    }
                }

                //se esiste la Box inferiore-dx e se non e' un muro -> aggiungi l'arco corrispondente
                if (i + 1 <= this.dim - 1 && j + 1 <= this.dim - 1){
                    if (this.maze[i+1][j+1].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(),this.maze[i+1][j+1].getId(), 1);
                    }
                }

                //se esiste la Box laterale-dx e se non e' un muro -> aggiungi l'arco corrispondente
                if (i <= this.dim - 1 && j + 1 <= this.dim - 1){
                    if (maze[i][j+1].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i][j+1].getId(), 1);
                    }
                }

                //se esiste la Box superiore-dx e se non e' un muro -> aggiungi l'arco corrispondente
                if (i - 1 <= this.dim - 1 && j + 1 <= this.dim - 1 && i - 1 >= 0){
                    if (maze[i-1][j+1].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i-1][j+1].getId(), 1);
                    }
                }

                //se esiste la Box superiore e se non e' un muro -> aggiungi l'arco corrispondente
                if (i - 1 >= 0 && i - 1 <= this.dim - 1 && j <= this.dim - 1){
                    if (maze[i-1][j].getValue() != ValueBox.WALL){
                        this.addEdge(this.maze[i][j].getId(), this.maze[i-1][j].getId(), 1);
                    }
                }
            }
        }
    }

    /**
     * Restituisce la dimensione del labirinto.
     * @return la dimensione del labirinto
     */
    public Integer getDim() {
        return this.dim;
    }
}
