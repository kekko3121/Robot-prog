package com.maze.Proxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Classe per la gestione del file della classifica
 * implementa le operazioni di lettura e scrittura del file
 * utilizzando il pattern Proxy
 */
public class Classification implements IFile {
    
    private File classification; // file contenente la classifica

    private String name; // nome del giocatore

    private String surname; // cognome del giocatore

    private String time; // tempo impiegato per completare il labirinto

    /**
     * Costruttore della classe per inizializzare il file della classifica
     * @param filename nome del file
     * @param name nome del giocatore
     * @param surname cognome del giocatore
     * @param time tempo impiegato per completare il labirinto
     * @throws IOException
     */
    public Classification(String filename, String name, String surname, String time) throws IOException{
        // Percorso del file per salvare la classifica
        Path path = Paths.get(System.getProperty("user.home"), "Documents", "RoboMazeScore"); 
        Files.createDirectories(path); // crea la directory se non esiste
        this.classification = new File(path + "/" + filename); // inizializzazione del file
        this.name = name; // inizializzazione del nome
        this.surname = surname; // inizializzazione del cognome
        this.time = time; // inizializzazione del tempo
    }

    /**
     * Costruttore per la lettura del file della classifica
     * @param filename nome del file
     * @throws IOException
     */
    public Classification(String filename) throws IOException{
        // Percorso del file per salvare la classifica
        Path path = Paths.get(System.getProperty("user.home"), "Documents", "RoboMazeScore"); 
        Files.createDirectories(path); // crea la directory se non esiste
        this.classification = new File(path + "/" + filename); // inizializzazione del file
    }

    /**
     * Metodo per aprire il file della classifica e se non esiste lo crea
     * @throws IOException
     */
    public void open() throws IOException{
        if(!this.classification.exists())
            this.classification.createNewFile();
    }

    /**
     * Metodo per la scrittura del file della classifica
     * @return true se la scrittura e' andata a buon fine, false altrimenti
     * @throws IOException
     */

    @Override
    public Boolean write() throws IOException {
        this.open(); // apre il file
        // legge il file e aggiunge il nome, cognome e tempo del giocatore
        ArrayList<String[]> lineFile = this.read();
        if (lineFile.size() < 1) { // se il file è vuoto
            
            // scrive il nome, cognome e tempo del giocatore
            try (BufferedWriter out = new BufferedWriter(new FileWriter(this.classification))) { 
                out.write(this.name + " " + this.surname + "," + this.time);
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else { // altrimenti controlla se il giocatore è già presente nella classifica
            Map<String, String> map = new HashMap<>();
            String[] a = new String[2];
            for (String[] s : lineFile) { // scorre la lista
                map.put(s[0], s[1]); // aggiunge il nome e il tempo alla mappa
                if (s[0].equals(name + " " + surname)) { // se il giocatore è già presente
                    a = s; // salva i dati del giocatore
                }
            }

            if (map.containsKey(name + " " + surname)) { // se il giocatore è già presente
                if (Integer.parseInt(map.get(name + " " + surname)) <= Integer.parseInt(time)) { // se il tempo è maggiore
                    return true; // non fare nulla
                } else { // altrimenti aggiorna il tempo
                    lineFile.remove(a); // togli il vecchio tempo dalla lista
                    int index = 0;
                    for (String[] s : lineFile) {
                        if (Integer.parseInt(s[1]) > Integer.parseInt(time)) // trova il nuovo posto in classifica
                            break;
                        index++;
                    }
                    a[1] = time;
                    lineFile.add(index, a); // aggiungi alla posizione giusta

                    // scrive la classifica aggiornata
                    try (BufferedWriter out = new BufferedWriter(new FileWriter(this.classification))) {
                        for (String[] s : lineFile) {
                            out.write(s[0] + "," + s[1]);
                            out.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                }
            } else { // altrimenti trova comunque il suo posto nella classifica
                int index = 0;
                for (String[] s : lineFile) {
                    if (Integer.parseInt(s[1]) > Integer.parseInt(time))
                        break;
                    index++;
                }
                a[0] = name + " " + surname;
                a[1] = time;
                lineFile.add(index, a);

                // scrive la classifica aggiornata
                try (BufferedWriter out = new BufferedWriter(new FileWriter(this.classification))) {
                    for (String[] s : lineFile) {
                        out.write(s[0] + "," + s[1]);
                        out.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
    }

    /**
     * Metodo per la lettura del file della classifica
     * @return ArrayList<String[]> contenente le stringhe del file
     * nelle prime posizioni il nome e cognome del giocatore
     * @throws IOException
     */
    @Override
    public ArrayList<String[]> read() throws IOException {
        this.open();
        BufferedReader reader;
        ArrayList<String[]> lineStrings = new ArrayList<>();
        String[] arraySplit;
        try {
            reader = new BufferedReader(new FileReader(this.classification));
            String line = reader.readLine();
            
            while (line != null) {
                if (!line.isBlank()) { // se la riga letta non è vuota / fatta solo di spazi
                    arraySplit = line.split(",");
                    lineStrings.add(arraySplit);
                }
                line = reader.readLine();
            }
            reader.close();
            return lineStrings;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineStrings;
    }
}
