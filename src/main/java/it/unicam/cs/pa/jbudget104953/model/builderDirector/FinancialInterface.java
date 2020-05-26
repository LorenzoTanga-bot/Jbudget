package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

/**
 * Questa interfaccia definisce un movimento.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface FinancialInterface extends Comparable<FinancialInterface> {

	/**
	 * restituisce l'ID del movimento
	 * 
	 * @return ID del movimento
	 */
	public int getID();

	/**
	 * restituisce la descrizione del movimento
	 * 
	 * @return descrizione del movimento
	 */
	public String getDescription();

	/**
	 * restituisce il tipo di movimento (Expense o Revenu)
	 * 
	 * @return tipo di movimento
	 */
	public TypeMovement getTypeMovement();

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
	 * restituisce la data di quanto viene creato il movimento
	 * 
	 * @return descrizione del movimento
	 */
	public GregorianCalendar getDate();

	/**
	 * restituisce la lista dei tag
	 * 
	 * @return tag associati
	 */
	public ArrayList<TagInterface> getTag();

	/**
	 * retituisci l'account che ha creato la transazione
	 * 
	 * @return account
	 */
	public AccountInterface getAccount();

	/**
	 * restituisce la data di quando verrà contabilizzata la transazione
	 * 
	 * @return data di contabilizzazione
	 */
	public ScheduledInterface getScheduled();
}
