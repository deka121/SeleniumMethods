package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class webpage {

    static WebDriver driver;

    public static void main(String args[]) {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        SelectRadioBtn();
        driver.quit();
    }

   public void alertHandler(){

        driver.findElement(By.cssSelector("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent();

        }


    public void alertOperation() {
        driver.findElement(By.cssSelector("#alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println("successfully get the txt " + text);

        Alert alert1 = driver.switchTo().alert();
        alert1.accept();
        alert1.dismiss();
        alert1.getText();
        alert1.sendKeys("de");
    }

    public void windowHandler() {
        /*Child Window handler*/
        String parentHandle = driver.getWindowHandle();
        System.out.println("parent window unique string value is " + parentHandle);
        /*Perform Action to Open New Window:
        This could be clicking on a link or button that opens a new window or tab.*/
        driver.findElement(By.cssSelector("#opentab")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handles : windowHandles) {
            System.out.println(windowHandles);
            if (!handles.equals(parentHandle)) {
                driver.switchTo().window(handles);
                System.out.println("i am in new window");
            }
            driver.switchTo().defaultContent();
        }
    }


    public void actionsOperation ()
    {
        // Locate the element to be dragged (replace with the actual element locator)
        WebElement element1 = driver.findElement(By.id("element1"));
        WebElement element2 = driver.findElement(By.id("element2"));

        Actions actions = new Actions(driver);
        actions.contextClick().perform(); // right click
        actions.doubleClick().perform();
        actions.moveToElement(element1).click().moveToElement(element2).click().build().perform();

        // Locate the input field (replace with the actual locator)
        WebElement inputField = driver.findElement(By.id("inputField"));
        // Use sendKeys() to press the Enter key
        actions.sendKeys(inputField, Keys.ENTER).perform();

    }

    public static void dropDown()
    {
       WebElement dropdownEle=  driver.findElement(By.cssSelector("#dropdown-class-example"));

        Select  select = new Select(dropdownEle);
        //select.selectByValue("Option2");
        //select.selectByVisibleText("option1");
        select.selectByIndex(2);
        System.out.println("successfully selected option2 ");

    }

    public static void SelectRadioBtn() {
        //WebElement radioBtnEle= driver.findElement(By.cssSelector("#radio2"));
        //radioBtnEle.click();

        List<WebElement> radioBtn= driver.findElements(By.xpath("//*[@id='radio-btn-example']/fieldset/label"));
        int numRadioBrn= radioBtn.size();

        for (int i=1;i<=numRadioBrn;i++) {
            WebElement radioBtn1= driver.findElement(By.xpath("//*[@id='radio-btn-example']/fieldset/label[" + i + "]/input"));
            radioBtn1.click();
            System.out.println("Successfully click");
        }


    }

    public static void iFrameHandler(){
        // Switch to a frame by index (0-based)
        driver.switchTo().frame(0);

        // Now you can interact with elements inside the frame
        WebElement elementInsideFrame = driver.findElement(By.id("elementIdInsideFrame"));
        elementInsideFrame.click();

        // Return to the main page
        driver.switchTo().defaultContent();

        // Continue interacting with elements outside the frame
        WebElement elementOutsideFrame = driver.findElement(By.id("elementIdOutsideFrame"));
        elementOutsideFrame.click();


    }

    public static void  VerifyLinksExample()    {
        // Find all the links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        // Iterate through each link and perform verification checks
        for (WebElement link : links) {
            String href = link.getAttribute("href");

            // Check if the link is not empty and starts with "http" (external link)
            if (href != null && !href.isEmpty() && href.startsWith("http")) {
                try {
                    // Use WebDriverWait to wait for the link to load
       /*             WebDriverWait wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.visibilityOf(link));*/

                    // Click the link (optional)
                    link.click();

                    // Verify if the title of the new page is as expected
                    String expectedTitle = "Expected Page Title";
                    if (driver.getTitle().equals(expectedTitle)) {
                        System.out.println("Link is valid: " + href);
                    } else {
                        System.out.println("Link is broken: " + href);
                    }

                    // Go back to the original page
                    driver.navigate().back();
                } catch (Exception e) {
                    System.out.println("Link is broken: " + href);
                }
            }
        }
    }


}
