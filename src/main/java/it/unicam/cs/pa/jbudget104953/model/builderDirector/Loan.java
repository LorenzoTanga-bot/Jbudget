package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class Loan implements LoanInterface {
	private final int ID;
	private String description;
	private FinancialInterface initialTransaction;
	private ArrayList<FinancialInterface> repaymentInstallments;
	private AccountInterface secondAccount;
	private TypeScope typeScope;
	private double ratio;

	public Loan(int ID, FinancialInterface initialTransaction, ArrayList<FinancialInterface> repaymentInstallments,
			AccountInterface secondAccount, TypeScope typeScope, double ratio) {
		this.ID = ID;
		this.initialTransaction = initialTransaction;
		this.repaymentInstallments = repaymentInstallments;
		this.secondAccount = secondAccount;
		this.typeScope = typeScope;
		this.ratio = ratio;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public FinancialInterface getInitialTransaction() {
		return initialTransaction;
	}

	@Override
	public ArrayList<FinancialInterface> getRepaymentInstallments() {
		return repaymentInstallments;
	}

	@Override
	public AccountInterface getSecondAccount() {
		return secondAccount;
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
		balance += repaymentInstallments.parallelStream()
				.filter(x -> x.getScheduled() == null || x.getScheduled().isCompleted()).mapToDouble(x -> x.getAmount())
				.sum();
		return balance;
	}

	@Override
	public String toString() {

		String string = "ID Loan: " + getID() + "\t\tScope: " + getTypeScope() + "\t\tDescription" + description + "\n";
		if (getSecondAccount() != null)
			string += "With: " + getSecondAccount().getName() + " " + getSecondAccount().getSurname() + "\n";

		string += "Inital Transaction: \n" + getInitialTransaction().toString() + "\n" + "Repayment Installments : \n";
		for (FinancialInterface e : repaymentInstallments) {
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((initialTransaction == null) ? 0 : initialTransaction.hashCode());
		result = prime * result + ((repaymentInstallments == null) ? 0 : repaymentInstallments.hashCode());
		result = prime * result + ((secondAccount == null) ? 0 : secondAccount.hashCode());
		result = prime * result + ((typeScope == null) ? 0 : typeScope.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Loan))
			return false;
		Loan other = (Loan) obj;
		if (ID != other.ID)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (initialTransaction == null) {
			if (other.initialTransaction != null)
				return false;
		} else if (!initialTransaction.equals(other.initialTransaction))
			return false;
		if (repaymentInstallments == null) {
			if (other.repaymentInstallments != null)
				return false;
		} else if (!repaymentInstallments.equals(other.repaymentInstallments))
			return false;
		if (secondAccount == null) {
			if (other.secondAccount != null)
				return false;
		} else if (!secondAccount.equals(other.secondAccount))
			return false;
		if (typeScope != other.typeScope)
			return false;
		return true;
	}

	@Override
	public int compareTo(LoanInterface o) {
		return initialTransaction.compareTo(o.getInitialTransaction());
	}

}
