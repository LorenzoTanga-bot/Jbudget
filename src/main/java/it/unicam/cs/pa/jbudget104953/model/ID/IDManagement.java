package it.unicam.cs.pa.jbudget104953.model.ID;

public class IDManagement implements IDInterface {
	private static IDInterface id = null;
	private int ID;

	private IDManagement() {
		ID = 0;
	}
	
	public static IDInterface getInstance() {
		if (id == null) 
			id = new IDManagement(); 
  
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
