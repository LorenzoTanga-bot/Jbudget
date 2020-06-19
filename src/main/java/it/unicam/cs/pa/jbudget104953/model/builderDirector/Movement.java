package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class Movement implements MovementInterface {
	private final int ID;
	private TypeMovement typeMovement;
	private FinancialInterface initialTransaction;
	private ArrayList<FinancialInterface> relatedTransaction;
	private TypeScope typeScope;
	private double ratio;

	public Movement(int ID, TypeMovement typeMovement, FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> relatedTransaction, TypeScope typeScope, double ratio) {
		this.ID = ID;
		this.typeMovement = typeMovement;
		this.initialTransaction = initialTransaction;
		this.relatedTransaction = relatedTransaction;
		this.typeScope = typeScope;
		this.ratio = ratio;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public TypeMovement getType() {
		return typeMovement;
	}

	@Override
	public FinancialInterface getInitialTransaction() {
		return initialTransaction;
	}

	@Override
	public ArrayList<FinancialInterface> getRelatedTransaction() {
		return relatedTransaction;
	}

	@Override
	public ArrayList<FinancialInterface> getAllTransaction() {
		return new ArrayList<FinancialInterface>() {
			private static final long serialVersionUID = 1L;

			{
				add(getInitialTransaction());
				if (typeMovement != TypeMovement.SINGLE)
					addAll(getRelatedTransaction());
			}
		};
	}

	@Override
	public TypeScope getTypeScope() {
		return typeScope;
	}

	@Override
	public double getRatio() {
		return ratio;
	}

	@Override
	public double getBalance() {
		double balance = initialTransaction.getAmount();
		if (relatedTransaction != null)
			balance += relatedTransaction.parallelStream()
					.filter(x -> x.getScheduled() != null && x.getScheduled().isCompleted())
					.mapToDouble(x -> x.getAmount()).sum();
		return balance;
	}

	@Override
	public String toString() {

		String string = "ID: " + getID() + "\tType: " + getType() + "\n" + "Inital Transaction: \n"
				+ getInitialTransaction().toString() + "\n";
		if (relatedTransaction != null) {
			string += "Repayment Installments : \n";
			for (FinancialInterface e : relatedTransaction)
				string += "ID financial: " + e.getID() + "\t\tDate: "
						+ (new SimpleDateFormat("dd-MM-yyyy").format(e.getDate().getTime())) + "\t\tAmount: "
						+ e.getAmount() + "\n";
		}

		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((initialTransaction == null) ? 0 : initialTransaction.hashCode());
		result = prime * result + ((relatedTransaction == null) ? 0 : relatedTransaction.hashCode());
		result = prime * result + ((typeScope == null) ? 0 : typeScope.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Movement))
			return false;
		Movement other = (Movement) obj;
		if (ID != other.ID)
			return false;
		if (initialTransaction == null) {
			if (other.initialTransaction != null)
				return false;
		} else if (!initialTransaction.equals(other.initialTransaction))
			return false;
		if (relatedTransaction == null) {
			if (other.relatedTransaction != null)
				return false;
		} else if (!relatedTransaction.equals(other.relatedTransaction))
			return false;
		if (typeScope != other.typeScope)
			return false;
		return true;
	}

	@Override
	public int compareTo(MovementInterface o) {
		return initialTransaction.compareTo(o.getInitialTransaction());
	}

}
