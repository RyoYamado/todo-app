package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {
    @Test
    void addAndList() {
        TodoList tasks = new TodoList();
        tasks.add("  homework  ");
        assertEquals(1, tasks.size());
        assertEquals(List.of("homework"), tasks.getAll());
    }

    @Test
    void removeTask() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        tasks.add("b");
        assertTrue(tasks.remove(0));
        assertEquals(List.of("b"), tasks.getAll());
    }

    @Test
    void invalidRemove() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        assertFalse(tasks.remove(-1));
        assertFalse(tasks.remove(1));
        assertEquals(1, tasks.size());
    }

    @Test
    void emptyTasksIgnored() {
        TodoList tasks = new TodoList();
        tasks.add(" ");
        tasks.add("");
        tasks.add(null);
        assertEquals(0, tasks.size());
    }

    @Test
    void clearTasks() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        tasks.done(0);
        tasks.clear();
        assertEquals(0, tasks.size());
        tasks.add("b");
        assertFalse(tasks.isDone(0));
    }

    @Test
    void markDone() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        assertFalse(tasks.isDone(0));
        assertTrue(tasks.done(0));
        assertTrue(tasks.isDone(0));
        assertEquals("a", tasks.getAll().get(0));
    }

    @Test
    void invalidDone() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        assertFalse(tasks.done(-1));
        assertFalse(tasks.done(1));
        assertFalse(tasks.isDone(0));
    }

    @Test
    void statusAfterRemove() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        tasks.add("b");
        tasks.done(1);
        tasks.remove(0);
        assertTrue(tasks.isDone(0));
    }

    @Test
    void searchTasks() {
        TodoList tasks = new TodoList();
        tasks.add("Buy milk");
        tasks.add("Read book");
        tasks.add("Buy bread");
        assertEquals(List.of(0, 2), tasks.search(" BUY "));
        assertTrue(tasks.search("missing").isEmpty());
        assertTrue(tasks.search("").isEmpty());
        assertTrue(tasks.search(null).isEmpty());
    }

    @Test
    void listIsCopy() {
        TodoList tasks = new TodoList();
        tasks.add("a");
        tasks.getAll().clear();
        assertEquals(1, tasks.size());
    }
}
