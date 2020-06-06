package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeMovement;

public class TagList implements TagListInterface {
	private static TagListInterface list = null;
	private Map<TypeMovement, ArrayList<TagInterface>> tagMap;

	private TagList() {
		tagMap = new HashMap<>();
		for (TypeMovement typeMovement : TypeMovement.values()) {
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
		for (Map.Entry<TypeMovement, ArrayList<TagInterface>> e : tagMap.entrySet())
			for (TagInterface tag : e.getValue())
				if (tag.getID() == ID)
					return tag;
		return null;
	}

	@Override
	public TagInterface getTag(String name) {
		for (Map.Entry<TypeMovement, ArrayList<TagInterface>> e : tagMap.entrySet())
			for (TagInterface tag : e.getValue())
				if (tag.getName().equals(name))
					return tag;
		return null;
	}

	public ArrayList<TagInterface> getType(TypeMovement type) {
		return tagMap.get(type);
	}

	@Override
	public Map<TypeMovement, ArrayList<TagInterface>> getTag() {
		return tagMap;
	}

	@Override
	public boolean addTag(TypeMovement type, TagInterface tag) {
		ArrayList<TagInterface> array = tagMap.get(type);
		return array.add(tag);
	}

	@Override
	public boolean removeTag(TagInterface tag) {
		boolean remove = false;
		for (Map.Entry<TypeMovement, ArrayList<TagInterface>> e : tagMap.entrySet())
			if (e.getValue().remove(tag))
				remove = true;
		return remove;
	}

	@Override
	public String toString() {
		String string = "";
		for (Map.Entry<TypeMovement, ArrayList<TagInterface>> e : tagMap.entrySet()) {
			string += "Type " + e.getKey().toString() + ":\n";
			for (TagInterface tag : e.getValue())
				string += tag.toString() + "\n";
		}
		return string;
	}
}
