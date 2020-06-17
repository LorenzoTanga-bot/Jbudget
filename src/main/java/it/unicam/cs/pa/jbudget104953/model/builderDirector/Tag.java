package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import it.unicam.cs.pa.jbudget104953.model.ID.IDTag;

public class Tag implements TagInterface {

	private final int ID;
	private final String name;

	public Tag(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public Tag(String name) {
		this(IDTag.getInstance().getID(), name);
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
	public String toString() {
		return "ID: " + getID() + "\ttName: " + getName();
	}

	@Override
	public int compareTo(TagInterface o) {
		return ((Integer) ID).compareTo((Integer) o.getID());
	}

}
