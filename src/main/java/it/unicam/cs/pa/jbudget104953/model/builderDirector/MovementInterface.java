package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

/**
 * Questa interfaccia definisce un prestito.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface MovementInterface extends Comparable<MovementInterface> {

	/**
	 * restituisce l'ID del prestito
	 * 
	 * @return ID del prestito
	 */
	public int getID();

	/**
	 * restituisce il tipo di movimento
	 * 
	 * @return tipo di movimento
	 */
	public TypeMovement getType();

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
	public ArrayList<FinancialInterface> getRelatedTransaction();

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
