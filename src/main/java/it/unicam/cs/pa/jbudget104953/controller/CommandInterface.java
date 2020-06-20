package it.unicam.cs.pa.jbudget104953.controller;

import java.util.function.Consumer;

/**
 * quest’interfaccia è implementata dalla classe responsabile del processing dei
 * comandi. Permette di associare delle stringhe a dei metodi ed eseguirli
 * quando gli viene passata la relativa stringa.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 * 
 * @param <T>
 */

public interface CommandInterface<T> {

    /**
     * processa un comando
     * 
     * @param command
     */
    public void processCommand(String command);

    /**
     * aggiunge un comando alla colezione
     * 
     * @param name    nome del comando
     * @param command operazione da eseguire
     * @return true se l'operazione è andata a buon fine
     */
    public boolean addCommand(String name, Consumer<T> command);
}