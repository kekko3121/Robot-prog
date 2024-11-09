
# Labirinto-4

Progetto di programmazione 3. 

## Traccia

Si vuole sviluppare un algoritmo per il cammino di un robot in un labirinto. La stanza è pavimentata a tasselli quadrati (caselle) ed è dotata di pareti esterne e interne (vedi Figura 1 per un esempio). Il robot si può muovere nelle 8 direzioni adiacenti. Devono essere previsti almeno tre diversi scenari (livelli di difficoltà). Nella stanza sono presenti degli oggetti di diverso colore (red, green, yellow, cyan) che compaiono e scompaiono casualmente sul percorso. Il robot può assumere quattro stati pursuit, evade, flee, seek. Se il robot si trova in prossimità di un oggetto cambia stato secondo lo schema della Macchina a Stati Finiti di Figura 2.

![Figura_1](https://github.com/user-attachments/assets/65a7e023-0204-4241-be48-c2f893bd076a)

Per ogni stato le strategie sono:

 - pursuit e seek - va nella direzione dell’uscita di una singola cella alla volta (usare l’algoritmo di Dijkstra, vedi sotto)

 - flee - va nella direzione dell’uscita di due celle alla volta (usare l’algoritmo di Dijkstra, vedi sotto)

 - evade - avanza in maniera casuale di una singola cella

Scrivere un programma per la gestione del labirinto. Il programma deve permettere di registrare il robot con il suo nome e cognome e visualizzare, ad ogni inizio e fine partita, la classifica dei risultati migliori ottenuti, da tutti i robot, in tutte le partite (minore numero di passi per raggiungere l’uscita). Visualizzare il percorso del robot dopo ogni passo, mostrando la stanza, la sua posizione e quella degli oggetti.

![Figura_2](https://github.com/user-attachments/assets/dfec793b-ae05-4192-9345-3a66956916d1)

## Documentazione

Per consultare la documentazione andare al seguente link: [nome_link](link)

## Authors

- [@kekko3121](https://github.com/kekko3121)
- [@TonIann7](https://github.com/TonIann7)