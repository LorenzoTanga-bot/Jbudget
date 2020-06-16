package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.ID.IDManagement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;

public class ManagementMovement implements ManagementInterface<MovementInterface> {
	private final int ID;
	private String name;
	private String description;
	private ArrayList<MovementInterface> movementArray;
	private double balance;

	public ManagementMovement(String name, String description) {
		this(IDManagement.getInstance().getID(), name, description);
	}

	public ManagementMovement(int ID, String name, String description) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.movementArray = new ArrayList<MovementInterface>();
		EventManager.getInstance("MANAGER");
		balance = 0;
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
	public String getDescription() {
		return description;
	}

	@Override
	public boolean addElement(Object element) {
		if (element instanceof MovementInterface)
			return addElementHelper((MovementInterface) element);
		return false;
	}

	private boolean addElementHelper(MovementInterface element) {
		boolean add = movementArray.add(element);
		if (add) {
			balance = movementArray.parallelStream().mapToDouble(x -> x.getBalance()).sum();
			EventManager.getInstance().notify("MANAGER", this);
		}
		return add;
	}

	@Override
	public boolean removeElement(int id) {
		boolean remove = movementArray.removeIf(x -> x.getID() == id);
		if (remove) {
			balance = movementArray.parallelStream().mapToDouble(x -> x.getBalance()).sum();
			EventManager.getInstance().notify("MANAGER", this);
		}
		return remove;
	}

	@Override
	public ArrayList<MovementInterface> getAllElement() {
		return movementArray;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public boolean subscribe(EventListener listener) {
		return EventManager.getInstance().subscribe("MANAGER", listener);
	}

	@Override
	public boolean unsubscribe(EventListener listener) {
		return EventManager.getInstance().unsubscribe("MANAGER", listener);
	}

	@Override
	public String toString() {
		String string = "ID management: " + getID() + "\tName: " + getName() + "\tBalance: " + getBalance() + "\n";
		for (MovementInterface e : movementArray)
			string += "ID:" + e.getID() + "\t\tType: " + e.getTypeScope() + "\t\tAmount: " + e.getBalance() + "\n";

		return string;
	}

	@Override
	public MovementInterface getElement(int id) {
		for (MovementInterface e : movementArray) {
			if (e.getID() == id)
				return e;
		}
		return null;
	}

}