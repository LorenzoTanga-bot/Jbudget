package it.unicam.cs.pa.jbudget104953.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;

public class AccountTest {
    int index = 0;
    String name = "Lorenzo";
    String surname = "Tanganelli";
    String description = "NIL";
    double balanceInside;
    double balanceOutside;
    Map<TypeManagement, ArrayList<ManagementInterface<?>>> managementMap = new HashMap<TypeManagement, ArrayList<ManagementInterface<?>>>() {
        private static final long serialVersionUID = 1L;
        {
            put(TypeManagement.SHARED, new ArrayList<>());
            put(TypeManagement.UNSHARED, new ArrayList<>());
        }
    };
    AccountInterface account = new Account(name, surname, description);

    void updateBalance() {
        balanceInside = 0;
        balanceOutside = 0;

        for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : managementMap.entrySet())
            for (ManagementInterface<?> management : e.getValue()) {
                balanceInside += management.getBalance();
                if (e.getKey() == TypeManagement.SHARED)
                    balanceOutside += management.getBalance();
            }
    }

    void compare() {
        updateBalance();
        assertEquals(account.getName(), name);
        assertEquals(account.getSurname(), surname);
        assertEquals(account.getDescription(), description);
        assertEquals(account.getBalanceInside(), balanceInside);
        assertEquals(account.getBalanceOutside(), balanceOutside);
        assertEquals(account.getManagement(), managementMap);

    }

    void addManagement() {
        for (int i = 0; i < 4; i++) {
            ManagementInterface<?> managementInterface = new ManagementMovement(name + i, description + i);
            if (i % 2 == 0) {
                managementMap.get(TypeManagement.SHARED).add(managementInterface);
                account.addManagement(TypeManagement.SHARED, managementInterface);
            } else {
                managementMap.get(TypeManagement.UNSHARED).add(managementInterface);
                account.addManagement(TypeManagement.UNSHARED, managementInterface);
            }

        }
    }

    void removeManagement() {
        account.removeManagement(managementMap.get(TypeManagement.SHARED).get(index));
        managementMap.get(TypeManagement.SHARED).remove(index);
    }

    @Test
    public void accountTest() {
        addManagement();
        compare();
        removeManagement();
        compare();
    }

}