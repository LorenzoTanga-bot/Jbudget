package it.unicam.cs.pa.jbudget104953.controller.modelController;

import java.util.ArrayList;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;

public class ControllerAccount implements ControllerAccountInterface {
    private AccountInterface account;

    @Override
    public boolean setAccount(AccountInterface accout) {
        this.account = accout;
        return true;
    }

    @Override
    public AccountInterface getAccount() {
        return account;
    }

    @Override
    public int getID() {
        return account.getID();
    }

    @Override
    public String getName() {
        return account.getName();
    }

    @Override
    public String getSurname() {
        return account.getSurname();
    }

    @Override
    public String getDescription() {
        return account.getDescription();
    }

    @Override
    public double getBalanceOutside() {
        return account.getBalanceOutside();
    }

    @Override
    public double getBalanceInside() {
        return account.getBalanceInside();
    }

    @Override
    public boolean addManagement(Map<String, String> info) {
        switch (info.get("Shared")) {
            case "Y":
                return account.addManagement(TypeManagement.SHARED,
                        new ManagementMovement(info.get("Name"), info.get("Description")));
            case "N":
                return account.addManagement(TypeManagement.UNSHARED,
                        new ManagementMovement(info.get("Name"), info.get("Description")));
        }
        return false;
    }

    @Override
    public boolean removeManagement(int ID) {
        return account.removeManagement(getManagement(ID));
    }

    @Override
    public ManagementInterface<?> getManagement(int ID) {
        for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : getManagement().entrySet())
            for (ManagementInterface<?> management : e.getValue())
                if (management.getID() == ID)
                    return management;

        return null;
    }

    @Override
    public ArrayList<ManagementInterface<?>> getManagement(TypeManagement type) {
        return getManagement().get(type);
    }

    @Override
    public ArrayList<ManagementInterface<?>> getAllInOneManagement() {
        return new ArrayList<ManagementInterface<?>>() {
            private static final long serialVersionUID = 1L;

            {
                addAll(getManagement(TypeManagement.SHARED));
                addAll(getManagement(TypeManagement.UNSHARED));
            }
        };
    }

    @Override
    public Map<TypeManagement, ArrayList<ManagementInterface<?>>> getManagement() {
        return account.getManagement();
    }

    @Override
    public String toString() {
        return account.toString();
    }
}