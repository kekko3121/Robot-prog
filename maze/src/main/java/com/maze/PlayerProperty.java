package com.maze;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe "Wrapper" dei valori dei player e del tempo trascorso, 
 * utile per consentirne la visualizzazione nelle TableViews*/
public class PlayerProperty {

    private SimpleStringProperty name; //contiene nome del player

    private SimpleStringProperty surname; //contiene cognome del player

    /**
     * Costruttore della classe PlayerProperty che inizializza i valori
     * @param name
     * @param surname
     */
    public PlayerProperty(String name, String surname){
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    /**
     * Metodo che restituisce il nome del player
     * @return
     */
    public String getPlayerName() {
        return name.get();
    }

    /**
     * Metodo che setta il nome del player
     * @param name
     */
    public void setPlayerName(String name) {
        this.name.set(name);
    }

    /**
     * Metodo che setta il cognome del player
     * @param surname
     */
    public void setPlayerSurname(String surname) {
        this.surname.set(surname);
    }

    /**
     * Metodo che restituisce il cognome del player
     * @return
     */
    public String getPlayerSurname() {
        return surname.get();
    }
}
