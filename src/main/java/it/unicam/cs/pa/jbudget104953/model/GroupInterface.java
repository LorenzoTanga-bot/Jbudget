package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;

/**
 * Questa interfaccia definisce un gruppo / famiglia
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface GroupInterface extends EventListener {
	/**
	 * restituisce l'ID del gruppo
	 * 
	 * @return ID
	 */
	public int getID();

	/**
	 * aggiunge un account al gruppo
	 * 
	 * @param account account
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean addAccount(AccountInterface account);

	/**
	 * rimuove un account al gruppo
	 * 
	 * @param account account
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean removeAccount(AccountInterface account);

	/**
	 * restituisce una collezione con tutti gli account del gruppo
	 * 
	 * @return collezione con tutti gli account
	 */
	public ArrayList<AccountInterface> getAccounts();

	/**
	 * restituisce un account avente l'ID passato per parametro
	 * 
	 * @param ID ID del account cercato nella collezione
	 * @return l'account se è presente nella collezione altrimenti null
	 */
	public AccountInterface getAccount(int ID);

	/**
	 * restituisce il bilancio del gruppo
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
