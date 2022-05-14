package ru.netology.javacore;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodosTests {

    // ваши тесты для класса Todos

    Todos todos = new Todos();

    @BeforeEach
    public void initData() {
        todos.addTask("Работа");
        todos.addTask("Учеба");
        todos.addTask("Йога");
        todos.addTask("Готовка");
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void clearData() {
        todos.clearTasksList();
        System.out.println("AfterEach");
    }


    @Test
    void addTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Работа", "Учеба", "Йога", "Готовка", "Сон"));//new ArrayList<>(Arrays.asList("Работа", "Учеба", "Йога", "Готовка"));
        todos.addTask("Сон");
        Assertions.assertEquals(expected, todos.getTasksList());
        System.out.println("addTaskTest");
    }

    @Test
    void remoteTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Учеба", "Йога", "Готовка"));//new ArrayList<>(Arrays.asList("Работа", "Учеба"));
        todos.removeTask("Работа");
        Assertions.assertEquals(expected, todos.getTasksList());
        System.out.println("remoteTaskTest");
    }

    @Test
    void getAllTasksTest() {
        String expected = "Готовка Йога Работа Учеба";//"Работа Учеба Йога Готовка";
        String result = todos.getAllTasks();
        Assertions.assertEquals(expected, result);
        System.out.println("getAllTasksTest");
    }
}
