package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

/**
 * Questa interfaccia definisce tutti i modi possibili per configurare un
 * movimento.(Es. Expense, Revenue)
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 *
 */

public interface BuilderFinancialInterface {

     /**
      * reset della costruzione del movimento
      * 
      * @return true se l'operazione è andata a buon fine
      */
     public boolean reset();

     /**
      * set di description
      * 
      * @param description Stringa che rappresenta la descrizione del
      *                    movimentobancario
      * @throws NullPointerException se il parametro passato è nil
      * @return true se l'operazione è andata a buon fine
      */
     public boolean setDescription(String description);

     /**
      * set di TypeMovement
      * 
      * @param type enumerable che identifica se è una spesa o un introito
      * @throws NullPointerException se il parametro passato è nil
      * @return true se l'operazione è andata a buon fine
      */

     public boolean setTypeMovement(TypeMovement type);

     /**
      * set di TypePayment
      * 
      * @param type enumerable che identifica se è un debito o un credito
      * @return true se l'operazione è andata a buon fine
      */

     public boolean setTypePayment(TypePayment type);

     /**
      * set di amount
      * 
      * @param amount numero a virgola mobile che quantifica la spesa
      * @return true se l'operazione è andata a buon fine
      */

     public boolean setAmount(double amount);

     /**
      * set di date
      * 
      * @param date Gregorian calendar che memorizza la data della transazione
      * @throws NullPointerException se il parametro passato è uguale a 0
      * @return true se l'operazione è andata a buon fine
      */

     public boolean setDate(GregorianCalendar date);

     /**
      * set di tag
      * 
      * @param amount lista di tag che caraterizzano il movimento
      * @throws NullPointerException se il parametro passato è nil
      * @return true se l'operazione è andata a buon fine
      */

     public boolean setTag(ArrayList<TagInterface> tag);

     /**
      * set di Scheduled
      * 
      * @param scheduled se il movimento è un credito allora viene settato un'oggetto
      *                  che rappresenza la data esatta della transazione
      * @return true se l'operazione è andata a buon fine
      */

     public boolean setScheduled(ScheduledInterface scheduled);

     /**
      * get di Financial
      * 
      * @return FinancialInterface restituisce il movimento in base ai set
      *         precedentemente fatti
      */
     public FinancialInterface getResult();
}
