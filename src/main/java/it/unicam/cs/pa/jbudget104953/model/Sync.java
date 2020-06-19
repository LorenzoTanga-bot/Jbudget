package it.unicam.cs.pa.jbudget104953.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import it.unicam.cs.pa.jbudget104953.model.ID.IDAccount;
import it.unicam.cs.pa.jbudget104953.model.ID.IDFinancial;
import it.unicam.cs.pa.jbudget104953.model.ID.IDGroup;
import it.unicam.cs.pa.jbudget104953.model.ID.IDLoan;
import it.unicam.cs.pa.jbudget104953.model.ID.IDManagement;
import it.unicam.cs.pa.jbudget104953.model.ID.IDTag;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Financial;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.FinancialInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Movement;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.MovementInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Scheduled;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.Tag;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagInterface;
import it.unicam.cs.pa.jbudget104953.model.builderDirector.TagList;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeManagement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypePayment;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeScope;

public class Sync implements SyncInterface {

    private JSONObject writeFinancial(FinancialInterface financial) {
        JSONObject jsonFinancial = new JSONObject();
        jsonFinancial.put("ID", financial.getID());
        jsonFinancial.put("Description", financial.getDescription());
        jsonFinancial.put("TypeFinancial", financial.getTypeFinancial());
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
        TypeFinancial typeMovement = TypeFinancial.valueOf(jsonFinancial.getString("TypeFinancial"));
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
            return new Financial(ID, description, typeMovement, typePayment, amount,
                    new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1,
                            Integer.parseInt(date[0])),
                    tagList, null);

