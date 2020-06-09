package it.unicam.cs.pa.jbudget104953;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Financial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Scheduled;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.ScheduledInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

class FinancialTest {
    int ID = 0;
    String description = "NIL";
    double amount = 10;
    GregorianCalendar date = new GregorianCalendar(2020, 1, 1);
    ArrayList<TagInterface> tag = new ArrayList<>();

    void compare(FinancialInterface financial, FinancialInterface financialCompare) {
        assertEquals(financial.getAmount(), financialCompare.getAmount());
        assertEquals(financial.getDate(), financialCompare.getDate());
        assertEquals(financial.getDescription(), financialCompare.getDescription());
        assertEquals(financial.getScheduled(), financialCompare.getScheduled());
        assertEquals(financial.getTag(), financialCompare.getTag());
        assertEquals(financial.getTypeMovement(), financialCompare.getTypeMovement());
        assertEquals(financial.getTypePayment(), financialCompare.getTypePayment());
    }

    @Test
    void expenseCreditTest() {
        TypeMovement movement = TypeMovement.EXPENSE;
        TypePayment payment = TypePayment.CREDIT;
        GregorianCalendar dateScheduled = new GregorianCalendar(2021, 1, 1);
        ScheduledInterface scheduled = new Scheduled(dateScheduled);

        FinancialInterface financial = new Financial(ID, description, movement, payment, -1 * amount, date, tag,
                scheduled);

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface financialCompare = DirectorFinancial.getInstance().getResult();

        compare(financial, financialCompare);
    }

    @Test
    void expenseDebtiTest() {
        TypeMovement movement = TypeMovement.EXPENSE;
        TypePayment payment = TypePayment.DEBIT;
        GregorianCalendar dateScheduled = null;
        ScheduledInterface scheduled = new Scheduled(dateScheduled);

        FinancialInterface financial = new Financial(ID, description, movement, payment, -1 * amount, date, tag,
                scheduled);

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface financialCompare = DirectorFinancial.getInstance().getResult();

        compare(financial, financialCompare);
    }

    @Test
    void revenueCreditTest() {
        TypeMovement movement = TypeMovement.REVENUE;
        TypePayment payment = TypePayment.CREDIT;
        GregorianCalendar dateScheduled = new GregorianCalendar(2021, 1, 1);
        ScheduledInterface scheduled = new Scheduled(dateScheduled);

        FinancialInterface financial = new Financial(ID, description, movement, payment, amount, date, tag, scheduled);

        DirectorFinancial.getInstance().makeRevenue(description, amount, date, tag, dateScheduled);
        FinancialInterface financialCompare = DirectorFinancial.getInstance().getResult();

        compare(financial, financialCompare);
    }

    @Test
    void revenueDebitTest() {
        TypeMovement movement = TypeMovement.REVENUE;
        TypePayment payment = TypePayment.DEBIT;
        GregorianCalendar dateScheduled = null;
        ScheduledInterface scheduled = new Scheduled(dateScheduled);

        FinancialInterface financial = new Financial(ID, description, movement, payment, amount, date, tag, scheduled);

        DirectorFinancial.getInstance().makeRevenue(description, amount, date, tag, dateScheduled);
        FinancialInterface financialCompare = DirectorFinancial.getInstance().getResult();

        compare(financial, financialCompare);
    }

}