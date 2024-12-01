package com.maze.Proxy;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaccia per la gestione dei file utilizzando il pattern Proxy
 * implementa le operazioni di lettura e scrittura di un file
 */
public interface IFile {

    /**
     * Metodo per la scrittura di un file
     * @return true se la scrittura e' andata a buon fine, false altrimenti
     * @throws IOException
     */
    public Boolean write() throws IOException;

    /**
     * Metodo per la lettura di un file
     * @return ArrayList<String[]> contenente le stringhe del file
     * nelle prime posizioni il nome e cognome del giocatore
     * @throws IOException
     */
    public ArrayList<String[]> read() throws IOException;
}