        return new Financial(ID, description, typeMovement, typePayment, amount,
                new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1,
                        Integer.parseInt(date[0])),
                tagList, new Scheduled(new GregorianCalendar(Integer.parseInt(scheduled[2]),
                        Integer.parseInt(scheduled[1]) - 1, Integer.parseInt(scheduled[0]))));
    }

    private JSONObject writeMovement(MovementInterface movement) {
        JSONObject jsonMovement = new JSONObject();
        jsonMovement.put("ID", movement.getID());
        jsonMovement.put("TypeMovement", movement.getType());
        jsonMovement.put("TypeScope", movement.getTypeScope());
        jsonMovement.put("Ratio", movement.getRatio());
        jsonMovement.put("InitialTransaction", writeFinancial(movement.getInitialTransaction()));

        JSONArray jsonElementArray = new JSONArray();
        if (movement.getRelatedTransaction() != null)
            for (FinancialInterface element : movement.getRelatedTransaction())
                jsonElementArray.put(writeFinancial(element));

        jsonMovement.put("RelatedTransaction", jsonElementArray);

        return jsonMovement;
    }

    private MovementInterface readMovement(JSONObject jsonLoan) {
        int ID = jsonLoan.getInt("ID");
        TypeMovement type = TypeMovement.valueOf(jsonLoan.getString("TypeMovement"));
        TypeScope scope = null;
        if (type == TypeMovement.LOAN)
            scope = TypeScope.valueOf(jsonLoan.getString("TypeScope"));
        double ratio = jsonLoan.getDouble("Ratio");
        FinancialInterface initialTransaction = readFinancial(jsonLoan.getJSONObject("InitialTransaction"));

        JSONArray jsonFinancilArray = jsonLoan.getJSONArray("RelatedTransaction");
        ArrayList<FinancialInterface> relatedTransaction = new ArrayList<>() {

            private static final long serialVersionUID = 1L;
            {
                for (Object financial : jsonFinancilArray) {
                    add(readFinancial((JSONObject) financial));
                }
            }
        };

        return new Movement(ID, type, initialTransaction, relatedTransaction, scope, ratio);

    }

    private JSONObject writeManagement(ManagementInterface<?> management) {
        JSONObject jsonManagement = new JSONObject();
        jsonManagement.put("ID", management.getID());
        jsonManagement.put("Name", management.getName());
        jsonManagement.put("Description", management.getDescription());

        JSONArray jsonElementArray = new JSONArray();
        for (Object element : management.getAllElement())
            jsonElementArray.put(writeMovement((MovementInterface) element));

        jsonManagement.put("Element", jsonElementArray);

        return jsonManagement;
    }

    private ManagementInterface<MovementInterface> readManagement(JSONObject jsonManagement) {

        ManagementInterface<MovementInterface> management = new ManagementMovement(jsonManagement.getInt("ID"),
                jsonManagement.getString("Name"), jsonManagement.getString("Description"));

        JSONArray elements = jsonManagement.getJSONArray("Element");

        for (Object financial : elements)
            management.addElement(readMovement((JSONObject) financial));

        return management;
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
        AccountInterface account = new Account(ID, name, surname, description);

        JSONArray jsonManagementArray = jsonAccount.getJSONArray("ManagementShared");

        for (Object management : jsonManagementArray)
            account.addManagement(TypeManagement.SHARED, readManagement((JSONObject) management));

        jsonManagementArray = jsonAccount.getJSONArray("ManagementUnshared");

        for (Object management : jsonManagementArray)
            account.addManagement(TypeManagement.UNSHARED, readManagement((JSONObject) management));

        return account;
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
        GroupInterface group = new Group(ID);

        JSONArray jsonAccountArray = jsonGroup.getJSONArray("Account");
        for (Object account : jsonAccountArray)
            group.addAccount(readAccount((JSONObject) account));

        return group;
    }

    private JSONObject writeID() {
        JSONObject jsonID = new JSONObject();
        jsonID.put("IDAccount", IDAccount.getInstance().getID());
        jsonID.put("IDFinancial", IDFinancial.getInstance().getID());
        jsonID.put("IDLoan", IDLoan.getInstance().getID());
        jsonID.put("IDManagement", IDManagement.getInstance().getID());
        jsonID.put("IDGroup", IDGroup.getInstance().getID());
        jsonID.put("IDTag", IDTag.getInstance().getID());

        return jsonID;
    }

    private void readID(JSONObject jsonID) {
        IDAccount.getInstance().setID(jsonID.getInt("IDAccount"));
        IDFinancial.getInstance().setID(jsonID.getInt("IDFinancial"));
        IDLoan.getInstance().setID(jsonID.getInt("IDLoan"));
        IDManagement.getInstance().setID(jsonID.getInt("IDManagement"));
        IDGroup.getInstance().setID(jsonID.getInt("IDGroup"));
        IDTag.getInstance().setID(jsonID.getInt("IDTag"));
    }

    private JSONObject writeTag(TagInterface tag) {
        JSONObject jsonTag = new JSONObject();
        jsonTag.put("ID", tag.getID());
        jsonTag.put("Name", tag.getName());
        return jsonTag;
    }

    private TagInterface readTag(JSONObject jsonTag) {
        return new Tag(jsonTag.getInt("ID"), jsonTag.getString("Name"));
    }

    private JSONArray writeTagList() {
        JSONArray jsonTagList = new JSONArray();
        for (Map.Entry<TypeFinancial, ArrayList<TagInterface>> tagList : TagList.getInstance().getTag().entrySet()) {
            for (TagInterface tag : tagList.getValue()) {
                jsonTagList.put(new JSONObject() {
                    {
                        put("Type", tagList.getKey());
                        put("Tag", writeTag(tag));
                    }
                });
            }

        }
        return jsonTagList;
    }

    private void readTagList(JSONArray jsonTagList) {
        for (Object tag : jsonTagList)
            TagList.getInstance().addTag(TypeFinancial.valueOf(((JSONObject) tag).getString("Type")),
                    readTag(((JSONObject) tag).getJSONObject("Tag")));

    }

    @Override
    public void write(GroupInterface group, String path) throws IOException {
        FileWriter file = new FileWriter(path);
        JSONObject jsonGroup = writeGroup(group);
        JSONObject jsonID = writeID();
        JSONArray jsonTagList = writeTagList();
        JSONObject json = new JSONObject() {
            {
                put("Group", jsonGroup);
                put("ID", jsonID);
                put("TagList", jsonTagList);
            }
        };
        file.write(json.toString());
        file.close();
    }

    @Override
    public GroupInterface read(String path) throws IOException {
        File file = new File(path);
        JSONTokener tokener = new JSONTokener(new FileInputStream(file));
        JSONObject tmp = new JSONObject(tokener);
        readID(tmp.getJSONObject("ID"));
        readTagList(tmp.getJSONArray("TagList"));
        return readGroup(tmp.getJSONObject("Group"));
    }

}