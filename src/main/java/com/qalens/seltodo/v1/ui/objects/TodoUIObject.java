package com.qalens.seltodo.v1.ui.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class TodoUIObject {

    protected WebDriver driver;
    public TodoUIObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver,10),this);
    }
    public  <T> T getObject(Function<WebDriver,T> factory, Consumer<T> waitFunction){
        return getObject(this.driver,factory,waitFunction);
    }
    public  static <T> T getObject(WebDriver driver,Function<WebDriver,T> factory, Consumer<T> waitFunction){
        T returnPage =  factory.apply(driver);
        waitFunction.accept(returnPage);
        return returnPage;
    }
    public static <T> T launch(Function<WebDriver,T> factory, Consumer<T> waitFunction){
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://gotodov1.onrender.com");
        return getObject(driver,factory,waitFunction);
    }
    public void waitForVisible(WebElement element, Duration duration){
        WebDriverWait wait=new WebDriverWait(this.driver,duration);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForVisible(By locator, Duration duration){
        WebDriverWait wait=new WebDriverWait(this.driver,duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForInVisible(By locator, Duration duration){
        WebDriverWait wait=new WebDriverWait(this.driver,duration);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void quite(){
        this.driver.quit();
    }
}