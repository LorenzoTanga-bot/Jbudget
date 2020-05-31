package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

/**
 * Questa interfaccia definisce l'ordine delle fasi di costruzione di un
 * prestito. Il direttore funziona con qualsiasi istanza che estende
 * LoanBuilderInterface che il codice client gli passa.
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface DirectorLoanInterface {

	/**
	 * set del builder
	 * 
	 * @param loanBuilder builder
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean setLoanBuilder(BuilderLoanInterface loanBuilder);

	/**
	 * Costruzione di un prestito per beni
	 * 
	 * @param initialTransaction    transazione iniziale
	 * @param repaymentInstallments rate del rimborso
	 * @param secondAccount         account con cui si condivide la transazione
	 * @param ratio                 tasso di interesse su ogni rata di rimborso
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeConsumer(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repaymentInstallments, double ratio);

	/**
	 * Costruzione di un prestito per liquidità
	 * 
	 * @param initialTransaction    transazione iniziale
	 * @param repaymentInstallments rate del rimborso
	 * @param secondAccount         account con cui si condivide la transazione
	 * @param ratio                 tasso di interesse su ogni rata di rimborso
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean makeLiquid(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repaymentInstallments, double ratio);

	/**
	 * resittuisce l'ultimo prestito creato
	 * 
	 * @return LoanIntrface
	 */
	public LoanInterface getResult();
}
