package com.qalens.seltodo.v1.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class CreateTodoUI extends AbstractTodoUI {
    @FindBy(css = "[name='title']")
    WebElement titleTextBox;
    @FindBy(tagName = "button")
    WebElement addButton;
    public void waitForCreateTodoFormToBeRendered(){
        waitForVisible(titleTextBox, Duration.ofSeconds(60));
        waitForVisible(addButton, Duration.ofSeconds(60));
    }
    public CreateTodoUI(WebDriver driver) {
        super(driver);
    }

    public CreateTodoUI tryCreatingTodoWithTitle(String title) {
        titleTextBox.clear();
        titleTextBox.sendKeys(title);
        addButton.click();
        return this;
    }
}
