package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;

public class Financial implements FinancialInterface {
	private final int ID;
	private String description;
	private TypeMovement typeMovement;
	private TypePayment typePayment;
	private double amount;
	private GregorianCalendar date;
	private ArrayList<TagInterface> tag;
	private AccountInterface account;
	private ScheduledInterface scheduled;

	public Financial(int ID, String description, TypeMovement typeMovement, TypePayment typePayment, double amount,
			GregorianCalendar date, ArrayList<TagInterface> tag, AccountInterface account,
			ScheduledInterface scheduled) {

		this.ID = ID;
		this.description = description;
		this.typeMovement = typeMovement;
		this.typePayment = typePayment;
		this.amount = amount;
		this.date = date;
		this.tag = tag;
		this.account = account;

	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public TypeMovement getTypeMovement() {
		return typeMovement;
	}

	@Override
	public TypePayment getTypePayment() {
		return typePayment;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public GregorianCalendar getDate() {
		return date;
	}

	@Override
	public ArrayList<TagInterface> getTag() {
		return tag;
	}

	@Override
	public AccountInterface getAccount() {
		return account;
	}

	@Override
	public ScheduledInterface getScheduled() {
		return scheduled;
	}

	@Override
	public String toString() {
		String string = "ID financial: " + getID() + "\t\tType: " + getTypeMovement() + "\t\tPayment: "
				+ getTypePayment() + "\t\tDate: " + (new SimpleDateFormat("dd-MM-yyyy").format(getDate().getTime()))
				+ "\n" + "Amount: " + getAmount();
		if (getScheduled() != null)
			string += "\t\tScheduled: " + getScheduled().toString();
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((scheduled == null) ? 0 : scheduled.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((typeMovement == null) ? 0 : typeMovement.hashCode());
		result = prime * result + ((typePayment == null) ? 0 : typePayment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Financial))
			return false;
		Financial other = (Financial) obj;
		if (ID != other.ID)
			return false;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (scheduled == null) {
			if (other.scheduled != null)
				return false;
		} else if (!scheduled.equals(other.scheduled))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (typeMovement != other.typeMovement)
			return false;
		if (typePayment != other.typePayment)
			return false;
		return true;
	}

	@Override
	public int compareTo(FinancialInterface o) {
		int compareDate = date.compareTo(o.getDate());
		int compareID = ((Integer) ID).compareTo((Integer) o.getID());

		if (compareDate != 0)
			return compareDate;
		if (compareID != 0)
			return compareID;
		return 0;
	}

}
