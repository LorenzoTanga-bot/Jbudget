package it.unicam.cs.pa.jbudget104953.modelController;

import java.util.ArrayList;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;

/**
 * Questa interfaccia definisce il controntrollore di un account
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface ControllerAccountInterface {
    /**
     * imposta l'accaount da controllare
     * 
     * @param accout
     * @return true se l'operazione è andata a buon fine
     */
    public boolean setAccount(AccountInterface accout);

    /**
     * restituisce l'account controllato
     * 
     * @return account controllato
     */
    public AccountInterface getAccount();

    /**
     * restituisce l'ID dell'account
     * 
     * @return ID dell'account
     */
    public int getID();

    /**
     * restituisce il nome del titolare
     * 
     * @return nome del titolare
     */
    public String getName();

    /**
     * restituisce il cognome del titolare
     * 
     * @return cognome del titolare
     */
    public String getSurname();

    /**
     * restituisce la descrizione dell'account
     * 
     * @return descrizione dell'account
     */
    public String getDescription();

    /**
     * restituisce il bilancio esterno dell'account
     * 
     * @return bilancio esterno
     */
    public double getBalanceOutside();

    /**
     * restituisce il bilancio interno dell'account
     * 
     * @return bilancio interno
     */
    public double getBalanceInside();

    /**
     * aggiunge un gestore di transazioni alla collezione
     * 
     * @param info Map con le informazioni proveninti dall'interfaccia con l'utente
     * @return true se l'operazione è andata a buon fine
     */
    public boolean addManagement(Map<String, String> info);

    /**
     * rimuove un gestore di transazioni dalla collezione
     * 
     * @param ID id del gestore
     * @return true se l'operazione è andat a buon fine
     */
    public boolean removeManagement(int ID);

    /**
     * restituisce un gestore avente l'ID passato per parametro
     * 
     * @param id id del gestore cercato nella collezione
     * @return il gestore se è presente nella collezione altrimenti null
     */
    public ManagementInterface<?> getManagement(int ID);

    /**
     * restituisce una collezione di tutti i gestori di un tipo
     * 
     * @param type tipo di gestori richiesti
     * @return collezione con tutti i gestori di quel tipo
     */
    public ArrayList<ManagementInterface<?>> getManagement(TypeManagement type);

    /**
     * restituisce una collegione di tutti i gestori
     * 
     * @return collezione con tutti i gestori
     */
    public ArrayList<ManagementInterface<?>> getAllInOneManagement();

    /**
     * restituisce una collezione di tutti i gestori (indipendentemente dal tipo)
     * 
     * @return collezione con tutti i gestori
     */
    public Map<TypeManagement, ArrayList<ManagementInterface<?>>> getManagement();

    public String toString();
}