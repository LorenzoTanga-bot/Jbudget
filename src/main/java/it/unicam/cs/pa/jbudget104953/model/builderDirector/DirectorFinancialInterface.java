package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Questa interfaccia definisce l'ordine delle fasi di costruzione di un
 * movimento. Il direttore funziona con qualsiasi istanza che estende
 * FinancialBuilderInterface che il codice client gli passa.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface DirectorFinancialInterface {
	/**
	 * set del builder
	 * 
	 * @param financialBuilder builder
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean setFinancialBuilder(BuilderFinancialInterface financialBuilder);

	/**
	 * costruzione di una spesa
	 * 
	 * @param description descrizione
	 * @param amount      quantità
	 * @param date        data di quando viene creata la transazione
	 * @param tag         tag associati
	 * @param account     account
	 * @param scheduled   data di quando verrà contabilizzata la transazione
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeExpense(String description, double amount, GregorianCalendar date, ArrayList<TagInterface> tag,
			GregorianCalendar scheduled);

	/**
	 * costruzione di un introito
	 * 
	 * @param description descrizione
	 * @param amount      quantità
	 * @param date        data di quando viene creata la transazione
	 * @param tag         tag associati
	 * @param account     account
	 * @param scheduled   data di quando verrà contabilizzata la transazione
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeRevenue(String description, double amount, GregorianCalendar date, ArrayList<TagInterface> tag,
			GregorianCalendar scheduled);

	/**
	 * restituisce l'ultimo moviemento creato
	 * 
	 * @return FinancialInterface
	 */
	public FinancialInterface getResult();
}
