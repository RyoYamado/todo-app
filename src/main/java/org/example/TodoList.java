package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TodoList {
    private final List<String> items = new ArrayList<>();
    private final List<Boolean> completed = new ArrayList<>();

    public void add(String item) {
        if (item != null && !item.trim().isEmpty()) {
            items.add(item.trim());
            completed.add(false);
        }
    }

    public boolean remove(int index) {
        if (index < 0 || index >= items.size()) {
            return false;
        }
        items.remove(index);
        completed.remove(index);
        return true;
    }

    public List<String> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
        completed.clear();
    }

    public boolean done(int index) {
        if (index < 0 || index >= size()) {
            return false;
        }
        completed.set(index, true);
        return true;
    }

    public boolean isDone(int index) {
        return completed.get(index);
    }

    public List<Integer> search(String query) {
        List<Integer> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return results;
        }
        String value = query.trim().toLowerCase(Locale.ROOT);
        for (int i = 0; i < size(); i++) {
            if (items.get(i).toLowerCase(Locale.ROOT).contains(value)) {
                results.add(i);
            }
        }
        return results;
    }
}
