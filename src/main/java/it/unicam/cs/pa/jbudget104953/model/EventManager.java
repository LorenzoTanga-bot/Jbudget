package it.unicam.cs.pa.jbudget104953.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements EventManagerInterface {

    private static EventManagerInterface eventManager;

    private Map<String, List<EventListener>> listeners = new HashMap<>();

    private EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public static EventManagerInterface getInstance(String... operations) {
        if (eventManager == null)
            eventManager = new EventManager(operations);
        else
            eventManager.addEvents(operations);

        return eventManager;
    }

    public boolean addEvents(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
        return true;
    }

    public boolean subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);

        return users.add(listener);
    }

    public boolean unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
        return true;
    }

    public boolean notify(String eventType, Object objects) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(objects);
        }
        return true;
    }
}
