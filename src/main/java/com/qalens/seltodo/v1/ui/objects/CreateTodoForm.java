package com.qalens.seltodo.v1.ui.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class CreateTodoForm extends TodoUIObject{
    @FindBy(css = "[name='title']")
    WebElement titleTextBox;
    @FindBy(tagName = "button")
    WebElement addButton;
    public void waitForForm(){
        waitForVisible(titleTextBox, Duration.ofSeconds(60));
        waitForVisible(addButton, Duration.ofSeconds(60));
    }
    public CreateTodoForm(WebDriver driver) {
        super(driver);
    }

    public CreateTodoForm tryCreateTodo(String title) {
        titleTextBox.clear();
        titleTextBox.sendKeys(title);
        addButton.click();
        return this;
    }
}
