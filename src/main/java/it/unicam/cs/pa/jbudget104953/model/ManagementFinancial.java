package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.unicam.cs.pa.jbudget104953.model.ID.IDManagement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;

public class ManagementFinancial implements ManagementInterface<FinancialInterface> {
	private final int ID;
	private final String type = "FINANCIAL";
	private String description;
	private ArrayList<FinancialInterface> financialArray;
	private double balance;

	public ManagementFinancial(String description) {
		this(IDManagement.getInstance().getID(), new ArrayList<FinancialInterface>(), description);
	}

	public ManagementFinancial(int ID, ArrayList<FinancialInterface> financialArray, String description) {
		this.ID = ID;
		this.description = description;
		this.financialArray = financialArray;
		EventManager.getInstance("ELEMENTS");
		balance = 0;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean addElement(Object a) {
		if (a instanceof FinancialInterface)
			return addElementHelper((FinancialInterface) a);
		return false;
	}

	private boolean addElementHelper(FinancialInterface element) {
		boolean add = financialArray.add(element);
		if (add) {
			balance = financialArray.parallelStream().filter(
					x -> x.getScheduled() == null || (x.getScheduled() != null && x.getScheduled().isCompleted()))
					.mapToDouble(x -> x.getAmount()).sum();
			EventManager.getInstance().notify("ELEMENTS", this);
		}
		return add;
	}

	@Override
	public boolean removeElement(int id) {
		boolean remove = financialArray.removeIf(x -> x.getID() == id);
		if (remove) {
			balance = financialArray.parallelStream().filter(
					x -> x.getScheduled() == null || (x.getScheduled() != null && x.getScheduled().isCompleted()))
					.mapToDouble(x -> x.getAmount()).sum();
			EventManager.getInstance().notify("ELEMENTS", this);
		}
		return remove;
	}

	@Override
	public ArrayList<FinancialInterface> getAllElement() {
		return financialArray;
	}

	@Override
	public ArrayList<FinancialInterface> getAllElementFilter(Predicate<FinancialInterface> predicate) {
		return financialArray.parallelStream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public boolean subscribe(EventListener listener) {
		return EventManager.getInstance().subscribe("ELEMENTS", listener);
	}

	@Override
	public boolean unsubscribe(EventListener listener) {
		return EventManager.getInstance().unsubscribe("ELEMENTS", listener);
	}

	@Override
	public String toString() {
		String string = "ID management: " + getID() + "\t\tType:" + getType() + "\t\tBalance: " + getBalance() + "\n";
		for (FinancialInterface e : financialArray)
			string += "ID: " + e.getID() + "\t\tType: " + e.getTypeMovement() + " - " + e.getTypePayment()
					+ "\t\tAmount: " + e.getAmount() + "\n";
		return string;
	}

	@Override
	public FinancialInterface getElement(int ID) {
		for (FinancialInterface e : financialArray) {
			if (e.getID() == ID)
				return e;
		}
		return null;
	}
}
