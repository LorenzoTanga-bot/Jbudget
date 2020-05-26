package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ID.IDLoan;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class BuilderLoan implements BuilderLoanInterface {

	private FinancialInterface initialTransaction;
	private ArrayList<FinancialInterface> repaymentInstallments;
	private AccountInterface secondAccount;
	private TypeScope typeScope;
	private double ratio;

	public BuilderLoan() {
		reset();
	}

	@Override
	public boolean reset() {
		initialTransaction = null;
		repaymentInstallments = null;
		secondAccount = null;
		typeScope = null;

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
	public boolean setRepaymentInstallments(ArrayList<FinancialInterface> repaymentInstallments) {
		if (initialTransaction == null)
			throw new NullPointerException();
		this.repaymentInstallments = repaymentInstallments;
		return true;
	}

	@Override
	public boolean setSecondAccount(AccountInterface secondAccount) {
		this.secondAccount = secondAccount;
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
	public LoanInterface getResutl() {
		try {
			return new Loan(IDLoan.getInstance().getID(), initialTransaction, repaymentInstallments, secondAccount,
					typeScope, ratio);
		} catch (Exception e) {
			return null;
		}
	}

}
