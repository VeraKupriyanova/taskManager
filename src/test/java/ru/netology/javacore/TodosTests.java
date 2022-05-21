package ru.netology.javacore;

import org.junit.jupiter.api.*;

public class TodosTests {

    Todos todos = new Todos();

    @BeforeEach
    public void initData() {
        todos.addTask("Работа");
        todos.addTask("Учеба");
        todos.addTask("Йога");
        todos.addTask("Готовка");
        todos.addTask("Сон");
        System.out.println("BeforeEach");
    }

    @AfterEach
    public void clearData() {
        System.out.println("AfterEach");
    }


    @Test
    void addTaskTest() {
        String expected = "Готовка Йога Работа Сон Сон Учеба ";//"Готовка Йога Работа";
        todos.addTask("Сон");
        Assertions.assertEquals(expected, todos.getAllTasks());
        System.out.println("addTaskTest");
    }

    @Test
    void remoteTaskTest() {
        String expected = "Готовка Йога Сон Учеба ";//"Готовка Йога Работа";
        todos.removeTask("Работа");
        Assertions.assertEquals(expected, todos.getAllTasks());
        System.out.println("remoteTaskTest");
    }

    @Test
    void getAllTasksTest() {
        String expected = "Готовка Йога Работа Сон Учеба ";//"Готовка Йога Работа Учеба ";
        String result = todos.getAllTasks();
        Assertions.assertEquals(expected, result);
        System.out.println("getAllTasksTest");
    }
}
