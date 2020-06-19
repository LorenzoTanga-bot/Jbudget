package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;
import java.util.function.Predicate;

import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;

public interface ManagementInterface<T> {

	/**
	 * restituisce l'ID del gestore
	 * 
	 * @return ID del gestore
	 */
	public int getID();

	/**
	 * restituisce il nome del gestore
	 * 
	 * @return nome del gestore
	 */
	public String getName();

	/**
	 * restituisce la descrizione del gestore
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * aggiunge elementi alla collezione
	 * 
	 * @param movementInterface elemento
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean addElement(Object movementInterface);

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
	public T getElement(int ID);

	/**
	 * restiuisce tutti gli elementi della collezione
	 * 
	 * @return tutti gli elementi della collezione
	 */
	public ArrayList<Object> getAllElement();

	/**
	 * restituisce la lista di tutti le transazioni (unione di initialTransaction e
	 * relatedTrnsaction)
	 * 
	 * @return transazioni
	 */
	public ArrayList<FinancialInterface> getAllTransaction();

	/**
	 * restituisce la lista di tutti le transazioni (unione di initialTransaction e
	 * relatedTrnsaction) filtrate secondo il predicato passato come parametro
	 * 
	 * @return transazioni
	 */
	public ArrayList<FinancialInterface> getAllTransaction(Predicate<FinancialInterface> predicate);

	/**
	 * restituisce il bilancio del gestore
	 * 
	 * @return bilancio
	 */
	public double getBalance();

	/**
	 * sottoscrive un osservatore all'istanza
	 * 
	 * @param listener osservatore
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean subscribe(EventListener listener);

	/**
	 * annulla l'iscrizione di un osservatore all'istanza
	 * 
	 * @param listener
	 * @return
	 */
	public boolean unsubscribe(EventListener listener);

	public String toString();
}
