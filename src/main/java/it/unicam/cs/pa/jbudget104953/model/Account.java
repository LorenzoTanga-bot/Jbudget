package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.ID.IDAccount;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;

public class Account implements AccountInterface {

	private final int ID;
	private String name;
	private String surname;
	private String description;
	private double balanceInside;
	private double balanceOutside;
	private Map<TypeManagement, ArrayList<ManagementInterface<?>>> managementMap;

	public Account(int ID, String name, String surname, String description) {
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.balanceInside = 0;
		this.balanceOutside = 0;
		EventManager.getInstance("ACCOUNTS");
		this.managementMap = new HashMap<TypeManagement, ArrayList<ManagementInterface<?>>>() {
			private static final long serialVersionUID = 1L;
			{
				put(TypeManagement.SHARED, new ArrayList<>());
				put(TypeManagement.UNSHARED, new ArrayList<>());
			}
		};
	}

	public Account(String name, String surname, String description) {
		this(IDAccount.getInstance().getID(), name, surname, description);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public double getBalanceInside() {
		return balanceInside;
	}

	@Override
	public double getBalanceOutside() {
		return balanceOutside;
	}

	@Override
	public Map<TypeManagement, ArrayList<ManagementInterface<?>>> getManagement() {
		return managementMap;
	}

	@Override
	public boolean subscribe(EventListener listener) {
		return EventManager.getInstance().subscribe("ACCOUNTS", listener);
	}

	@Override
	public boolean unsubscribe(EventListener listener) {
		return EventManager.getInstance().unsubscribe("ACCOUNTS", listener);
	}

	@Override
	public boolean addManagement(TypeManagement type, ManagementInterface<?> management) {
		ArrayList<ManagementInterface<?>> managementArray = managementMap.get(type);
		boolean add = managementArray.add(management);
		if (add) {
			management.subscribe(this);
			EventManager.getInstance().notify("ACCOUNTS", this);
			balance();
		}
		return add;
	}

	@Override
	public boolean removeManagement(ManagementInterface<?> management) {
		boolean remove = false;
		for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : managementMap.entrySet())
			if (e.getValue().remove(management))
				remove = true;
		if (remove) {
			management.unsubscribe(this);
			EventManager.getInstance().notify("ACCOUNTS", this);
			balance();
		}
		return remove;
	}

	@Override
	public String toString() {
		String string = "ID account: " + getID() + "\t\tBalance inside: " + getBalanceInside() + " - Balance outside: "
				+ getBalanceOutside() + "\n";
		for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : managementMap.entrySet())
			for (ManagementInterface<?> management : e.getValue())
				string += "Type: " + e.getKey() + "\tID: " + management.getID() + "\tDescription: "
						+ management.getDescription() + "\tBalance: " + management.getBalance() + "\n";
		return string;
	}

	private void balance() {
		balanceInside = 0;
		balanceOutside = 0;

		for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : managementMap.entrySet())
			for (ManagementInterface<?> management : e.getValue()) {
				balanceInside += management.getBalance();
				if (e.getKey() == TypeManagement.SHARED)
					balanceOutside += management.getBalance();
			}
	}

	@Override
	public void update(Object objects) {
		for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : managementMap.entrySet())
			if (e.getValue().contains(objects)) {
				balance();
				EventManager.getInstance().notify("ACCOUNTS", this);
			}

	}

}
