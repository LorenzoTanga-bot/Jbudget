package it.unicam.cs.pa.jbudget104953.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.Account;
import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementFinancial;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementLoan;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorFinancial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.DirectorLoan;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Tag;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.view.ViewConsole;
import it.unicam.cs.pa.jbudget104953.view.ViewInterface;

public class Controller {
	private final int GROUPNUM = 0;
	private final int ACCOUNTNUM = 1;
	private final int MANAGEMENTNUM = 2;
	private final int TAGLISNUM = 3;
	private final int ELEMENTNUM = 4;
	private Command<Controller> command;
	private ViewInterface view;
	private GroupInterface group;
	private AccountInterface account;
	private ManagementInterface<?> management;

	private boolean status;
	private boolean sync;
	private int feedback;

	public Controller(ViewInterface view) {
		this.view = view;
		status = true;
		sync = false;
		feedback = GROUPNUM;
		command = new Command<>(this);
	}

	public Controller() {
		this(new ViewConsole());
	}

	/* GROUP */
	private void addAccount() {
		Map<String, String> info = view.addAccount();
		group.addAccount(new Account(info.get("Name"), info.get("Surname"), info.get("Description")));
	}

	private void removeAccount() {
		int id = view.getID();
		group.getAccounts().removeIf(x -> x.getID() == id);
	}

	private void viewAccount() {
		int id = view.getID();
		AccountInterface account = null;
		if ((account = group.getAccount(id)) != null) {
			this.account = account;
			this.feedback = ACCOUNTNUM;
		}
	}

	/* ACCOUNT */
	private void addManagement() {
		Map<String, String> info = view.addManagement();
		switch (info.get("Type")) {
			case "FINANCIAL":
				switch (info.get("Shared")) {
					case "Y":
						this.account.addManagement(TypeManagement.SHARED,
								new ManagementFinancial(info.get("Description")));
						break;
					case "N":
						this.account.addManagement(TypeManagement.UNSHARED,
								new ManagementFinancial(info.get("Description")));
						break;
				}
				break;

			case "LOAN":
				switch (info.get("Shared")) {
					case "Y":
						this.account.addManagement(TypeManagement.SHARED, new ManagementLoan(info.get("Description")));
						break;
					case "N":
						this.account.addManagement(TypeManagement.UNSHARED,
								new ManagementLoan(info.get("Description")));
						break;
				}
				break;

		}
	}

	private void removeManagement() {
		int id = view.getID();
		account.removeManagement(account.getManagement(id));
	}

	private void viewManagement() {
		int id = view.getID();
		ManagementInterface<?> management = null;
		if ((management = account.getManagement(id)) != null) {
			this.management = management;
			this.feedback = MANAGEMENTNUM;
		}
	}

	/* MANAGEMENT */
	private FinancialInterface newFinancial(Map<String, String> map) {
		String description = map.get("Description");
		double amount = Double.parseDouble(map.get("Amount"));
		ArrayList<TagInterface> tagArray = new ArrayList<>() {

			private static final long serialVersionUID = 1L;

			{
				if (map.get("Tag") != null)
					for (String tag : map.get("Tag").split(","))
						add(TagList.getInstance().getTag(tag));
			}
		};

		String[] scheduled = null;
		if (map.get("DateScheduled") != null)
			scheduled = map.get("DateScheduled").split("/");

		switch (map.get("TypeMovement")) {
			case "EXPENSE":
				if (map.get("DateScheduled") != null)
					DirectorFinancial.getInstance().makeExpense(description, amount, new GregorianCalendar(), tagArray,
							account, new GregorianCalendar(Integer.parseInt(scheduled[2]),
									Integer.parseInt(scheduled[1]), Integer.parseInt(scheduled[0])));
				else
					DirectorFinancial.getInstance().makeExpense(description, amount, new GregorianCalendar(), tagArray,
							account, null);
				break;

			case "REVENUE":
				if (map.get("DateScheduled") != null)
					DirectorFinancial.getInstance().makeRevenue(description, amount, new GregorianCalendar(), tagArray,
							account, new GregorianCalendar(Integer.parseInt(scheduled[2]),
									Integer.parseInt(scheduled[1]), Integer.parseInt(scheduled[0])));
				else
					DirectorFinancial.getInstance().makeRevenue(description, amount, new GregorianCalendar(), tagArray,
							account, null);
				break;
		}
		return DirectorFinancial.getInstance().getResult();
	}

	private void addFinancial() {
		Map<String, String> map = view.newFinancial();
		management.addElement(newFinancial(map));
	}

