package it.unicam.cs.pa.jbudget104953.controller.modelController;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;

public interface ControllerManagementInterface {
    /**
     * imposta il gestore da controllare
     * 
     * @param management
     * @return true se l'operazione è andata a buon fine
     */
    public boolean setManagement(ManagementInterface<?> management);

    /**
     * restituisce il gestore controllato
     * 
     * @return gestore controllato
     */
    public ManagementInterface<?> getManagement();

    /**
     * restituisce l'ID del gestore
     * 
     * @return ID del gestore
     */
    public int getID();

    /**
     * restituisce il bilancio del gestore
     * 
     * @return bilancio
     */
    public double getBalance();

    /**
     * restituisce la descrizione del gestore
     * 
     * @return
     */
    public String getDescription();

    /**
     * aggiunge elementi alla collezione
     * 
     * @param info Map con le informazioni proveninti dall'interfaccia con l'utente
     * @return true se l'operazione è andata a buon fine
     */
    public boolean addElement(Map<String, String> info);

    /**
     * rimuove un'elemento avente l'ID passato per parametro
     * 
     * @param ID id dell'elemento da rimuovere
     * @return true se l'operazione è andata a buon fine
     */
    public boolean removeElement(int ID);

    /**
     * restituisce un elmento avente l'ID passato per parametro
     * 
     * @param ID id
     * @return l'elemento se è presente nella collezione altrimenti null
     */
    public Object getElement(int ID);

    public String toString();
}