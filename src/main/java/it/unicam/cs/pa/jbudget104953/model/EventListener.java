package it.unicam.cs.pa.jbudget104953.model;

public interface EventListener {
	/**
	 * metodo per ricevere informazioni su quando un'osservato cabia il proprio
	 * stato
	 * 
	 * @param object oggetto che ha cambiato il proprio stato
	 */
	void update(Object object);
}
