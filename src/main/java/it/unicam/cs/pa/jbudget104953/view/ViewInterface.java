package it.unicam.cs.pa.jbudget104953.view;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;

public interface ViewInterface extends EventListener {

	public void hello();

	public int getID();

	public String menuGroup(GroupInterface group);

	public Map<String, String> addAccount();

	public String menuAccount(AccountInterface account);

	public Map<String, String> addManagement();

	public String menuManagement(ManagementInterface<?> management);

	public Map<String, String> newMovement();

	public String menuTagList();

	public Map<String, String> addTag();

	public void viewElement(Object element);

}
