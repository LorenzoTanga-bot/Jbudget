package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class DirectorLoan implements DirectorLoanInterface {

	private static DirectorLoanInterface director = null;

	private BuilderLoanInterface loanBuilder;

	private DirectorLoan() {
		loanBuilder = new BuilderLoan();
	}

	public static DirectorLoanInterface getInstance() {
		if (director == null)
			director = new DirectorLoan();

		return director;
	}

	@Override
	public boolean setLoanBuilder(BuilderLoanInterface loanBuilder) {
		this.loanBuilder = loanBuilder;
		return true;
	}

	@Override
	public boolean makeConsumer(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repaymentInstallments, AccountInterface secondAccount, double ratio) {

		loanBuilder.reset();
		loanBuilder.setInitialTransaction(initialTransaction);
		loanBuilder.setRepaymentInstallments(repaymentInstallments);
		loanBuilder.setSecondAccount(secondAccount);
		loanBuilder.setTypeScope(TypeScope.CONSUMER_GOODS);
		loanBuilder.setRatio(ratio);
		return true;
	}

	@Override
	public boolean makeLiquid(FinancialInterface initialTransaction,
			ArrayList<FinancialInterface> repaymentInstallments, AccountInterface secondAccount, double ratio) {

		loanBuilder.reset();
		loanBuilder.setInitialTransaction(initialTransaction);
		loanBuilder.setRepaymentInstallments(repaymentInstallments);
		loanBuilder.setSecondAccount(secondAccount);
		loanBuilder.setTypeScope(TypeScope.LIQUID_ASSETS);
		loanBuilder.setRatio(ratio);
		return true;
	}

	@Override
	public LoanInterface getResult() {
		return loanBuilder.getResutl();
	}

}
