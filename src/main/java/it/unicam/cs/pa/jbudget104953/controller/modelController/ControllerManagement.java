package it.unicam.cs.pa.jbudget104953.controller.modelController;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorLoan;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.LoanInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;

public class ControllerManagement implements ControllerManagementInterface {
    private ManagementInterface<?> management;

    @Override
    public boolean setManagement(ManagementInterface<?> management) {
        this.management = management;
        return true;
    }

    @Override
    public ManagementInterface<?> getManagement() {
        return management;
    }

    @Override
    public int getID() {
        return management.getID();
    }

    @Override
    public double getBalance() {
        return management.getBalance();
    }

    @Override
    public String getType() {
        return management.getType();
    }

    @Override
    public String getDescription() {
        return management.getDescription();
    }

    private FinancialInterface newFinancial(Map<String, String> info) {
        ArrayList<TagInterface> tagList = new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                if (!info.get("Tag").equals(""))
                    for (String tag : info.get("Tag").split(","))
                        add(TagList.getInstance().getTag(Integer.parseInt(tag)));
            }
        };

        GregorianCalendar scheduled = null;
        if (info.get("DateScheduled") != null) {
            String[] _scheduled = info.get("DateScheduled").split("/");
            scheduled = new GregorianCalendar(Integer.parseInt(_scheduled[2]), Integer.parseInt(_scheduled[1]),
                    Integer.parseInt(_scheduled[0]));
        }

        makeFinancial(info.get("TypeMovement"), info.get("Description"), Double.parseDouble(info.get("Amount")),
                new GregorianCalendar(), tagList, scheduled);
        return DirectorFinancial.getInstance().getResult();
    }

    private void makeFinancial(String typeMovement, String description, double amount, GregorianCalendar date,
            ArrayList<TagInterface> tagList, GregorianCalendar scheduled) {
        switch (typeMovement) {
            case "EXPENSE":
                DirectorFinancial.getInstance().makeExpense(description, amount, date, tagList, scheduled);
                break;
            case "REVENUE":
                DirectorFinancial.getInstance().makeRevenue(description, amount, date, tagList, scheduled);
                break;
        }
    }

    private LoanInterface newLoan(Map<String, String> info) {
        FinancialInterface initialTransaction = newFinancial(info);
        int numRate = Integer.parseInt(info.get("NumberRate"));
        double amount = (initialTransaction.getAmount() / numRate)
                + ((initialTransaction.getAmount() / numRate) * Double.parseDouble(info.get("Ratio")) / 100);

        ArrayList<FinancialInterface> repaymentInstallments = makeRepayment(initialTransaction, numRate, amount);
        MakeLoan(info.get("Scope"), initialTransaction, repaymentInstallments, Double.parseDouble(info.get("Ratio")));
        return DirectorLoan.getInstance().getResult();
    }

    private ArrayList<FinancialInterface> makeRepayment(FinancialInterface initialTransaction, int numRate,
            double amount) {
        return new ArrayList<>() {
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
    }

    private void MakeLoan(String scope, FinancialInterface initialTransaction,
            ArrayList<FinancialInterface> repaymentInstallments, double ratio) {
        switch (scope) {
            case "LIQUID":
                DirectorLoan.getInstance().makeLiquid(initialTransaction, repaymentInstallments, ratio);
                break;
            case "CONSUMER":
                DirectorLoan.getInstance().makeConsumer(initialTransaction, repaymentInstallments, ratio);
                break;
        }

    }

    @Override
    public boolean addElement(Map<String, String> info) {
        if (management.getType().equals("FINANCIAL"))
            return management.addElement(newFinancial(info));
        else
            return management.addElement(newLoan(info));
    }

    @Override
    public boolean removeElement(int ID) {
        return management.removeElement(ID);
    }

    @Override
    public Object getElement(int ID) {
        return management.getElement(ID);
    }

    @Override
    public String toString() {
        return management.toString();
    }

}