package ru.netology.javacore;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class TodoServer {

    private Todos todos;
    private int port;


    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Запуск сервера " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {// стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ) {
                    // обработка одного подключения
                    String jsonTask = in.readLine();
                    Map<String, String> map = jsonToMap(jsonTask);

                    String action = "", task = "";
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key.equals("type")) {
                            action = value;
                        } else {
                            task = value;
                        }
                    }

                    switch (action) {
                        case "ADD":
                            todos.addTask(task);
                            break;
                        case ("REMOVE"):
                            todos.removeTask(task);
                            break;
                    }
                    out.println(todos.getAllTasks());
                } catch (IOException e) {
                    System.out.println("Не могу стартовать сервер");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private static Map<String, String> jsonToMap(String jsonText) {

        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Map<String, String> map = gson.fromJson(jsonText, mapType);
        return map;
    }

}
