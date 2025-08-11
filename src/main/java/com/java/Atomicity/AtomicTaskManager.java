package com.java.Atomicity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class AtomicTaskManager {
    private static final Logger logger = Logger.getLogger(AtomicTaskManager.class.getName());
    private static final AtomicReference<List<String>> tasks = new AtomicReference<>(new ArrayList<>());

    /**
     * Adds a new task in a thread-safe, atomic way.
     * @param task Non-blank task string
     * @throws IllegalArgumentException if the task is blank
     */
    public void add(String task) {
        if (task == null || task.isBlank()) {
            throw new IllegalArgumentException("Task cannot be blank or null.");
        }

        tasks.updateAndGet(oldList -> {
            List<String> copy = new ArrayList<>(oldList);
            copy.add(task);
            return copy;
        });

        logger.info("Task added: " + task);
    }

    /**
     * Retrieves a task by index in a thread-safe way.
     * @param index The task index
     * @return The task string
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public String getTask(int index) {
        List<String> snapshot = tasks.get();
        if (index < 0 || index >= snapshot.size()) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds! There are " + snapshot.size() + " tasks."
            );
        }
        return snapshot.get(index);
    }

    /**
     * Returns an immutable snapshot of all tasks.
     */
    public List<String> getAllTasks() {
        return Collections.unmodifiableList(tasks.get());
    }
}
