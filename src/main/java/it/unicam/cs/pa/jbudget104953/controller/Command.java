package it.unicam.cs.pa.jbudget104953.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Command<T> implements CommandInterface<T> {
    private Map<String, Consumer<T>> commands;
    private T state;

    public Command(T state) {
        this(new HashMap<>(), state);
    }

    public Command(Map<String, Consumer<T>> commands, T state) {
        this.commands = commands;
        this.state = state;
    }

    public void processCommand(String command) {
        Consumer<? super T> action = commands.get(command);
        if (action == null) {
            throw new IllegalArgumentException();
        } else {
            action.accept(state);
        }
    }

    public boolean addCommand(String name, Consumer<T> command) {
        commands.put(name, command);
        return commands.containsKey(name);
    }

}