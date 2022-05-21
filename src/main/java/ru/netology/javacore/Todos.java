package ru.netology.javacore;

import java.util.*;

public class Todos {

    private List<String> tasksList = new ArrayList<>();

    public void addTask(String task) {
        tasksList.add(task);
    }

    public void removeTask(String task) {
        tasksList.remove(task);
    }

    public String getAllTasks() {
        Collections.sort(tasksList);
        StringBuilder builder = new StringBuilder();
        for (String task : tasksList) {
            builder.append(task).append(" ");
        }
        return builder.toString();
    }


}
