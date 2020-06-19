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
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class SingleTest {

    int ID = 0;
    TypeMovement type = TypeMovement.SINGLE;
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
    void singleTest() {

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        MovementInterface single = new Movement(ID, type, initialTransaction, relatedTransaction, typeScope, ratio);

        DirectorMovement.getInstance().makeSingleMovement(initialTransaction);
        MovementInterface singleCompare = DirectorMovement.getInstance().getResult();

        compare(single, singleCompare);
    }
}