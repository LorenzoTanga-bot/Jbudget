package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

/**
 * Questa interfaccia definisce l'ordine delle fasi di costruzione di un
 * prestito. Il direttore funziona con qualsiasi istanza che estende
 * LoanBuilderInterface che il codice client gli passa.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface DirectorMovementInterface {

	/**
	 * set del builder
	 * 
	 * @param loanBuilder builder
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean setMovementBuilder(BuilderMovementInterface loanBuilder);

	/**
	 * Costruzione di una singola transazione
	 * 
	 * @param initialTransaction transazione
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeSingleMovement(FinancialInterface initialTransaction);

	/**
	 * Costruzione di una transazione composta da più movimenti
	 * 
	 * @param initialTransaction transazione principale
	 * @param relatedTransaction transazioni collegate a quella principale
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeMultiMovement(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> relatedTransaction);

	/**
	 * Costruzione di una transazione ripetuta
	 * 
	 * @param initialTransaction  transazione iniziale
	 * @param repeatedTransaction transazioni seguenti
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeRepeatedMovement(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repeatedTransaction);

	/**
	 * Costruzione di un prestito per beni
	 * 
	 * @param initialTransaction    transazione iniziale
	 * @param repaymentInstallments rate del rimborso
	 * @param secondAccount         account con cui si condivide la transazione
	 * @param ratio                 tasso di interesse su ogni rata di rimborso
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeLoanConsumer(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> relatedTransaction, double ratio);

	/**
	 * Costruzione di un prestito per liquidità
	 * 
	 * @param initialTransaction    transazione iniziale
	 * @param repaymentInstallments rate del rimborso
	 * @param secondAccount         account con cui si condivide la transazione
	 * @param ratio                 tasso di interesse su ogni rata di rimborso
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeLoanLiquid(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> relatedTransaction, double ratio);

	/**
	 * resittuisce l'ultimo prestito creato
	 * 
	 * @return LoanIntrface
	 */
	public MovementInterface getResult();
}
