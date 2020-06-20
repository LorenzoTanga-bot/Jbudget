package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

/**
 * Questa interfaccia definisce ua transazione.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface FinancialInterface extends Comparable<FinancialInterface> {

	/**
	 * restituisce l'ID della transazione
	 * 
	 * @return ID della transazione
	 */
	public int getID();

	/**
	 * restituisce la descrizione della transazione
	 * 
	 * @return descrizione della transazione
	 */
	public String getDescription();

	/**
	 * restituisce il tipo di transazione (Expense o Revenu)
	 * 
	 * @return tipo di transazione
	 */
	public TypeFinancial getTypeFinancial();

	/**
	 * restituisce il tipo di pagamento (Debit o Credit)
	 * 
	 * @return descrizione il tipo di pagamento
	 */
	public TypePayment getTypePayment();

	/**
	 * restituisce la quantità
	 * 
	 * @return quantità
	 */
	public double getAmount();

	/**
	 * restituisce la data di quanto viene creata la transazione
	 * 
	 * @return data di creazione
	 */
	public GregorianCalendar getDate();

	/**
	 * restituisce la lista dei tag
	 * 
	 * @return tag associati
	 */
	public ArrayList<TagInterface> getTag();

	/**
	 * restituisce la data di quando verrà contabilizzata la transazione
	 * 
	 * @return data di contabilizzazione
	 */
	public ScheduledInterface getScheduled();
}
