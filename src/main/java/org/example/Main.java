package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class Main {

    static WebDriver driver=null;

    public static void main(String[] args) {
           /*Get the Current Window Handle: Before opening
        or switching to another window, it's a good practice to store the current window's handle.*/
        String parentHandle = driver.getWindowHandle();
        System.out.println("parent window unique string value is " + parentHandle);
        /*Perform Action to Open New Window:
        This could be clicking on a link or button that opens a new window or tab.*/
        driver.findElement(By.cssSelector("#openwindow")).click();
        /*Get All Window Handles: After a new window or tab is opened,
        you can get the set of all window handles and iterate through them.*/
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!parentHandle.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }


    }}