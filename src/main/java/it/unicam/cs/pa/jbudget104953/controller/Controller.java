package it.unicam.cs.pa.jbudget104953.controller;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.Group;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Tag;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.view.ViewConsole;
import it.unicam.cs.pa.jbudget104953.view.ViewInterface;

public class Controller {
	private final int GROUPNUM = 0;
	private final int ACCOUNTNUM = 1;
	private final int MANAGEMENTNUM = 2;
	private final int TAGLISNUM = 3;
	private Command<Controller> command;
	private ViewInterface view;
	private ControllerGroup groupController;
	private ControllerAccount accountController;
	private ControllerManagement managementController;

	private boolean status;
	private boolean sync;
	private int feedback;

	public Controller(ViewInterface view) {
		this.view = view;
		status = true;
		sync = false;
		feedback = GROUPNUM;
		command = new Command<>(this);

		groupController = new ControllerGroup();
		accountController = new ControllerAccount();
		managementController = new ControllerManagement();
	}

	public Controller() {
		this(new ViewConsole());
	}

	/* GROUP */
	private void addAccount() {
		groupController.addAccount(view.addAccount());
	}

	private void removeAccount() {
		groupController.removeAccount(view.getID());
	}

	private void viewAccount() {
		AccountInterface account = null;
		if ((account = groupController.getAccount(view.getID())) != null) {
			accountController.setAccount(account);
			this.feedback = ACCOUNTNUM;
		}
	}

	/* ACCOUNT */
	private void addManagement() {
		accountController.addManagement(view.addManagement());

	}

	private void removeManagement() {
		accountController.removeManagement(view.getID());
	}

	private void viewManagement() {
		ManagementInterface<?> management = null;
		if ((management = accountController.getManagement(view.getID())) != null) {
			managementController.setManagement(management);
			this.feedback = MANAGEMENTNUM;
		}
	}

	/* MANAGEMENT */
	private void addElement() {
		if (managementController.getType().equals("FINANCIAL"))
			managementController.addElement(view.newFinancial());
		else
			managementController.addElement(view.newLoan());
	}

	private void removeElement() {
		managementController.removeElement(view.getID());
	}

	private void viewElement() {
		view.viewElement(managementController.getElement(view.getID()));
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
		command.addCommand("ADD ELEMENT", Controller::addElement);
		command.addCommand("REMOVE ELEMENT", Controller::removeElement);
		command.addCommand("VIEW ELEMENT", Controller::viewElement);
		command.addCommand("SHOW ELEMENT", Controller -> this.feedback = TAGLISNUM);
		command.addCommand("ADD TAG", Controller::addTag);
		command.addCommand("REMOVE TAG", Controller::removeTag);
		command.addCommand("GO BACK", Controller -> {
			{
				if (feedback == TAGLISNUM)
					feedback = MANAGEMENTNUM;
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
			GroupInterface group = new Group();
			group.subscribe(view);
			groupController.setGroup(group);
		}
		addCommands();
		while (status) {
			switch (feedback) {
				case GROUPNUM:
					command = view.menuGroup(groupController.getGroup());
					break;
				case ACCOUNTNUM:
					command = view.menuAccount(accountController.getAccount());
					break;
				case MANAGEMENTNUM:
					command = view.menuManagement(managementController.getManagement());
					break;
				case TAGLISNUM:
					command = view.menuTagList();
					break;
			}
			this.command.processCommand(command);

		}

	}

}
