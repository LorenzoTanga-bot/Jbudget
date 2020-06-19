package it.unicam.cs.pa.jbudget104953.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorMovement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;

public class ManagementMovementTest {
    String name = "Lorenzo";
    String description = "NIL";
    ArrayList<MovementInterface> movementArray = new ArrayList<>();
    double balance;
    double amount = 100;
    GregorianCalendar date = new GregorianCalendar(2020, 1, 1);
    ArrayList<TagInterface> tag = new ArrayList<>();
    GregorianCalendar dateScheduled = new GregorianCalendar(2021, 1, 1);
    ArrayList<FinancialInterface> relatedTransaction = null;
    ManagementInterface<MovementInterface> management = new ManagementMovement(name, description);

    ArrayList<FinancialInterface> getAllTransaction() {
        return new ArrayList<>() {
            private static final long serialVersionUID = 1L;

            {
                for (MovementInterface movementInterface : movementArray) {
                    addAll(movementInterface.getAllTransaction());
                }
            }
        };
    }

    <T> void compare() {
        assertEquals(management.getAllTransaction(), getAllTransaction());
        assertEquals(management.getBalance(), movementArray.parallelStream().mapToDouble(x -> x.getBalance()).sum());
        assertEquals(management.getDescription(), description);
        assertEquals(management.getName(), name);
    }

    void addMovement() {
        for (int i = 0; i < 3; i++) {
            DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
            FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();
            relatedTransaction = new ArrayList<FinancialInterface>() {
                private static final long serialVersionUID = 1L;
                {
                    GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                    for (int i = 0; i < 3; i++) {
                        date.add(GregorianCalendar.DAY_OF_YEAR, 1);
                        DirectorFinancial.getInstance().makeExpense("", amount, (GregorianCalendar) date.clone(),
                                initialTransaction.getTag(), (GregorianCalendar) date.clone());

                        add(DirectorFinancial.getInstance().getResult());
                    }
                }
            };

            DirectorMovement.getInstance().makeMultiMovement(initialTransaction, relatedTransaction);
            MovementInterface movement = DirectorMovement.getInstance().getResult();
            movementArray.add(movement);
            management.addElement(movement);
        }
    }

    @Test
    public void managementMovementTest() {
        addMovement();
        compare();
        management.removeElement(movementArray.get(movementArray.size() - 1).getID());
        movementArray.remove(movementArray.get(movementArray.size() - 1));
        compare();

    }

}