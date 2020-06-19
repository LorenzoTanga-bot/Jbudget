package it.unicam.cs.pa.jbudget104953.modelController;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorMovement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
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
    public String getName() {
        return management.getName();
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
            scheduled = new GregorianCalendar(Integer.parseInt(_scheduled[2]), Integer.parseInt(_scheduled[1]) - 1,
                    Integer.parseInt(_scheduled[0]));

        }

        makeFinancial(info.get("TypeFinancial"), info.get("Description"), Double.parseDouble(info.get("Amount")),
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

    private MovementInterface newMovement(Map<String, String> info) {
        Map<String, String> infoInitialTransaction = new HashMap<>() {
            private static final long serialVersionUID = 1L;

            {
                put("TypeFinancial", info.get("TypeFinancial0"));
                put("Description", info.get("Description0"));
                put("Amount", info.get("Amount0"));
                put("Tag", info.get("Tag0"));
                put("DateScheduled", info.get("DateScheduled0"));
            }
        };
        FinancialInterface initialTransaction = newFinancial(infoInitialTransaction);
        TypeMovement typeMovement = TypeMovement.valueOf(info.get("TypeMovement"));

        switch (typeMovement) {
            case SINGLE:
                DirectorMovement.getInstance().makeSingleMovement(initialTransaction);
                break;
            case MULTI:
                ArrayList<FinancialInterface> relatedTransaction = makeRelatedTransaction(info);
                DirectorMovement.getInstance().makeMultiMovement(initialTransaction, relatedTransaction);
                break;
            case REPEATED:
                ArrayList<FinancialInterface> repeatedTransaction = makeRepeatedTransaction(initialTransaction, info);
                DirectorMovement.getInstance().makeRepeatedMovement(initialTransaction, repeatedTransaction);
                break;
            case LOAN:
                ArrayList<FinancialInterface> repaymentInstallments = makeRepaymentTransaction(initialTransaction,
                        info);
                MakeLoan(info.get("Scope"), initialTransaction, repaymentInstallments,
                        Double.parseDouble(info.get("Ratio")));
        }
        return DirectorMovement.getInstance().getResult();
    }

    private ArrayList<FinancialInterface> makeRepeatedTransaction(FinancialInterface initialTransaction,
            Map<String, String> info) {
        int numRate = Integer.parseInt(info.get("NumberMovement"));
        int day;
        if (info.get("Day") != null)
            day = Integer.parseInt(info.get("Day"));
        else
            day = 28;
        double amount = initialTransaction.getAmount();

        return new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.DAY_OF_YEAR, day);
                    if (initialTransaction.getTypeFinancial() == TypeFinancial.EXPENSE)
                        DirectorFinancial.getInstance().makeExpense("", amount, (GregorianCalendar) date.clone(),
                                initialTransaction.getTag(), (GregorianCalendar) date.clone());
                    else
                        DirectorFinancial.getInstance().makeRevenue("", amount, (GregorianCalendar) date.clone(),
                                initialTransaction.getTag(), (GregorianCalendar) date.clone());
                    add(DirectorFinancial.getInstance().getResult());
                }
            }
        };
    }

    private ArrayList<FinancialInterface> makeRelatedTransaction(Map<String, String> info) {
        ArrayList<FinancialInterface> relatedTransaction = new ArrayList<>();

        for (int i = 1; i < Integer.valueOf(info.get("NumberMovement")); i++) {

            final int x = i;

            Map<String, String> infoRelatedTransaction = new HashMap<>() {
                private static final long serialVersionUID = 1L;

                {
                    put("TypeFinancial", info.get("TypeFinancial" + x));
                    put("Description", info.get("Description" + x));
                    put("Amount", info.get("Amount" + x));
                    put("Tag", info.get("Tag" + x));
                    put("DateScheduled", info.get("DateScheduled" + x));
                }
            };

            relatedTransaction.add(newFinancial(infoRelatedTransaction));
        }

        return relatedTransaction;
    }

    private ArrayList<FinancialInterface> makeRepaymentTransaction(FinancialInterface initialTransaction,
            Map<String, String> info) {

        int numRate = Integer.parseInt(info.get("NumberMovement"));
        int day;
        if (info.get("Day") != null)
            day = Integer.parseInt(info.get("Day"));
        else
            day = 28;
        double amount = (initialTransaction.getAmount() / numRate)
                + ((initialTransaction.getAmount() / numRate) * Double.parseDouble(info.get("Ratio")) / 100);

        return new ArrayList<>() {
            private static final long serialVersionUID = 1L;
            {
                GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
                for (int i = 0; i < numRate; i++) {
                    date.add(GregorianCalendar.DAY_OF_YEAR, day);
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
    }

    private void MakeLoan(String scope, FinancialInterface initialTransaction,
            ArrayList<FinancialInterface> repaymentInstallments, double ratio) {
        switch (scope) {
            case "LIQUID":
                DirectorMovement.getInstance().makeLoanLiquid(initialTransaction, repaymentInstallments, ratio);
                break;
            case "CONSUMER":
                DirectorMovement.getInstance().makeLoanConsumer(initialTransaction, repaymentInstallments, ratio);
                break;
        }

    }

    @Override
    public boolean addElement(Map<String, String> info) {
        return management.addElement(newMovement(info));
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
    public ArrayList<FinancialInterface> getAllTransaction() {
        return management.getAllTransaction();
    }

    @Override
    public ArrayList<FinancialInterface> getAllTransactionFilterByTag(ArrayList<TagInterface> tagList) {
        return management.getAllTransaction(x -> x.getTag().containsAll(tagList));
    }

    @Override
    public ArrayList<Object> getAllElement() {
        return management.getAllElement();
    }

    @Override
    public String toString() {
        return management.toString();
    }
}