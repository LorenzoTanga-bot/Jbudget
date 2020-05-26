package it.unicam.cs.pa.jbudget104953.model.ID;

public class IDLoan implements IDInterface{
	private static IDInterface id = null;
	private int ID;

	private IDLoan() {
		ID = 0;
	}
	
	public static IDInterface getInstance() {
		if (id == null) 
			id = new IDLoan(); 
  
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
