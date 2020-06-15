package it.unicam.cs.pa.jbudget104953.model.builderDirector;

import java.util.ArrayList;
import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.enumerable.TypeFinancial;

/**
 * Questa interfaccia definisce la collezione di tutti i tag
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface TagListInterface {

	/**
	 * restituisce il tag avente l'ID passato per parametro
	 * 
	 * @param ID ID del tag cercato nella collezione
	 * @return TagInterface se è presente nella collezione altrimenti null
	 */
	public TagInterface getTag(int ID);

	/**
	 * restituisce il tag avente il nome passato per parametro
	 * 
	 * @param name nome del tag cercato nella collezione
	 * @return TagInterface se è presente nella collezione altrimenti null
	 */
	public TagInterface getTag(String name);

	/**
	 * restituisce tutti i tag divisi per categorie (Expense o Revenue)
	 * 
	 * @return tutti i tag
	 */
	public Map<TypeFinancial, ArrayList<TagInterface>> getTag();

	/**
	 * aggiunge un tag alla collezione
	 * 
	 * @param type tipo di tag
	 * @param tag  tag che si vuole aggiungere alla collezione
	 * @return true se l'operazione è andata a buon fine
	 */
	public boolean addTag(TypeFinancial type, TagInterface tag);

	/**
	 * rimuovere un tag dalla collezione
	 * 
	 * @param tag tag da rimuovere
	 * @return
	 */
	public boolean removeTag(TagInterface tag);

}
