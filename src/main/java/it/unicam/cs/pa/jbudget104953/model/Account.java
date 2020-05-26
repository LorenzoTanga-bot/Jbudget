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

	public Account(int ID, String name, String surname, String description,
			Map<TypeManagement, ArrayList<ManagementInterface<?>>> managementArray) {
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.balanceInside = 0;
		this.balanceOutside = 0;
		EventManager.getInstance("ACCOUNTS");
		this.managementMap = managementArray;
	}

	public Account(String name, String surname, String description) {
		this(IDAccount.getInstance().getID(), name, surname, description,
				new HashMap<TypeManagement, ArrayList<ManagementInterface<?>>>() {

					private static final long serialVersionUID = 1L;

					{
						put(TypeManagement.SHARED, new ArrayList<>());
						put(TypeManagement.UNSHARED, new ArrayList<>());
					}
				});
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
	public ManagementInterface<?> getManagement(int ID) {
		for (Map.Entry<TypeManagement, ArrayList<ManagementInterface<?>>> e : managementMap.entrySet())
			for (ManagementInterface<?> management : e.getValue()) {
				if (management.getID() == ID) {
					return management;
				}
			}
		return null;
	}

	@Override
	public ArrayList<ManagementInterface<?>> getManagement(TypeManagement type) {
		return managementMap.get(type);
	}

	@Override
	public ArrayList<ManagementInterface<?>> getManagement() {
		return new ArrayList<ManagementInterface<?>>() {

			private static final long serialVersionUID = 1L;

			{
				addAll(getManagement(TypeManagement.SHARED));
				addAll(getManagement(TypeManagement.UNSHARED));
			}
		};
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
		}
		return remove;
	}

	@Override
	public String toString() {
		String string = "ID account: " + getID() + "\t\tBalance inside: " + getBalanceInside() + " - Balance outside: "
				+ getBalanceOutside() + "\n";
		for (ManagementInterface<?> e : getManagement())
			string += "ID: " + e.getID() + "\tType: " + e.getType() + "\tBalance: " + e.getBalance() + "\n";
		return string;
	}

	@Override
	public void update(Object objects) {
		if (getManagement().contains(objects)) {
			balanceInside = 0;
			balanceOutside = 0;
			for (ManagementInterface<?> e : getManagement())
				balanceInside += e.getBalance();
			for (ManagementInterface<?> e : getManagement(TypeManagement.SHARED))
				balanceOutside += e.getBalance();

			EventManager.getInstance().notify("ACCOUNTS", this);
		}
	}

}
