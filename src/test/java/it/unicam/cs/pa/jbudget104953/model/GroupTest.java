package it.unicam.cs.pa.jbudget104953.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GroupTest {
    int index = 0;
    String name = "Lorenzo";
    String surname = "Tanganelli";
    String description = "NIL";
    ArrayList<AccountInterface> accountArray = new ArrayList<>();
    GroupInterface group = new Group();

    void addAccount() {
        for (int i = 0; i < 3; i++) {
            AccountInterface account = new Account(name + i, surname + i, description + i);
            accountArray.add(account);
            group.addAccount(account);
        }
    }

    void removeAccount() {
        group.removeAccount(accountArray.get(index));
        accountArray.remove(index++);
    }

    @Test
    public void groupTest() {
        addAccount();
        assertEquals(group.getAccounts(), accountArray);
        removeAccount();
        assertEquals(group.getAccounts(), accountArray);

    }

}