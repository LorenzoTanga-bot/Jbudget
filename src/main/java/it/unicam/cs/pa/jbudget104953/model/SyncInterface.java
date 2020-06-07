package it.unicam.cs.pa.jbudget104953.model;

import java.io.IOException;

public interface SyncInterface {

    /**
     * metodo per il salvataggio delle informazioni
     * 
     * @param group GroupInterface da salvare
     * @param path  path di dove creare il file
     * @throws IOException
     */
    public void write(GroupInterface group, String path) throws IOException;

    /**
     * metodo per la lettura e la creazione delle informazioni salvate su file
     * 
     * @param path path di dove si trova il file
     * @return GroupInterface generato dal file
     * @throws IOException
     */
    public GroupInterface read(String path) throws IOException;
}