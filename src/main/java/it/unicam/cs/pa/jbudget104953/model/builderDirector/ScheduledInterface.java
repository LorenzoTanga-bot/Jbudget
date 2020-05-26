package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.GregorianCalendar;

/**
 * Questa interfaccia definisce come programmare un movimento
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface ScheduledInterface {

	/**
	 * restituisce la data di quando verà contabilizzato il movimento
	 * 
	 * @return data di quando avverà la contabilizzazione
	 */
	public GregorianCalendar getDate();

	/**
	 * 
	 * @return true se è stato contabilizzato il movimento
	 */
	public boolean isCompleted();

}
