package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

/**
 * Questa interfaccia definisce un prestito.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface LoanInterface extends Comparable<LoanInterface> {

	/**
	 * restituisce l'ID del prestito
	 * 
	 * @return ID del prestito
	 */
	public int getID();

	/**
	 * restituisce la transazione iniziale
	 * 
	 * @return movimento iniziale
	 */
	public FinancialInterface getInitialTransaction();

	/**
	 * restituisce la list delle rate di rimborso
	 * 
	 * @return rate del rimborso
	 */
	public ArrayList<FinancialInterface> getRepaymentInstallments();

	/**
	 * restituisce l'account con cui si condivide la transazione
	 * 
	 * @return account con cui si condivide la transazione
	 */
	public AccountInterface getSecondAccount();

	/**
	 * restituisce lo scopo della transazione (Consumer goods o Liquid assets)
	 * 
	 * @return scopo della transazione
	 */
	public TypeScope getTypeScope();

	/**
	 * restituisce il tasso di interesse su ogni rata di rimborso
	 * 
	 * @return tasso di interesse su ogni rata di rimborso
	 */
	public double getRatio();

	/**
	 * restituisce il bilancio del prestito
	 * 
	 * @return bilancio del prestito
	 */
	public double getBalance();
}
