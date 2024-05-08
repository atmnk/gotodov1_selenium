package com.qalens.seltodo.v1.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class TodoListUI extends AbstractTodoUI {
    private static final By DeleteIconLocator = By.xpath("//*[name()='svg']");
    @FindBy(id = "todos")
    WebElement list;
    public void waitForTodoListToBeVisible(){
        waitForVisible(list, Duration.ofSeconds(60));
    }
    public TodoListUI(WebDriver driver) {
        super(driver);
    }
    public TodoListUI waitForTodoWithTitleToBeVisible(String title){
        waitForVisible(getTodoLocatorByTitle(title),Duration.ofSeconds(10));
        return this;
    }
    public TodoListUI waitForTodoWithTitleToBeInvisible(String title){
        waitForInVisible(getTodoLocatorByTitle(title),Duration.ofSeconds(10));
        return this;
    }

    private static By getTodoLocatorByTitle(String title) {
        return By.xpath("//li[.='" + title + "']/form");
    }

    public TodoListUI DeleteTodoByTitle(String todoTitle) {
        WebElement todoItem = driver.findElement(getTodoLocatorByTitle(todoTitle));
        Actions actions = new Actions(driver);
        actions.moveToElement(todoItem).perform();
        todoItem.findElement(DeleteIconLocator).click();
        return this;
    }
}
