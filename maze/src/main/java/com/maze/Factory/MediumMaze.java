package com.maze.Factory;

import com.maze.Interactors.ValueBox;

/**
 * Classe che implementa il labirinto medio.
 * Il labirinto medio è un labirinto con dimensione 12x12.
 * @see Maze
 */
public class MediumMaze extends Maze{
    
    /**
     * Costruttore della classe per passare la dimensione del labirinto.
     */
    public MediumMaze(){
        super(10); // chiama il costruttore della superclasse per passare la dimensione del labirinto
    }

    /**
     * Metodo che genera il labirinto.
     */
    public void generateMaze(){
        for(int i = getDim() - 1; i > 7;  i--){
            getBox(i, 7).setValue(ValueBox.WALL);
        }
        for(int i = 10; i < getDim() -2; i++){
            getBox(10, i).setValue(ValueBox.WALL);
        }
        for(int i = 6; i < getDim(); i++){
            getBox(3, i).setValue(ValueBox.WALL);
        }
    }
}