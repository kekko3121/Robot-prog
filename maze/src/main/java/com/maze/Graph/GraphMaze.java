package com.maze.Graph;

import com.maze.Interactors.ValueBox;
import com.maze.Interactors.Box;

public class GraphMaze extends Graph{
    
    private Box[][] maze;

    private int dim;

    public GraphMaze(Box[][] maze, int dim){
        super();
        this.maze = maze;
        this.dim = dim;
    }


    @Override
    public Box getNode(Integer index) {
        for (int i = 0; i < this.dim; i++)
            for (int j = 0; j < this.dim; j++) {
                if (this.maze[i][j].getId() == index)
                    return maze[i][j];
            }

        return null;
    }


    public void graphGen(){
        //settaggio degli id dei nodi - solo i nodi non "wall" hanno un id
        int id = 0;
        for (int i = 0; i < this.dim; i++)
            for (int j = 0; j < this.dim; j++)
                if (this.maze[i][j].getValue() != ValueBox.WALL)
                    this.maze[i][j].setId(id++);



        //Si aggiungono gli archi in base ai vicini - le Box muro non hanno
        //archi entranti / archi uscenti
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (this.maze[i][j].getValue() == ValueBox.WALL)
                    continue;

                //se esiste la Box superiore-sx e se non e' un muro -> aggiungi l'arco corrispondente
                 if (i - 1 >= 0 && j - 1 >= 0)
                    if (this.maze[i-1][j-1].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i-1][j-1].getId(), 1);

                //se esiste la Box laterale-sx e se non e' un muro -> aggiungi l'arco corrispondente
                 if (j - 1 >= 0)
                    if (this.maze[i][j-1].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i][j-1].getId(), 1);

                //se esiste la Box inferiore-sx e se non e' un muro -> aggiungi l'arco corrispondente
                 if (j - 1 >= 0 && j - 1 <= this.dim - 1 && i + 1 <= this.dim - 1)
                    if (this.maze[i+1][j-1].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i+1][j-1].getId(), 1);

                //se esiste la Box inferiore e se non e' un muro -> aggiungi l'arco corrispondente
                 if (i + 1 <= this.dim - 1 && j <= this.dim - 1)
                    if (this.maze[i+1][j].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i+1][j].getId(), 1);

                //se esiste la Box inferiore-dx e se non e' un muro -> aggiungi l'arco corrispondente
                 if (i + 1 <= this.dim - 1 && j + 1 <= this.dim - 1)
                    if (this.maze[i+1][j+1].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(),this.maze[i+1][j+1].getId(), 1);

                //se esiste la Box laterale-dx e se non e' un muro -> aggiungi l'arco corrispondente
                 if (i <= this.dim - 1 && j + 1 <= this.dim - 1)
                    if (maze[i][j+1].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i][j+1].getId(), 1);

                //se esiste la Box superiore-dx e se non e' un muro -> aggiungi l'arco corrispondente
                 if (i - 1 <= this.dim - 1 && j + 1 <= this.dim - 1 && i - 1 >= 0)
                    if (maze[i-1][j+1].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i-1][j+1].getId(), 1);

                //se esiste la Box superiore e se non e' un muro -> aggiungi l'arco corrispondente
                 if (i - 1 >= 0 && i - 1 <= this.dim - 1 && j <= this.dim - 1)
                    if (maze[i-1][j].getValue() != ValueBox.WALL)
                        this.addVertices(this.maze[i][j].getId(), this.maze[i-1][j].getId(), 1);
            }
        }
    }
}
