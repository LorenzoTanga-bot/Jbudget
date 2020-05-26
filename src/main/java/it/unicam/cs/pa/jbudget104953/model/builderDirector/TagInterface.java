package it.unicam.cs.pa.jbudget104953.model.builderDirector;

/**
 * Questa interfaccia definisce un Tag/Categoria
 * 
 * @author Lorenzo Tanganelli lorenzo.tanganelli@studenti.unicam.it
 */
public interface TagInterface extends Comparable<TagInterface> {

	/**
	 * restituisce l'ID del tag
	 * 
	 * @return ID
	 */
	public int getID();

	/**
	 * restituisce il nome del tag
	 * 
	 * @return nome del tag
	 */
	public String getName();
}
