package it.unicam.cs.pa.jbudget104953.controller;

import java.util.Map;

import it.unicam.cs.pa.jbudget104953.model.ManagementInterface;

public interface ControllerManagementInterface {
    public boolean setManagement(ManagementInterface<?> management);

    public ManagementInterface<?> getManagement();

    public int getID();

    public double getBalance();

    public String getType();

    public boolean addElement(Map<String, String> info);

    public boolean removeElement(int ID);

    /**
     * restituisce un elmento avente l'ID passato per parametro
     * 
     * @param ID id
     * @return l'elemento se è presente nella collezione altrimenti null
     */
    public Object getElement(int ID);

    public String toString();
}