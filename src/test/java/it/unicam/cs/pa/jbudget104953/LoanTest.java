package it.unicam.cs.pa.jbudget104953;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorLoan;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Loan;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.LoanInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class LoanTest {

    int ID = 0;
    String description = "NIL";
    double amount = 100;
    double ratio = 5;
    int numRate = 10;
    GregorianCalendar date = new GregorianCalendar(2020, 1, 1);
    ArrayList<TagInterface> tag = new ArrayList<>();
    GregorianCalendar dateScheduled = new GregorianCalendar(2021, 1, 1);

    void compare(LoanInterface loan, LoanInterface loanCompare) {
        assertEquals(loan.getInitialTransaction(), loanCompare.getInitialTransaction());
        assertEquals(loan.getRepaymentInstallments(), loan.getRepaymentInstallments());
        assertEquals(loan.getRatio(), loanCompare.getRatio());
        assertEquals(loan.getTypeScope(), loanCompare.getTypeScope());
        assertEquals(loan.getBalance(), loan.getBalance());
    }

    @Test
    void consumerExpenseTest() {
        TypeScope typeScope = TypeScope.CONSUMER_GOODS;

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
                    if (initialTransaction.getTypeMovement() == TypeMovement.EXPENSE)
                        DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        LoanInterface loan = new Loan(ID, initialTransaction, repaymentInstallments, typeScope, ratio);

        DirectorLoan.getInstance().makeConsumer(initialTransaction, repaymentInstallments, ratio);
        LoanInterface loanCompare = DirectorLoan.getInstance().getResult();

        compare(loan, loanCompare);
    }

    @Test
    void consumerRevenueTest() {
        TypeScope typeScope = TypeScope.CONSUMER_GOODS;

        DirectorFinancial.getInstance().makeRevenue(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
                    if (initialTransaction.getTypeMovement() == TypeMovement.EXPENSE)
                        DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        LoanInterface loan = new Loan(ID, initialTransaction, repaymentInstallments, typeScope, ratio);

        DirectorLoan.getInstance().makeConsumer(initialTransaction, repaymentInstallments, ratio);
        LoanInterface loanCompare = DirectorLoan.getInstance().getResult();

        compare(loan, loanCompare);
    }

    @Test
    void liquidExpenseTest() {
        TypeScope typeScope = TypeScope.LIQUID_ASSETS;

        DirectorFinancial.getInstance().makeExpense(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
                    if (initialTransaction.getTypeMovement() == TypeMovement.EXPENSE)
                        DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        LoanInterface loan = new Loan(ID, initialTransaction, repaymentInstallments, typeScope, ratio);

        DirectorLoan.getInstance().makeLiquid(initialTransaction, repaymentInstallments, ratio);
        LoanInterface loanCompare = DirectorLoan.getInstance().getResult();

        compare(loan, loanCompare);
    }

    @Test
    void liquidRevenueTest() {
        TypeScope typeScope = TypeScope.LIQUID_ASSETS;

        DirectorFinancial.getInstance().makeRevenue(description, amount, date, tag, dateScheduled);
        FinancialInterface initialTransaction = DirectorFinancial.getInstance().getResult();

        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
                    if (initialTransaction.getTypeMovement() == TypeMovement.EXPENSE)
                        DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
                                new ArrayList<>(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };

        LoanInterface loan = new Loan(ID, initialTransaction, repaymentInstallments, typeScope, ratio);

        DirectorLoan.getInstance().makeLiquid(initialTransaction, repaymentInstallments, ratio);
        LoanInterface loanCompare = DirectorLoan.getInstance().getResult();

        compare(loan, loanCompare);
    }

}