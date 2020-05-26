package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.ID.IDFinancial;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

public class BuilderFinancial implements BuilderFinancialInterface {

	private String description;
	private TypeMovement typeMovement;
	private TypePayment typePayment;
	private double amount;
	private GregorianCalendar date;
	private ArrayList<TagInterface> tag;
	private AccountInterface account;
	private ScheduledInterface scheduled;

	public BuilderFinancial() {
		reset();
	}

	@Override
	public boolean reset() {
		description = null;
		typeMovement = null;
		typePayment = null;
		amount = 0;
		date = null;
		tag = null;
		account = null;
		scheduled = null;

		return true;
	}

	@Override
	public boolean setDescription(String description) {
		if (description == null)
			throw new NullPointerException();
		this.description = description;
		return true;
	}

	@Override
	public boolean setTypeMovement(TypeMovement type) {
		if (type == null)
			throw new NullPointerException();
		this.typeMovement = type;
		return true;
	}

	@Override
	public boolean setTypePayment(TypePayment type) {
		this.typePayment = type;
		return true;
	}

	@Override
	public boolean setAmount(double amount) {
		if (amount == 0)
			throw new NullPointerException();
		this.amount = amount;
		return true;
	}

	@Override
	public boolean setDate(GregorianCalendar date) {
		if (date == null)
			throw new NullPointerException();
		this.date = date;
		return true;
	}

	@Override
	public boolean setTag(ArrayList<TagInterface> tag) {
		if (tag == null)
			throw new NullPointerException();
		this.tag = tag;
		return true;
	}

	@Override
	public boolean setAccount(AccountInterface account) {
		this.account = account;
		return true;
	}

	@Override
	public boolean setScheduled(ScheduledInterface scheduled) {
		this.scheduled = scheduled;
		return true;
	}

	@Override
	public FinancialInterface getResult() {
		try {
			return new Financial(IDFinancial.getInstance().getID(), description, typeMovement, typePayment, amount,
					date, tag, account, scheduled);
		} catch (Exception e) {
			return null;
		}

	}

}
