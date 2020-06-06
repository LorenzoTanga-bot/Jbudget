package it.unicam.cs.pa.jbudget104953.controller;

import java.util.ArrayList;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;

/**
 * Questa interfaccia definisce il controllore gruppo / famiglia
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface ControllerGroupInterface {

    /**
     * imposta il gruppo da controllare
     * 
     * @param grupo
     * @return true se l'operazione è andata a buon fine
     */
    public boolean setGroup(GroupInterface group);

    /**
     * restituisce il gruppo controllato
     * 
     * @return gruppo controllato
     */
    public GroupInterface getGroup();

    /**
     * restituisce l'ID del gruppo
     * 
     * @return ID del gruppo
     */
    public int getID();

    /**
     * restituisce il bilancio del gruppo
     * 
     * @return bilancio
     */
    public double getBalance();

    /**
     * aggiunge un account al gruppo
     * 
     * @param info Map con le informazioni proveninti dall'interfaccia con l'utente
     * @return true se l'operazione è andata a buon fine
     */
    public boolean addAccount(Map<String, String> info);

    /**
     * rimuove un account dalla collezione
     * 
     * @param ID id dell'account
     * @return true se l'operazione è andat a buon fine
     */
    public boolean removeAccount(int ID);

    /**
     * restituisce la collezione di tutti gli account presenti nel gruppo
     * 
     * @return collezione degli account
     */
    public ArrayList<AccountInterface> getAccounts();

    /**
     * restituisce un account avente l'ID passato per parametro
     * 
     * @param ID ID del account cercato nella collezione
     * @return l'account se è presente nella collezione altrimenti null
     */
    public AccountInterface getAccount(int ID);

    public String toString();

}