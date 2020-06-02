package it.unicam.cs.pa.jbudget104953.controller;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementFinancial;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementLoan;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;

public class ControllerAccount {
    private AccountInterface account;

    public boolean setAccount(AccountInterface accout) {
        this.account = accout;
        return true;
    }

    public AccountInterface getAccount() {
        return account;
    }

    public int getID() {
        return account.getID();
    }

    public double getBalanceOutside() {
        return account.getBalanceOutside();
    }

    public double getBalanceInside() {
        return account.getBalanceInside();
    }

    public boolean addManagement(Map<String, String> info) {
        switch (info.get("Type")) {
            case "FINANCIAL":
                switch (info.get("Shared")) {
                    case "Y":
                        return account.addManagement(TypeManagement.SHARED,
                                new ManagementFinancial(info.get("Description")));
                    case "N":
                        return account.addManagement(TypeManagement.UNSHARED,
                                new ManagementFinancial(info.get("Description")));
                }
            case "LOAN":
                switch (info.get("Shared")) {
                    case "Y":
                        return account.addManagement(TypeManagement.SHARED,
                                new ManagementLoan(info.get("Description")));
                    case "N":
                        return account.addManagement(TypeManagement.UNSHARED,
                                new ManagementLoan(info.get("Description")));
                }
        }
        return false;
    }

    public boolean removeManagement(int ID) {
        return account.removeManagement(account.getManagement(ID));
    }

    public ManagementInterface<?> getManagement(int ID) {
        return account.getManagement(ID);
    }

    public String toString() {
        return account.toString();
    }
}