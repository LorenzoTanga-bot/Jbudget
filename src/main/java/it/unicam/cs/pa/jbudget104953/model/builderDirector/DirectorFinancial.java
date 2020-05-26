package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

public class DirectorFinancial implements DirectorFinancialInterface {

	private static DirectorFinancialInterface director = null;

	private BuilderFinancialInterface financialBuilder;

	private DirectorFinancial() {
		financialBuilder = new BuilderFinancial();
	}

	public static DirectorFinancialInterface getInstance() {
		if (director == null)
			director = new DirectorFinancial();

		return director;
	}

	@Override
	public boolean setFinancialBuilder(BuilderFinancialInterface financialBuilder) {
		this.financialBuilder = financialBuilder;
		return true;
	}

	@Override
	public boolean makeExpense(String description, double amount, GregorianCalendar date, ArrayList<TagInterface> tag,
			AccountInterface account, GregorianCalendar scheduled) {
		financialBuilder.reset();
		financialBuilder.setDescription(description);
		financialBuilder.setAmount(-1 * Math.abs(amount));
		financialBuilder.setDate(date);
		financialBuilder.setTag(tag);
		financialBuilder.setAccount(account);
		financialBuilder.setScheduled(new Scheduled(scheduled));
		financialBuilder.setTypeMovement(TypeMovement.EXPENSE);
		if (scheduled == null)
			financialBuilder.setTypePayment(TypePayment.DEBIT);
		else
			financialBuilder.setTypePayment(TypePayment.CREDIT);
		return true;
	}

	@Override
	public boolean makeRevenue(String description, double amount, GregorianCalendar date, ArrayList<TagInterface> tag,
			AccountInterface account, GregorianCalendar scheduled) {
		financialBuilder.reset();
		financialBuilder.setDescription(description);
		financialBuilder.setAmount(amount);
		financialBuilder.setDate(date);
		financialBuilder.setTag(tag);
		financialBuilder.setAccount(account);
		financialBuilder.setScheduled(null);
		financialBuilder.setTypeMovement(TypeMovement.REVENUE);
		if (scheduled == null)
			financialBuilder.setTypePayment(TypePayment.DEBIT);
		else
			financialBuilder.setTypePayment(TypePayment.CREDIT);
		return true;
	}

	@Override
	public FinancialInterface getResult() {
		return financialBuilder.getResult();
	}
}