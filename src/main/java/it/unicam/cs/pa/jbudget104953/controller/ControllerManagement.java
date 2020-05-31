package it.unicam.cs.pa.jbudget104953.controller;

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

public class ControllerManagement {
    private ManagementInterface<?> management;

    public boolean setManagement(ManagementInterface<?> management) {
        this.management = management;
        return true;
    }

    public ManagementInterface<?> getManagement() {
        return management;
    }

    public int getID() {
        return management.getID();
    }

    public double getBalance() {
        return management.getBalance();
    }

    public String getType() {
        return management.getType();
    }

    private FinancialInterface newFinancial(Map<String, String> info) {
        String description = info.get("Description");
        double amount = Double.parseDouble(info.get("Amount"));
        ArrayList<TagInterface> tagArray = new ArrayList<>() {

            private static final long serialVersionUID = 1L;

            {
                if (info.get("Tag") != null)
                    for (String tag : info.get("Tag").split(","))
                        add(TagList.getInstance().getTag(tag));
            }
        };

        String[] scheduled = null;
        if (info.get("DateScheduled") != null)
            scheduled = info.get("DateScheduled").split("/");

        switch (info.get("TypeMovement")) {
            case "EXPENSE":
                if (info.get("DateScheduled") != null)
                    DirectorFinancial.getInstance().makeExpense(description, amount, new GregorianCalendar(), tagArray,
                            new GregorianCalendar(Integer.parseInt(scheduled[2]), Integer.parseInt(scheduled[1]),
                                    Integer.parseInt(scheduled[0])));
                else
                    DirectorFinancial.getInstance().makeExpense(description, amount, new GregorianCalendar(), tagArray,
                            null);
                break;

            case "REVENUE":
                if (info.get("DateScheduled") != null)
                    DirectorFinancial.getInstance().makeRevenue(description, amount, new GregorianCalendar(), tagArray,
                            new GregorianCalendar(Integer.parseInt(scheduled[2]), Integer.parseInt(scheduled[1]),
                                    Integer.parseInt(scheduled[0])));
                else
                    DirectorFinancial.getInstance().makeRevenue(description, amount, new GregorianCalendar(), tagArray,
                            null);
                break;
        }
        return DirectorFinancial.getInstance().getResult();
    }

    private LoanInterface newLoan(Map<String, String> info) {
        FinancialInterface initialTransaction = newFinancial(info);
        double ratio = Double.parseDouble(info.get("Ratio"));
        int numRate = Integer.parseInt(info.get("NumberRate"));
        double amount = (initialTransaction.getAmount() / numRate)
                + ((initialTransaction.getAmount() / numRate) * ratio / 100);

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

        switch (info.get("Scope")) {
            case "LIQUID":
                DirectorLoan.getInstance().makeLiquid(initialTransaction, repaymentInstallments, ratio);
                break;
            case "CONSUMER":
                DirectorLoan.getInstance().makeConsumer(initialTransaction, repaymentInstallments, ratio);
                break;
        }
        return DirectorLoan.getInstance().getResult();
    }

    public boolean addElement(Map<String, String> info) {
        if (management.getType().equals("FINANCIAL"))
            return management.addElement(newFinancial(info));
        else
            return management.addElement(newLoan(info));
    }

    public boolean removeElement(int ID) {
        return management.removeElement(ID);
    }

    public Object getElement(int ID) {
        return management.getElement(ID);
    }

}