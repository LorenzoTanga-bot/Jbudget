package it.unicam.cs.pa.jbudget104953.controller;

import java.util.ArrayList;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.Account;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;

public class ControllerGroup implements ControllerGroupInterface {

    private GroupInterface group = new Group();

    @Override
    public boolean setGroup(GroupInterface group) {
        this.group = group;
        return true;
    }

    @Override
    public GroupInterface getGroup() {
        return group;
    }

    @Override
    public int getID() {
        return group.getID();
    }

    @Override
    public double getBalance() {
        return group.getBalance();
    }

    @Override
    public boolean addAccount(Map<String, String> info) {
        return group.addAccount(new Account(info.get("Name"), info.get("Surname"), info.get("Description")));
    }

    @Override
    public boolean removeAccount(int ID) {
        return group.removeAccount(getAccount(ID));
    }

    @Override
    public ArrayList<AccountInterface> getAccounts() {
        return group.getAccounts();
    }

    @Override
    public AccountInterface getAccount(int ID) {
        for (AccountInterface a : getAccounts())
            if (a.getID() == ID)
                return a;

        return null;
    }

    @Override
    public String toString() {
        return group.toString();
    }

}