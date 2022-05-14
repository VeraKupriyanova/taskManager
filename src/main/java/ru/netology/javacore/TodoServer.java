package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    Todos todos;
    int port;

    public TodoServer(int port, Todos todos) {

        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Запуск сервера " + port + "...");

        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                System.out.println("Соединение установлено");

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String taskLine;

                taskLine = in.readLine();
                if (taskLine.equals("0")) break;

                todos = gson.fromJson(taskLine, Todos.class);
                String type = todos.getAction();
                String task = todos.getTask();

                switch (type) {
                    case "ADD":
                        todos.addTask(task);
                        break;
                    case "REMOVE":
                        todos.removeTask(task);
                        break;
                }
                String tasks = todos.getAllTasks();
                out.println("Мои задачи: " + tasks);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
