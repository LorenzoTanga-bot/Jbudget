package it.unicam.cs.pa.jbudget104953.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import it.unicam.cs.pa.jbudget104953.model.builderDirector.Financial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Loan;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.LoanInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Scheduled;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class Sync {

    private JSONObject writeFinancial(FinancialInterface financial) {
        JSONObject jsonFinancial = new JSONObject();
        jsonFinancial.put("ID", financial.getID());
        jsonFinancial.put("Description", financial.getDescription());
        jsonFinancial.put("TypeMovement", financial.getTypeMovement());
        jsonFinancial.put("TypePayment", financial.getTypePayment());
        jsonFinancial.put("Amount", financial.getAmount());
        jsonFinancial.put("Date", new SimpleDateFormat("dd-MM-yyyy").format(financial.getDate().getTime()));

        if (financial.getScheduled() != null)
            jsonFinancial.put("Scheduled",
                    new SimpleDateFormat("dd-MM-yyyy").format(financial.getScheduled().getDate().getTime()));
        else
            jsonFinancial.put("Scheduled", "null");

        JSONArray jsonTag = new JSONArray();
        for (TagInterface tag : financial.getTag()) {
            jsonTag.put(tag.getID());
        }

        jsonFinancial.put("Tag", jsonTag);

        return jsonFinancial;
    }

    private FinancialInterface readFinancial(JSONObject jsonFinancial) {
        int ID = jsonFinancial.getInt("ID");
        String description = jsonFinancial.getString("Description");
        double amount = jsonFinancial.getDouble("Amount");
        TypeMovement typeMovement = TypeMovement.valueOf(jsonFinancial.getString("TypeMovement"));
        TypePayment typePayment = TypePayment.valueOf(jsonFinancial.getString("TypePayment"));
        String[] date = jsonFinancial.getString("Date").split("-");
        String[] scheduled = jsonFinancial.getString("Scheduled").split("-");

        JSONArray jsonTag = jsonFinancial.getJSONArray("Tag");
        ArrayList<TagInterface> tagList = new ArrayList<>() {
            private static final long serialVersionUID = 1L;

            {
                for (Object tag : jsonTag) {
                    add(TagList.getInstance().getTag((int) tag));
                }
            }
        };

        if (scheduled[0].equals("null"))
            return new Financial(ID, description, typeMovement, typePayment, amount, new GregorianCalendar(
                    Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])), tagList, null);

        return new Financial(ID, description, typeMovement, typePayment, amount,
                new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])),
                tagList, new Scheduled(new GregorianCalendar(Integer.parseInt(scheduled[2]),
                        Integer.parseInt(scheduled[1]), Integer.parseInt(scheduled[0]))));
    }

    private JSONObject writeLoan(LoanInterface loan) {
        JSONObject jsonLoan = new JSONObject();
        jsonLoan.put("ID", loan.getID());
        jsonLoan.put("TypeScope", loan.getTypeScope());
        jsonLoan.put("Ratio", loan.getRatio());
        jsonLoan.put("InitialTransaction", writeFinancial(loan.getInitialTransaction()));

        JSONArray jsonElementArray = new JSONArray();
        for (FinancialInterface element : loan.getRepaymentInstallments())
            jsonElementArray.put(writeFinancial(element));

        jsonLoan.put("RepaymentInstallments", jsonElementArray);

        return jsonLoan;
    }

    private LoanInterface readLoan(JSONObject jsonLoan) {
        int ID = jsonLoan.getInt("ID");
        TypeScope scope = TypeScope.valueOf(jsonLoan.getString("TypeScope"));
        double ratio = jsonLoan.getDouble("Ratio");
        FinancialInterface initialTransaction = readFinancial(jsonLoan.getJSONObject("InitialTransaction"));

        JSONArray jsonFinancilArray = jsonLoan.getJSONArray("RepaymentInstallments");
        ArrayList<FinancialInterface> repaymentInstallments = new ArrayList<>() {

            private static final long serialVersionUID = 1L;
            {
                for (Object financial : jsonFinancilArray) {
                    add(readFinancial((JSONObject) financial));
                }
            }
        };

        return new Loan(ID, initialTransaction, repaymentInstallments, scope, ratio);

    }

    private JSONObject writeManagement(ManagementInterface<?> management) {
        JSONObject jsonManagement = new JSONObject();
        jsonManagement.put("ID", management.getID());
        jsonManagement.put("Type", management.getType());
        jsonManagement.put("Description", management.getDescription());

        JSONArray jsonElementArray = new JSONArray();
        if (management.getType().equals("FINANCIAL"))
            for (Object element : management.getAllElement())
                jsonElementArray.put(writeFinancial((FinancialInterface) element));

        else
            for (Object element : management.getAllElement())
                jsonElementArray.put(writeLoan((LoanInterface) element));

        jsonManagement.put("Element", jsonElementArray);

        return jsonManagement;
    }

    private ManagementInterface<?> readManagement(JSONObject jsonManagement) {
        int ID = jsonManagement.getInt("ID");
        String description = jsonManagement.getString("Description");

        JSONArray elements = jsonManagement.getJSONArray("Element");

        if (jsonManagement.getString("Type").equals("FINANCIAL")) {
            ArrayList<FinancialInterface> financialArray = new ArrayList<>() {

                private static final long serialVersionUID = 1L;

                {
                    for (Object financial : elements) {
                        add(readFinancial((JSONObject) financial));
                    }
                }
            };
            return new ManagementFinancial(ID, financialArray, description);
        } else {
            ArrayList<LoanInterface> loanArray = new ArrayList<>() {

                private static final long serialVersionUID = 1L;

                {
                    for (Object financial : elements) {
                        add(readLoan((JSONObject) financial));
                    }
                }
            };
            return new ManagementLoan(ID, loanArray, description);

        }
    }

    private JSONObject writeAccount(AccountInterface account) {
        JSONObject jsonAccount = new JSONObject();
        jsonAccount.put("ID", account.getID());
        jsonAccount.put("Name", account.getName());
        jsonAccount.put("Surname", account.getSurname());
        jsonAccount.put("Description", account.getDescription());

        JSONArray jsonManagementArray = new JSONArray();
        for (ManagementInterface<?> management : account.getManagement().get(TypeManagement.SHARED))
            jsonManagementArray.put(writeManagement(management));

        jsonAccount.put("ManagementShared", jsonManagementArray);

        jsonManagementArray = new JSONArray();
        for (ManagementInterface<?> management : account.getManagement().get(TypeManagement.UNSHARED))
            jsonManagementArray.put(writeManagement(management));

        jsonAccount.put("ManagementUnshared", jsonManagementArray);

        return jsonAccount;
    }

    private AccountInterface readAccount(JSONObject jsonAccount) {
        int ID = jsonAccount.getInt("ID");
        String name = jsonAccount.getString("Name");
        String surname = jsonAccount.getString("Surname");
        String description = jsonAccount.getString("Description");

        JSONArray jsonManagementArray = jsonAccount.getJSONArray("ManagementShared");
        ArrayList<ManagementInterface<?>> shareList = new ArrayList<>();

        for (Object management : jsonManagementArray)
            shareList.add(readManagement((JSONObject) management));

        jsonManagementArray = jsonAccount.getJSONArray("ManagementUnshared");
        ArrayList<ManagementInterface<?>> unshareList = new ArrayList<>();

        for (Object management : jsonManagementArray)
            unshareList.add(readManagement((JSONObject) management));

        Map<TypeManagement, ArrayList<ManagementInterface<?>>> managementArray = new HashMap<>() {

            private static final long serialVersionUID = 1L;

            {
                put(TypeManagement.SHARED, shareList);
                put(TypeManagement.UNSHARED, unshareList);
            }
        };
        return new Account(ID, name, surname, description, managementArray);
    }

    private JSONObject writeGroup(GroupInterface group) {
        JSONObject jsonGroup = new JSONObject();
        jsonGroup.put("ID", group.getID());

        JSONArray jsonAccountArray = new JSONArray();

        for (AccountInterface account : group.getAccounts())
            jsonAccountArray.put(writeAccount(account));

        jsonGroup.put("Account", jsonAccountArray);

        return jsonGroup;
    }

    private GroupInterface readGroup(JSONObject jsonGroup) {
        int ID = jsonGroup.getInt("ID");

        JSONArray jsonAccountArray = jsonGroup.getJSONArray("Account");
        ArrayList<AccountInterface> accountArray = new ArrayList<>();
        for (Object account : jsonAccountArray)
            accountArray.add(readAccount((JSONObject) account));

        return new Group(ID, accountArray);
    }

    public void write(GroupInterface group, String path) throws IOException {
        FileWriter file = new FileWriter(path);
        file.write(writeGroup(group).toString());
        file.close();
    }

    public GroupInterface read(String path) throws IOException {
        File file = new File(path);
        JSONTokener tokener = new JSONTokener(new FileInputStream(file));
        return readGroup(new JSONObject(tokener));
    }

}