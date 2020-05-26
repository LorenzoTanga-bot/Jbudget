package it.unicam.cs.pa.jbudget104953.controller;

import java.util.function.Consumer;

public interface CommandInterface<T> {
    public void processCommand(String command);

    public boolean addCommand(String name, Consumer<T> command);
}