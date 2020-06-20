package it.unicam.cs.pa.jbudget104953.view;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;

/**
 * quest’interfaccia è implementata dalla classe responsabile dell’iterazione
 * tra utente e controller. Fornisce i metodi per visualizzare e ricevere
 * informazioni l’utente.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 * 
 */
public interface ViewInterface extends EventListener {

	/**
	 * metodo per la stampa di avvio applicazione
	 */
	public void hello();

	/**
	 * resituisce l'ID chiesto all'utente
	 * 
	 * @return ID
	 */
	public int getID();

	/**
	 * stampa il menu del gruppo e restituice il comando digitato dall'utente
	 * 
	 * @param group gruppo per cui viene costruito il menu
	 * @return comando dell'utente
	 */
	public String menuGroup(GroupInterface group);

	/**
	 * prende in input le informazioni digitate dall'utente per creare un nuovo
	 * account e li restiusce codifici all'interno di un hashmap
	 * 
	 * @return input utente
	 */
	public Map<String, String> addAccount();

	/**
	 * stampa il menu dell'account e restiuisce il comando digitato dall'utente
	 * 
	 * @param account account per cui viene costurito il menu
	 * @return comando dell'utente
	 */
	public String menuAccount(AccountInterface account);

	/**
	 * prende in input le informazioni digitate dall'utente per creare un nuovo
	 * portafoglio e le restiusce codificate all'interno di un hashmap
	 * 
	 * @return input utente
	 */
	public Map<String, String> addManagement();

	/**
	 * stampa il menu del portagolio e restiuisce il comando digitato dall'utente
	 * 
	 * @param management portafoglio per cui viene costurito il menu
	 * @return comando dell'utente
	 */
	public String menuManagement(ManagementInterface<?> management);

	/**
	 * prende in input le informaizoni digitate dall'utente per creare un nuovo
	 * movimento e le restiusce codificate all'interno di un hashmap
	 * 
	 * @return input utente
	 */
	public Map<String, String> newMovement();

	/**
	 * stampa il menu dei tag e restiuisce il comando digitato dall'utente
	 * 
	 * @return comando dell'utente
	 */
	public String menuTagList();

	/**
	 * prende in input le informaizoni digitate dall'utente per creare un nuovo tag
	 * e le restiusce codificate all'interno di un hashmap
	 * 
	 * @return input utente
	 */
	public Map<String, String> addTag();

	/**
	 * stampa le informazioni dell'oggetto passato
	 * 
	 * @param element oggetto da stampare
	 */
	public void viewElement(Object element);

}
