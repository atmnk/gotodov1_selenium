package com.qalens.seltodo.v1.tests;

import com.github.javafaker.Faker;
import com.qalens.seltodo.v1.ui.objects.CreateTodoForm;
import com.qalens.seltodo.v1.ui.objects.TodoList;
import com.qalens.seltodo.v1.ui.objects.TodoUIObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateTodoTest {
    static Faker faker = new Faker();
    public String TodoTitle;
    CreateTodoForm createTodoForm;
    TodoList todoList;

    @BeforeMethod
    public void setUp() {
        TodoTitle = faker.lorem().sentence();
        createTodoForm = TodoUIObject
                .launch(CreateTodoForm::new, CreateTodoForm::waitForForm);
    }

    @Test
    public void creationOfTodoShouldWork() {
        todoList = createTodoForm
                .tryCreateTodo(TodoTitle)
                .getObject(TodoList::new, TodoList::waitForListToBeVisible);

        todoList
                .waitForTodo(TodoTitle);
    }

    @AfterMethod
    public void tearDown() {
        todoList.DeleteTodoByTitle(TodoTitle)
                .waitForNoTodo(TodoTitle)
                .quite();
        ;
    }
}
