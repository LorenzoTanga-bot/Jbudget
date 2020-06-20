package it.unicam.cs.pa.jbudget104953.model.ID;

public class IDMovement implements IDInterface {
	private static IDInterface id = null;
	private int ID;

	private IDMovement() {
		ID = 0;
	}

	public static IDInterface getInstance() {
		if (id == null)
			id = new IDMovement();

		return id;
	}

	@Override
	public boolean setID(int i) {
		this.ID = i;
		return true;
	}

	@Override
	public int getID() {
		return ID++;
	}

}
