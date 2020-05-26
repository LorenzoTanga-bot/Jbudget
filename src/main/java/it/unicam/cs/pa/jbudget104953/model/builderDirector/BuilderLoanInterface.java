package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

/**
 * Questa interfaccia impone le fasi di costruzione del prestito sia che da noi
 * verso qualcun'altro che il contrario
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 *
 */

public interface BuilderLoanInterface {

     /**
      * reset della costruzione del prestito
      * 
      * @return true se l'operazione è andata a buon fine
      */
     public boolean reset();

     /**
      * set della prima transazione
      * 
      * @param initialTransaction movimento base della prima transazione
      * @throws NullPointerException se il parametro passato è nil
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setInitialTransaction(FinancialInterface initialTransaction);

     /**
      * set delle transazioni di rimborso
      * 
      * @param repaymentInstallments lista delle transazioni di rimborso
      * @throws NullPointerException se il parametro passato è nil
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setRepaymentInstallments(ArrayList<FinancialInterface> repaymentInstallments);

     /**
      * set dell'account con cui è condivisa la tansazione
      * 
      * @param secondAccount account con cui è condivisa la tansazione
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setSecondAccount(AccountInterface secondAccount);

     /**
      * set scopo della transazione
      * 
      * @param repaymentInstallments enum della motivazione della transazione
      * @throws NullPointerException se il parametro passato è nil
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setTypeScope(TypeScope typeScope);

     /**
      * set del tasso di interesse su ogni rimborso
      * 
      * @param ratio tasso di interesse
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setRatio(double ratio);

     /**
      * set delle transazioni di rimborso
      * 
      * @return restituisce
      */
     public LoanInterface getResutl();

}
