package it.unicam.cs.pa.jbudget104953.model.ID;

/**
 * Quest’interfaccia è implementata dalle classi che sono responsabili della
 * gestione e di assegnare l’ID
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 * 
 */
public interface IDInterface {

	/**
	 * setta il valore di partenza di tale id
	 * 
	 * @param i
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean setID(int i);

	/**
	 * restituisce l'ID da assegnare ad un nuovo elemento
	 * 
	 * @return ID
	 */
	public int getID();
}
