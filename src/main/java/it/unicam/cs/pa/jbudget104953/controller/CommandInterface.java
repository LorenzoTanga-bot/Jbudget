package it.unicam.cs.pa.jbudget104953.controller;

import java.util.function.Consumer;

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