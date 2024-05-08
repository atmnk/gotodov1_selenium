package com.qalens.seltodo.v1.tests;

import com.github.javafaker.Faker;
import com.qalens.seltodo.v1.ui.CreateTodoUI;
import com.qalens.seltodo.v1.ui.TodoListUI;
import com.qalens.seltodo.v1.ui.AbstractTodoUI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateTodoTest {
    static Faker faker = new Faker();
    public String TodoTitle;
    CreateTodoUI createTodoForm;
    TodoListUI todoListUI;

    @BeforeMethod
    public void setUp() {
        TodoTitle = faker.lorem().sentence();
        createTodoForm = AbstractTodoUI
                .launch(CreateTodoUI::new, CreateTodoUI::waitForCreateTodoFormToBeRendered);
    }

    @Test
    public void creationOfTodoShouldWork() {
        todoListUI = createTodoForm
                .tryCreatingTodoWithTitle(TodoTitle)
                .getObject(TodoListUI::new, TodoListUI::waitForTodoListToBeVisible);

        todoListUI
                .waitForTodoWithTitleToBeVisible(TodoTitle);
    }

    @AfterMethod
    public void tearDown() {
        todoListUI.DeleteTodoByTitle(TodoTitle)
                .waitForTodoWithTitleToBeInvisible(TodoTitle)
                .quite();
        ;
    }
}
