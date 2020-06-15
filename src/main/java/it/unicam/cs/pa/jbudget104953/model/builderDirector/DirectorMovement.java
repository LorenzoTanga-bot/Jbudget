package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class DirectorMovement implements DirectorMovementInterface {

	private static DirectorMovementInterface director = null;

	private BuilderMovementInterface movementBuilder;

	private DirectorMovement() {
		movementBuilder = new BuilderMovement();
	}

	public static DirectorMovementInterface getInstance() {
		if (director == null)
			director = new DirectorMovement();

		return director;
	}

	@Override
	public boolean setMovementBuilder(BuilderMovementInterface loanBuilder) {
		this.movementBuilder = loanBuilder;
		return true;
	}

	@Override
	public boolean makeLoanConsumer(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repaymentInstallments, double ratio) {

		movementBuilder.reset();
		movementBuilder.setInitialTransaction(initialTransaction);
		movementBuilder.setRelatedTransaction(repaymentInstallments);
		movementBuilder.setTypeScope(TypeScope.CONSUMER_GOODS);
		movementBuilder.setRatio(ratio);
		return true;
	}

	@Override
	public boolean makeLoanLiquid(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repaymentInstallments, double ratio) {

		movementBuilder.reset();
		movementBuilder.setInitialTransaction(initialTransaction);
		movementBuilder.setRelatedTransaction(repaymentInstallments);
		movementBuilder.setTypeScope(TypeScope.LIQUID_ASSETS);
		movementBuilder.setRatio(ratio);
		return true;
	}

	@Override
	public boolean makeSingleMovement(FinancialInterface initialTransaction) {
		movementBuilder.reset();
		movementBuilder.setTypeMovement(TypeMovement.SINGLE);
		movementBuilder.setInitialTransaction(initialTransaction);
		movementBuilder.setRelatedTransaction(null);
		movementBuilder.setTypeScope(null);
		return true;
	}

	@Override
	public boolean makeMultiMovement(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> relatedTransaction) {
		movementBuilder.reset();
		movementBuilder.setTypeMovement(TypeMovement.MULTI);
		movementBuilder.setInitialTransaction(initialTransaction);
		movementBuilder.setRelatedTransaction(relatedTransaction);
		movementBuilder.setTypeScope(null);
		return true;
	}

	@Override
	public boolean makeRepeatedMovement(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repeatedTransaction) {
		movementBuilder.reset();
		movementBuilder.setTypeMovement(TypeMovement.REPEATED);
		movementBuilder.setInitialTransaction(initialTransaction);
		movementBuilder.setRelatedTransaction(repeatedTransaction);
		movementBuilder.setTypeScope(null);
		return true;
	}

	@Override
	public MovementInterface getResult() {
		return movementBuilder.getResutl();
	}

}
