package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.EventListener;
import it.unicam.cs.pa.jbudget104953.model.EventManager;
import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;

public class TagList implements TagListInterface {
	private static TagListInterface list = null;
	private Map<TypeFinancial, ArrayList<TagInterface>> tagMap;

	private TagList() {
		EventManager.getInstance("TAG");
		tagMap = new HashMap<>();
		for (TypeFinancial typeMovement : TypeFinancial.values()) {
			tagMap.put(typeMovement, new ArrayList<>());
		}

	}

	public static TagListInterface getInstance() {
		if (list == null)
			list = new TagList();

		return list;
	}

	@Override
	public TagInterface getTag(int ID) {
		for (Map.Entry<TypeFinancial, ArrayList<TagInterface>> e : tagMap.entrySet())
			for (TagInterface tag : e.getValue())
				if (tag.getID() == ID)
					return tag;
		return null;
	}

	@Override
	public TagInterface getTag(String name) {
		for (Map.Entry<TypeFinancial, ArrayList<TagInterface>> e : tagMap.entrySet())
			for (TagInterface tag : e.getValue())
				if (tag.getName().equals(name))
					return tag;
		return null;
	}

	public ArrayList<TagInterface> getType(TypeFinancial type) {
		return tagMap.get(type);
	}

	@Override
	public Map<TypeFinancial, ArrayList<TagInterface>> getTag() {
		return tagMap;
	}

	@Override
	public boolean addTag(TypeFinancial type, TagInterface tag) {
		ArrayList<TagInterface> array = tagMap.get(type);
		Boolean add = array.add(tag);
		EventManager.getInstance().notify("TAG", this);
		return add;
	}

	@Override
	public boolean removeTag(TagInterface tag) {
		boolean remove = false;
		for (Map.Entry<TypeFinancial, ArrayList<TagInterface>> e : tagMap.entrySet())
			if (e.getValue().remove(tag))
				remove = true;
		return remove;
	}

	@Override
	public boolean subscribe(EventListener listener) {
		return EventManager.getInstance().subscribe("TAG", listener);
	}

	@Override
	public boolean unsubscribe(EventListener listener) {
		return EventManager.getInstance().subscribe("TAG", listener);
	}

	@Override
	public String toString() {
		String string = "";
		for (Map.Entry<TypeFinancial, ArrayList<TagInterface>> e : tagMap.entrySet()) {
			string += "Type " + e.getKey().toString() + ":\n";
			for (TagInterface tag : e.getValue())
				string += tag.toString() + "\n";
		}
		return string;
	}
}
