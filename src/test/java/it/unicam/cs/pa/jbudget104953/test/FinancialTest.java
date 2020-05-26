package it.unicam.cs.pa.jbudget104953.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget104953.model.Account;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ID.IDFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Financial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Scheduled;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Tag;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

public class FinancialTest {

        @Test
        final void createFinancial() {
                AccountInterface account = new Account("Lorenzo", "Tanganelli", "NIL");
                GregorianCalendar date = new GregorianCalendar(1, 1, 2020);
                ArrayList<TagInterface> tag = new ArrayList<>() {

                        private static final long serialVersionUID = 1L;

                        {
                                add(new Tag("TAG1"));
                                add(new Tag("TAG2"));
                                add(new Tag("TAG3"));
                        }
                };

                DirectorFinancial.getInstance().makeExpense("description", 10, date, tag, account, date);
                assertTrue(DirectorFinancial.getInstance().getResult()
                                .equals(new Financial(IDFinancial.getInstance().getID() - 1, "description",
                                                TypeMovement.EXPENSE, TypePayment.CREDIT, -10, date, tag, account,
                                                new Scheduled(date))));

                DirectorFinancial.getInstance().makeExpense("description", 10, date, tag, account, null);
                assertTrue(DirectorFinancial.getInstance().getResult()
                                .equals(new Financial(IDFinancial.getInstance().getID() - 1, "description",
                                                TypeMovement.EXPENSE, TypePayment.DEBIT, -10, date, tag, account,
                                                null)));

                DirectorFinancial.getInstance().makeRevenue("description", 10, date, tag, account, date);
                assertTrue(DirectorFinancial.getInstance().getResult()
                                .equals(new Financial(IDFinancial.getInstance().getID() - 1, "description",
                                                TypeMovement.REVENUE, TypePayment.CREDIT, 10, date, tag, account,
                                                new Scheduled(date))));

                DirectorFinancial.getInstance().makeRevenue("description", 10, date, tag, account, date);
                assertTrue(DirectorFinancial.getInstance().getResult()
                                .equals(new Financial(IDFinancial.getInstance().getID() - 1, "description",
                                                TypeMovement.REVENUE, TypePayment.CREDIT, 10, date, tag, account,
                                                new Scheduled(date))));
        }

}