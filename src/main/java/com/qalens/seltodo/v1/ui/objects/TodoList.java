package com.qalens.seltodo.v1.ui.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList extends TodoUIObject{
    private static final By DeleteIconLocator = By.xpath("//*[name()='svg']");
    @FindBy(id = "todos")
    WebElement list;
    public void waitForListToBeVisible(){
        waitForVisible(list, Duration.ofSeconds(60));
    }
    public TodoList(WebDriver driver) {
        super(driver);
    }
    public TodoList waitForTodo(String title){
        waitForVisible(getTodoLocatorByTitle(title),Duration.ofSeconds(10));
        return this;
    }
    public TodoList waitForNoTodo(String title){
        waitForInVisible(getTodoLocatorByTitle(title),Duration.ofSeconds(10));
        return this;
    }

    public List<String> getListItemsByTitle(String title) {
        return list.findElements(getTodoLocatorByTitle(title)).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private static By getTodoLocatorByTitle(String title) {
        return By.xpath("//li[.='" + title + "']/form");
    }

    public TodoList DeleteTodoByTitle(String todoTitle) {
        WebElement todoItem = driver.findElement(getTodoLocatorByTitle(todoTitle));
        Actions actions = new Actions(driver);
        actions.moveToElement(todoItem).perform();
        todoItem.findElement(DeleteIconLocator).click();
        return this;
    }
}
