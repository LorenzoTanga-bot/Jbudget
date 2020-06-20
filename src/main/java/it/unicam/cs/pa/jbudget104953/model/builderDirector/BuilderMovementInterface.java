package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

/**
 * Questa interfaccia impone le fasi di costruzione del prestito sia che da noi
 * verso qualcun'altro che il contrario
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 *
 */

public interface BuilderMovementInterface {

     /**
      * reset della costruzione del prestito
      * 
      * @return true se l'operazione è andata a buon fine
      */
     public boolean reset();

     /**
      * set del tipo di movimento
      * 
      * @param typeMovement
      */
     public boolean setTypeMovement(TypeMovement typeMovement);

     /**
      * set della prima transazione
      * 
      * @param initialTransaction movimento base della prima transazione
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setInitialTransaction(FinancialInterface initialTransaction);

     /**
      * set delle transazioni di rimborso
      * 
      * @param relatedTransaction lista delle transazioni di rimborso
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setRelatedTransaction(ArrayList<FinancialInterface> relatedTransaction);

     /**
      * set scopo della transazione
      * 
      * @param repaymentInstallments enum della motivazione della transazione
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
      * restituisci l'utlimo movimento creato
      * 
      * @return restituisce
      */
     public MovementInterface getResutl();

}
