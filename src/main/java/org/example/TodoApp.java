package org.example;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TodoList tasks = new TodoList();
        System.out.println("Commands: add <task>, remove <index>, list, clear, done <index>, search <text>, exit");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+", 2);
                String command = parts[0].toLowerCase(Locale.ROOT);
                String argument = parts.length == 2 ? parts[1] : "";
                switch (command) {
                    case "add":
                        if (argument.isBlank()) {
                            System.out.println("Usage: add <task>");
                        } else {
                            tasks.add(argument);
                            System.out.println("Added.");
                        }
                        break;
                    case "remove":
                    case "done":
                        try {
                            int index = Integer.parseInt(argument);
                            boolean success = command.equals("remove")
                                    ? tasks.remove(index) : tasks.done(index);
                            System.out.println(success ? "OK." : "Index out of range.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid index.");
                        }
                        break;
                    case "list":
                        if (tasks.size() == 0) {
                            System.out.println("(empty)");
                        }
                        for (int i = 0; i < tasks.size(); i++) {
                            printTask(tasks, i);
                        }
                        break;
                    case "clear":
                        tasks.clear();
                        System.out.println("Cleared.");
                        break;
                    case "search":
                        List<Integer> found = tasks.search(argument);
                        if (found.isEmpty()) {
                            System.out.println("No matches.");
                        }
                        for (int index : found) {
                            printTask(tasks, index);
                        }
                        break;
                    case "exit":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Unknown command.");
                }
            }
        }
    }

    private static void printTask(TodoList tasks, int index) {
        String status = tasks.isDone(index) ? "[x]" : "[ ]";
        System.out.printf("%d: %s %s%n", index, status, tasks.getAll().get(index));
    }
}
