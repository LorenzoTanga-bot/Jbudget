package it.unicam.cs.pa.jbudget104953.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorMovement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Movement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class LoanTest {

    int ID = 0;
    TypeMovement type = TypeMovement.LOAN;
    String description = "NIL";
    double amount = 100;
    double ratio = 5;
    int numRate = 10;
    GregorianCalendar date = new GregorianCalendar(2020, 1, 1);
    ArrayList<TagInterface> tag = new ArrayList<>();
    GregorianCalendar dateScheduled = new GregorianCalendar(2021, 1, 1);

    void compare(MovementInterface movement, MovementInterface movementCompare) {
        assertEquals(movement.getType(), movementCompare.getType());
        assertEquals(movement.getAllTransaction(), movementCompare.getAllTransaction());
        assertEquals(movement.getRatio(), movementCompare.getRatio());
        assertEquals(movement.getTypeScope(), movementCompare.getTypeScope());
        assertEquals(movement.getBalance(), movementCompare.getBalance());
    }

    @Test
    void consumerTest() {
        TypeScope typeScope = TypeScope.CONSUMER_GOODS;

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
                    if (initialTransaction.getTypeFinancial() == TypeFinancial.EXPENSE)
                        DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        MovementInterface loan = new Movement(ID, type, initialTransaction, repaymentInstallments, typeScope, ratio);

        DirectorMovement.getInstance().makeLoanConsumer(initialTransaction, repaymentInstallments, ratio);
        MovementInterface loanCompare = DirectorMovement.getInstance().getResult();

        compare(loan, loanCompare);
    }

    @Test
    void liquidTest() {
        TypeScope typeScope = TypeScope.LIQUID_ASSETS;

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
                    if (initialTransaction.getTypeFinancial() == TypeFinancial.EXPENSE)
                        DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        MovementInterface loan = new Movement(ID, type, initialTransaction, repaymentInstallments, typeScope, ratio);

        DirectorMovement.getInstance().makeLoanLiquid(initialTransaction, repaymentInstallments, ratio);
        MovementInterface loanCompare = DirectorMovement.getInstance().getResult();

        compare(loan, loanCompare);
    }

}