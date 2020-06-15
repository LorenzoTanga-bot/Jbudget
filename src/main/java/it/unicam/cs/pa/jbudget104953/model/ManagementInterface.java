package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;

public interface ManagementInterface<T> {

	/**
	 * restituisce l'ID del gestore
	 * 
	 * @return ID del gestore
	 */
	public int getID();

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
	public ArrayList<T> getAllElement();

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
