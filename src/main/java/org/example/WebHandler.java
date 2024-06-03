package org.example;

        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.Select;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import javax.lang.model.element.Element;
        import javax.lang.model.util.Elements;
        import java.util.List;
        import java.util.Set;

public class WebHandler {

    static WebDriver driver;

    public static void main(String args[]) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //driver.get("https://the-internet.herokuapp.com/nested_frames");
        //driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
        Thread.sleep(1000);
        driver.manage().window().maximize();
        windows();
        //dynamicDropDownHandler();
        //ActionHandler();
        driver.quit();
    }

    public static void alertHandler() {
        driver.findElement(By.cssSelector("#alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public static void windowHandler() {
        // To handle a window we can use getWindowHandle() or getWindowHandles() method.
        //it return the window id
        //get the parent window id by using getWindowHandler.
        //This would need when we need to get back to main window.

        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("#openwindow")).click();
        Set<String> childWindows = driver.getWindowHandles();
        for (String childWin : childWindows) {
            if (!childWin.equals(parentWindow)) {
                driver.switchTo().window(childWin);
                System.out.println("successfully navigate");
            }
            driver.switchTo().defaultContent();
            System.out.println("default");

        }


    }
    public static void dropdownHandler() {
        WebElement dropdownLocation = driver.findElement(By.cssSelector("#dropdown-class-example"));
        Select select = new Select(dropdownLocation);
        List<WebElement> elements = select.getOptions();

        for (WebElement ele : elements) {
           // ele.click(); // click one by one
            String dropdownList = ele.getText();
            System.out.println(dropdownList);
            if (dropdownList.equals("option2")) {
                ele.click();
            }
        }

//        select.selectByValue("option2");
//        System.out.println("successfully selected Option2");
//        select.getAllSelectedOptions();
//        select.getFirstSelectedOption();// it will return you default selected value*/
   }
   public static void  dynamicDropDownHandler(){

        driver.findElement(By.cssSelector("#fromCity")).click();
        List<WebElement> cityName = driver.findElements(By.cssSelector("#react-autowhatever-1 > div > ul>li"));
        for (WebElement cities:cityName) {
         String nameofCity= cities.getText();
         if(nameofCity.contains("Hyderabad, India"))
         {
             cities.click();
             break;
         }

       }

   }
   public static void frameHandler(){
        driver.switchTo().frame("frm1");
        WebElement dropdown = driver.findElement(By.cssSelector("#selectnav1"));
       driver.findElement(By.cssSelector("[class='menu section']")).click();
        Select select = new Select(dropdown);
        select.selectByVisibleText("- Java");
       System.out.println("successfully select java from frame 1");
       driver.switchTo().defaultContent();
       driver.switchTo().frame("frm2");
       driver.findElement(By.cssSelector("#firstName")).sendKeys("dhruba");
       System.out.println("Successfully enter the name");
       driver.switchTo().defaultContent();
       String[] frameIds = {"frm1", "frm2", "frm3"};
       for (String frameId: frameIds ) {
           driver.switchTo().frame(frameId);
           System.out.println("i am in frame"+frameId);
       }
   }
   public static void ActionHandler() {

       WebElement mouseOver= driver.findElement(By.cssSelector("#mousehover"));
       WebElement topMenu= driver.findElement(By.xpath("//a[contains(@href, 'top')]"));
       Actions actions = new Actions(driver);
       actions.moveToElement(mouseOver).perform();
       System.out.println("mouseover");
       actions.moveToElement(topMenu).click().perform();
       System.out.println("topMenu");
   }
   public static void windows() {

       String parentId = driver.getWindowHandle();
       driver.findElement(By.cssSelector("#opentab.btn-style")).click();
       Set<String> childId = driver.getWindowHandles();

       for (String ids:childId) {

           if(!ids.equals(parentId))
           {
               driver.switchTo().window(ids);
               String gettittle= driver.getTitle();
               System.out.println(gettittle);

           }

       }



   }

}







