package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;

/**
 * Questa interfaccia definisce un account
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface AccountInterface extends EventListener {
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
	 * restituisce il bilancio interno dell'account
	 * 
	 * @return bilancio interno
	 */
	public double getBalanceInside();

	/**
	 * restituisce il bilancio esterno dell'account
	 * 
	 * @return bilancio esterno
	 */
	public double getBalanceOutside();

	/**
	 * aggiunge un gestore di transazioni alla collezione
	 * 
	 * @param type       tipo di gestore (condiviso o no)
	 * @param management gestore
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean addManagement(TypeManagement type, ManagementInterface<?> management);

	/**
	 * rimuove un gestore di transazioni dalla collezione
	 * 
	 * @param management gestore
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean removeManagement(ManagementInterface<?> management);

	/**
	 * restituisce una collezione di tutti i gestori (indipendentemente dal tipo)
	 * 
	 * @return map con tutti i gestori
	 */
	public Map<TypeManagement, ArrayList<ManagementInterface<?>>> getManagement();

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
