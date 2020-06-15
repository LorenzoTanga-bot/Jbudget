package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.ID.IDLoan;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class BuilderMovement implements BuilderMovementInterface {

	private FinancialInterface initialTransaction;
	private TypeMovement typeMovement;
	private ArrayList<FinancialInterface> relatedTransaction;
	private TypeScope typeScope;
	private double ratio;

	public BuilderMovement() {
		reset();
	}

	@Override
	public boolean reset() {
		initialTransaction = null;
		relatedTransaction = null;
		typeScope = null;

		return true;
	}

	@Override
	public boolean setTypeMovement(TypeMovement typeMovement) {
		if (typeMovement == null)
			throw new NullPointerException();
		this.typeMovement = typeMovement;
		return true;
	}

	@Override
	public boolean setInitialTransaction(FinancialInterface initialTransaction) {
		if (initialTransaction == null)
			throw new NullPointerException();
		this.initialTransaction = initialTransaction;
		return true;
	}

	@Override
	public boolean setRelatedTransaction(ArrayList<FinancialInterface> relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
		return true;
	}

	@Override
	public boolean setTypeScope(TypeScope typeScope) {
		if (initialTransaction == null)
			throw new NullPointerException();
		this.typeScope = typeScope;
		return true;
	}

	@Override
	public boolean setRatio(double ratio) {
		this.ratio = ratio;
		return true;
	}

	@Override
	public MovementInterface getResutl() {
		try {
			return new Movement(IDLoan.getInstance().getID(), typeMovement, initialTransaction, relatedTransaction,
					typeScope, ratio);
		} catch (Exception e) {
			return null;
		}
	}

}
