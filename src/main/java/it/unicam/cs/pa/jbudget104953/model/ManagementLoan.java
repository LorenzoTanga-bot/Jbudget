package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.unicam.cs.pa.jbudget104953.model.ID.IDManagement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.LoanInterface;

public class ManagementLoan implements ManagementInterface<LoanInterface> {
	private final int ID;
	private final String type = "LOAN";
	private String description;
	private ArrayList<LoanInterface> loanArray;
	private double balance;

	public ManagementLoan(String description) {
		this(IDManagement.getInstance().getID(), new ArrayList<LoanInterface>(), description);
	}

	public ManagementLoan(int ID, ArrayList<LoanInterface> debtArray, String description) {
		this.ID = ID;
		this.description = description;
		this.loanArray = debtArray;
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
	public boolean addElement(Object element) {
		if (element instanceof LoanInterface)
			return addElementHelper((LoanInterface) element);
		return false;
	}

	private boolean addElementHelper(LoanInterface element) {
		boolean add = loanArray.add(element);
		if (add) {
			balance = loanArray.parallelStream().mapToDouble(x -> x.getBalance()).sum();
			EventManager.getInstance().notify("ELEMENTS", this);
		}
		return add;
	}

	@Override
	public boolean removeElement(int id) {
		boolean remove = loanArray.removeIf(x -> x.getID() == id);
		if (remove) {
			balance = loanArray.parallelStream().mapToDouble(x -> x.getBalance()).sum();
			EventManager.getInstance().notify("ELEMENTS", this);
		}
		return remove;
	}

	@Override
	public ArrayList<LoanInterface> getAllElement() {
		return loanArray;
	}

	public ArrayList<LoanInterface> getAllElementFilter(Predicate<LoanInterface> predicate) {
		return loanArray.parallelStream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
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
		for (LoanInterface e : loanArray)
			string += "ID:" + e.getID() + "\t\tType: " + e.getTypeScope() + "\t\tAmount: " + e.getBalance() + "\n";

		return string;
	}

	@Override
	public LoanInterface getElement(int id) {
		for (LoanInterface e : loanArray) {
			if (e.getID() == id)
				return e;
		}
		return null;
	}

}
