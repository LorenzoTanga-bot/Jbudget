package it.unicam.cs.pa.jbudget104953.modelControl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.jbudget104953.model.Account;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;
import it.unicam.cs.pa.jbudget104953.modelController.ControllerAccount;
import it.unicam.cs.pa.jbudget104953.modelController.ControllerAccountInterface;

public class ControllerAccountTest {
    String name = "Lorenzo";
    String surname = "Taganelli";
    String description = "NIL";
    String shared = "Y";
    AccountInterface account = new Account(name, surname, description);
    ControllerAccountInterface controller = new ControllerAccount();

    void compare() {
        assertEquals(controller.getName(), account.getName());
        assertEquals(controller.getSurname(), account.getSurname());
        assertEquals(controller.getDescription(), account.getDescription());
        // TODO l'ID incasina il tutto.
        assertEquals(controller.getManagement(TypeManagement.SHARED),
                account.getManagement().get(TypeManagement.SHARED));
    }

    @Test
    public void controllerAccountTest() {
        controller.setAccount(new Account(name, surname, description));
        compare();

        controller.addManagement(new HashMap<String, String>() {
            private static final long serialVersionUID = 1L;
            {
                put("Shared", shared);
                put("Name", name);
                put("Description", description);

            }
        });

        account.addManagement(TypeManagement.SHARED, new ManagementMovement(name, description));
        compare();

    }

}