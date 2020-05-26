package it.unicam.cs.pa.jbudget104953.model;

/**
 * Questa interfaccia definisce i metodi di sottoscrizione e notifica
 * dell'osservato
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface EventManagerInterface {

	/**
	 * aggiunge una lista di operazioni / tipi di eventi
	 * 
	 * @param operations lista di operazioni
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean addEvents(String... operations);

	/**
	 * sottoscrive un osservatore all'operazione
	 * 
	 * @param eventType evento
	 * @param listener  osservatore
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean subscribe(String eventType, EventListener listener);

	/**
	 * annulla l'iscrizione di un osservatore dall'operazione
	 * 
	 * @param eventType evento
	 * @param listener  osservatore
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean unsubscribe(String eventType, EventListener listener);

	/**
	 * notifaca il cambiamento di stato a tutti gli iscritti a quell'evento
	 * 
	 * @param eventType evento
	 * @param onObjects istanza che ha cambiato stato
	 * @return
	 */
	public boolean notify(String eventType, Object objects);
}
