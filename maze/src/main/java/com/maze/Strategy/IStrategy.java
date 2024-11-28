package com.maze.Strategy;

import com.maze.Interactors.Box;

/**
 * Interfaccia per la strategia di movimento del microrobot.
 */
public interface IStrategy {
    public Integer nextMove(Box box);
}