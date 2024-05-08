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
    @FindBy(id = "todos")
    WebElement list;
    public void waitForListToBeVisible(){
        waitForVisible(list, Duration.ofSeconds(60));
    }
    public TodoList(WebDriver driver) {
        super(driver);
    }
    public TodoList waitForTodo(String title){
        waitForVisible(getBy(title),Duration.ofSeconds(10));
        return this;
    }
    public TodoList waitForNoTodo(String title){
        waitForInVisible(getBy(title),Duration.ofSeconds(10));
        return this;
    }

    public List<String> getListItemsByTitle(String title) {
        return list.findElements(getBy(title)).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private static By getBy(String title) {
        return By.xpath("//li[.='" + title + "']/form");
    }

    public TodoList DeleteTodoByTitle(String todoTitle) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(getBy(todoTitle))).perform();
        driver.findElement(getBy(todoTitle)).findElement(By.xpath("//*[name()='svg']")).click();
        return this;
    }
}
