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
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;

public class MultiTest {

    int ID = 0;
    TypeMovement type = TypeMovement.MULTI;
    String description = "NIL";
    double amount = 100;
    double ratio = 0;
    int numRate = 0;
    TypeScope typeScope = null;
    GregorianCalendar date = new GregorianCalendar(2020, 1, 1);
    ArrayList<TagInterface> tag = new ArrayList<>();
    ArrayList<FinancialInterface> relatedTransaction = null;
    GregorianCalendar dateScheduled = new GregorianCalendar(2021, 1, 1);

    void compare(MovementInterface movement, MovementInterface movementCompare) {
        assertEquals(movement.getType(), movementCompare.getType());
        assertEquals(movement.getAllTransaction(), movementCompare.getAllTransaction());
        assertEquals(movement.getRatio(), movementCompare.getRatio());
        assertEquals(movement.getTypeScope(), movementCompare.getTypeScope());
        assertEquals(movement.getBalance(), movementCompare.getBalance());
    }

    @Test
    void multiTest() {

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        relatedTransaction = new ArrayList<FinancialInterface>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.DAY_OF_YEAR, 1);
                    DirectorFinancial.getInstance().makeExpense("", amount, (GregorianCalendar) date.clone(),
                            initialTransaction.getTag(), (GregorianCalendar) date.clone());

                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        MovementInterface multi = new Movement(ID, type, initialTransaction, relatedTransaction, typeScope, ratio);

        DirectorMovement.getInstance().makeMultiMovement(initialTransaction, relatedTransaction);
        MovementInterface multiCompare = DirectorMovement.getInstance().getResult();

        compare(multi, multiCompare);
    }

}