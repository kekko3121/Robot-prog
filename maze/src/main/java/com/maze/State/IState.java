package com.maze.State;

import com.maze.Interactors.Box;

/**
 * Interfaccia del pattern State per rappresentare uno stato del microrobot.
 */
public interface IState {
    Integer action(Box box); // Azione che il microrobot deve compiere nello stato attuale
}
