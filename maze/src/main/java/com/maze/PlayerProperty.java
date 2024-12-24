package com.maze;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe "Wrapper" dei valori dei player e del tempo trascorso, 
 * utile per consentirne la visualizzazione nelle TableViews*/
public class PlayerProperty {

    private SimpleStringProperty name; //contiene nome del player

    private SimpleStringProperty surname; //contiene cognome del player

    public PlayerProperty(String name, String surname){
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    public String getPlayerName() {
        return name.get();
    }

    public void setPlayerName(String name) {
        this.name.set(name);
    }

    public void setTime(String surname) {
        this.surname.set(surname);
    }

    public String getPlayerSurname() {
        return surname.get();
    }
}
