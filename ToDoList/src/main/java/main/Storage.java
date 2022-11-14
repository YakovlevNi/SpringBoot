package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    private static int currentId = 1;

    public synchronized static List<Task> getAllTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.addAll(tasks.values());
        return taskArrayList;
    }

    public static int addTask(Task task) {
        int id = currentId++;
        task.setId(id);
        tasks.put(id, task);

        return id;

    }

    public static Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }

    public static void deleteTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

    public static void upgradeTask(int id, Task task) {
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }


    }
}
