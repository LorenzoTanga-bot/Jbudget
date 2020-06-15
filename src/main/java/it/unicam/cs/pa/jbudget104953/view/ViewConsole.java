package it.unicam.cs.pa.jbudget104953.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.AccountInterface;
import it.unicam.cs.pa.jbudget104953.model.GroupInterface;
import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;

public class ViewConsole implements ViewInterface {

	private BufferedReader in;
	private PrintStream out;

	public ViewConsole(InputStream in, OutputStream out) {
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintStream(out);
	}

	public ViewConsole() {
		this(System.in, System.out);

	}

	private double readNum() {
		while (true) {
			try {
				out.print("\n > ");
				String s = in.readLine();
				return Double.parseDouble(s.trim());
			} catch (NumberFormatException e) {
				out.print("Error, insert number\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String readString() {
		while (true) {
			try {
				out.print("\n > ");
				return in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Object object) {
		;
	}

	@Override
	public void hello() {
		out.println("|---------------------------------------|\n" + "|           Jbudget 0.1 beta            |\n"
				+ "|       Author: Lorenzo Tanganelli      |\n" + "|---------------------------------------|");
	}

	@Override
	public int getID() {
		out.print("ID");
		return (int) readNum();
	}

	/* GROUP */
	@Override
	public String menuGroup(GroupInterface group) {
		out.println(group.toString());
		out.print("ADD ACCOUNT | REMOVE ACCOUNT | VIEW ACCOUNT | EXIT");
		return readString().toUpperCase();
	}

	@Override
	public Map<String, String> addAccount() {
		Map<String, String> info = new HashMap<>();
		out.print("Name");
		info.put("Name", readString());
		out.print("Surname");
		info.put("Surname", readString());
		out.print("Description");
		info.put("Description", readString());
		return info;
	}

	/* ACCOUNT */
	@Override
	public String menuAccount(AccountInterface account) {
		out.println(account.toString());
		out.print("ADD MANAGEMENT | REMOVE MANAGEMENT |  VIEW MANAGEMENT | GO BACK");
		return readString().toUpperCase();
	}

	@Override
	public Map<String, String> addManagement() {
		Map<String, String> info = new HashMap<>();
		out.print("Description");
		info.put("Description", readString());
		out.print("Is SHARED WITH THE GROUP\n Y/N");
		info.put("Shared", readString().toUpperCase());
		return info;
	}

	/* MANAGEMENT */
	@Override
	public String menuManagement(ManagementInterface<?> management) {
		out.println(management.toString());
		out.print("ADD ELEMENT | REMOVE ELEMENT |  VIEW ELEMENT | SHOW TAG | GO BACK");
		return readString().toUpperCase();
	}

	private Map<String, String> newFinancial() {
		Map<String, String> info = new HashMap<>();

		out.print("NEW FINANCIAL\nEXPENSE | REVENUE");
		info.put("TypeFinancial", readString().toUpperCase());

		out.print("AMOUNT");
		info.put("Amount", String.valueOf(readNum()));

		out.print("TAG\n");
		out.print(TagList.getInstance().toString());
		String IDtag = "";
		do {
			out.print("ID | SKIP");
			String string = readString().toUpperCase();
			try {
				IDtag += Integer.parseInt(string) + ",";
			} catch (NumberFormatException e) {
				if (string.equals("SKIP"))
					break;
			}
		} while (true);
		info.put("Tag", IDtag);

		out.print("DESCRIPTION");
		info.put("Description", readString());

		out.print("Is Scheduled\nY/N");
		if (readString().toUpperCase().equals("Y")) {
			out.print("Date scheduled <DD/MM/YYYY>");
			info.put("DateScheduled", readString());
		} else
			info.put("DateScheduled", null);
		return info;
	}

	private Map<String, String> newSingle() {
		Map<String, String> info = newFinancial();

		return new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("TypeFinancial0", info.get("TypeFinancial"));
				put("Amount0", info.get("Amount"));
				put("Tag0", info.get("Tag"));
				put("Description0", info.get("Description"));
				put("DateScheduled0", info.get("DateScheduled"));
			}
		};
	}

	private Map<String, String> newMulti() {
		Map<String, String> info = new HashMap<>();
		int i = 0;
		do {
			Map<String, String> financial = newFinancial();
			info.put("TypeFinancial" + i, financial.get("TypeFinancial"));
			info.put("Amount" + i, financial.get("Amount"));
			info.put("Tag" + i, financial.get("Tag"));
			info.put("Description" + i, financial.get("Description"));
			info.put("DateScheduled" + i, financial.get("DateScheduled"));

			i++;
			out.print("Do you want to insert a new one?\nY/N");
		} while (readString().toUpperCase().equals("Y"));
		info.put("numMovement", String.valueOf(i));

		return info;

	}

	private Map<String, String> newRepeated() {
		Map<String, String> info = new HashMap<>();
		info.putAll(newSingle());

		out.print("NUMBER OF RATE");
		info.put("NumberRate", String.valueOf((int) readNum()));

		out.print("EVERY ? DAY");
		info.put("Day", String.valueOf((int) readNum()));
		return info;
	}

	private Map<String, String> newLoan() {
		Map<String, String> info = new HashMap<>();

		info.putAll(newSingle());

		out.print("NUMBER OF RATE");
		info.put("NumberRate", String.valueOf((int) readNum()));

		out.print("EVERY ? DAY");
		info.put("Day", String.valueOf((int) readNum()));

		out.print("SCOPE\nLIQUID | CONSUMER");
		info.put("Scope", readString().toUpperCase());

		out.print("RATIO");
		info.put("Ratio", String.valueOf(readNum()));
		return info;
	}

	@Override
	public Map<String, String> newMovement() {
		Map<String, String> info = new HashMap<>();
		out.print("SINGLE | MULTI | REPEATED | LOAN");
		info.put("TypeMovement", readString().toUpperCase());
		switch (info.get("TypeMovement")) {
			case "SINGLE":
				info.putAll(newSingle());
				break;
			case "MULTI":
				info.putAll(newMulti());
				break;
			case "REPEATED":
				info.putAll(newRepeated());
				break;
			case "LOAN":
				info.putAll(newLoan());
				break;
		}

		return info;
	}

	@Override
	public String menuTagList() {
		out.println(TagList.getInstance().toString());
		out.print("ADD TAG | REMOVE TAG | GO BACK");
		return readString().toUpperCase();
	}

	@Override
	public Map<String, String> addTag() {
		Map<String, String> info = new HashMap<>();
		out.print("EXPENSE | REVENUE");
		info.put("Type", readString().toUpperCase());
		out.print("NAME:");
		info.put("Name", readString());
		return info;
	}

	@Override
	public void viewElement(Object element) {
		out.println(element.toString());
	}
}