package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.ID.IDGroup;

public class Group implements GroupInterface {

	private final int ID;
	private ArrayList<AccountInterface> accountArray;
	private double balance;

	public Group(int ID, ArrayList<AccountInterface> accountArray) {
		this.ID = ID;
		this.accountArray = accountArray;
		EventManager.getInstance("GROUP");
	}

	public Group() {
		this(IDGroup.getInstance().getID(), new ArrayList<>());
	}

	@Override
	public void update(Object object) {
		if (accountArray.contains(object)) {
			balance = accountArray.parallelStream().mapToDouble(x -> x.getBalanceOutside()).sum();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public boolean addAccount(AccountInterface account) {
		boolean add = accountArray.add(account);
		if (add) {
			account.subscribe(this);
			EventManager.getInstance().notify("GROUP", this);
		}
		return add;
	}

	@Override
	public boolean removeAccount(AccountInterface account) {
		boolean remove = accountArray.remove(account);
		if (remove) {
			account.unsubscribe(this);
			EventManager.getInstance().notify("GROUP", this);
		}
		return remove;
	}

	@Override
	public ArrayList<AccountInterface> getAccounts() {
		return accountArray;
	}

	@Override
	public AccountInterface getAccount(int ID) {
		for (AccountInterface a : accountArray)
			if (a.getID() == ID)
				return a;

		return null;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public boolean subscribe(EventListener listener) {
		return EventManager.getInstance().subscribe("GROUP", listener);
	}

	@Override
	public boolean unsubscribe(EventListener listener) {
		return EventManager.getInstance().unsubscribe("GROUP", listener);
	}

	@Override
	public String toString() {
		String string = "ID group: " + getID() + "\t\tBalance: " + getBalance() + "\n\n";
		for (AccountInterface e : getAccounts()) {
			string += "ID: " + e.getID() + "\tName: " + e.getName() + " " + e.getSurname() + "\tBalance: "
					+ e.getBalanceOutside() + "\n";
		}
		return string;
	}

}