	private void addLoan() {
		Map<String, String> map = view.newLoan();
		FinancialInterface initialTransaction = newFinancial(map);
		double ratio = Double.parseDouble(map.get("Ratio"));
		int numRate = Integer.parseInt(map.get("NumberRate"));
		double amount = (initialTransaction.getAmount() / numRate)
				+ ((initialTransaction.getAmount() / numRate) * ratio / 100);

		AccountInterface secondAccount;
		if (map.get("SecondAccount") != null)
			secondAccount = group.getAccount(Integer.parseInt(map.get("SecondAccount")));
		else
			secondAccount = null;

		ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {

			private static final long serialVersionUID = 1L;

			{
				GregorianCalendar date = (GregorianCalendar) initialTransaction.getDate().clone();
				for (int i = 0; i < numRate; i++) {
					date.add(GregorianCalendar.WEEK_OF_YEAR, 4);
					if (initialTransaction.getTypeMovement() == TypeMovement.EXPENSE)
						DirectorFinancial.getInstance().makeRevenue("", -1 * amount, (GregorianCalendar) date.clone(),
								new ArrayList<>(), secondAccount, (GregorianCalendar) date.clone());
					else
						DirectorFinancial.getInstance().makeExpense("", -1 * amount, (GregorianCalendar) date.clone(),
								new ArrayList<>(), secondAccount, (GregorianCalendar) date.clone());
					add(DirectorFinancial.getInstance().getResult());
				}

			}
		};

		switch (map.get("Scope")) {
			case "LIQUID":
				DirectorLoan.getInstance().makeLiquid(initialTransaction, repaymentInstallments, secondAccount, ratio);
				break;
			case "CONSUMER":
				DirectorLoan.getInstance().makeConsumer(initialTransaction, repaymentInstallments, secondAccount,
						ratio);
				break;
		}
		management.addElement(DirectorLoan.getInstance().getResult());

	}

	private void removeElement() {
		int id = view.getID();
		management.removeElement(id);
	}

	private void viewElement() {
		int ID = view.getID();
		view.viewElement(management.getElement(ID));
	}

	/* TAG */
	private void addTag() {
		Map<String, String> map = view.addTag();
		switch (map.get("Type")) {
			case "EXPENSE":
				TagList.getInstance().addTag(TypeMovement.EXPENSE, new Tag(map.get("Name")));
				break;
			case "REVENUE":
				TagList.getInstance().addTag(TypeMovement.REVENUE, new Tag(map.get("Name")));
				break;
		}
	}

	private void removeTag() {
		int ID = view.getID();
		TagList.getInstance().removeTag(TagList.getInstance().getTag(ID));
	}

	private void addCommands() {
		command.addCommand("ADD ACCOUNT", Controller::addAccount);
		command.addCommand("REMOVE ACCOUNT", Controller::removeAccount);
		command.addCommand("VIEW ACCOUNT", Controller::viewAccount);
		command.addCommand("ADD MANAGEMENT", Controller::addManagement);
		command.addCommand("REMOVE MANAGEMENT", Controller::removeManagement);
		command.addCommand("VIEW MANAGEMENT", Controller::viewManagement);
		command.addCommand("ADD ELEMENT", Controller -> {
			switch (management.getType()) {
				case "FINANCIAL":
					addFinancial();
					break;
				case "LOAN":
					addLoan();
			}
		});
		command.addCommand("REMOVE ELEMENT", Controller::removeElement);
		command.addCommand("VIEW ELEMENT", Controller::viewElement);
		command.addCommand("SHOW ELEMENT", Controller -> this.feedback = TAGLISNUM);
		command.addCommand("ADD TAG", Controller::addTag);
		command.addCommand("REMOVE TAG", Controller::removeTag);
		command.addCommand("GO BACK", Controller -> {
			{
				if (feedback == TAGLISNUM)
					feedback -= 2;
				else
					feedback--;
			}
		});
		command.addCommand("EXIT", Controller -> status = false);
	}

	public void start() {
		String command = "";
		view.hello();
		if (!sync) {
			this.group = new Group();
			this.group.subscribe(view);
		}
		addCommands();
		while (status) {
			switch (feedback) {
				case GROUPNUM:
					command = view.menuGroup(group);
					break;
				case ACCOUNTNUM:
					command = view.menuAccount(account);
					break;
				case MANAGEMENTNUM:
					command = view.menuManagement(management);

					break;
				case ELEMENTNUM:
					// commandsElemets.get(key).run();
					break;
				case TAGLISNUM:
					command = view.menuTagList();
					break;
			}
			this.command.processCommand(command);

		}

	}

}
