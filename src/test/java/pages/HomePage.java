package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import testCases.GlobalFunctions;
import testCases.TestCase;

public class HomePage {
    public WebDriver driver;
    //constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    //members-locators
    By  homeCategoryLink=By.cssSelector("a[title='HOME']");
    By livingRoomLeftLink=By.linkText("Living Room");
    By bedroomTopLink=By.linkText("Bedroom");
    By languageBtn=By.cssSelector("button[class='header-lpjoj4']");
    By hebrewBtn=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[4]/div/button[1]");

    By englishBtn=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[4]/div/button[2]");
    By shopNowBtn=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[5]/button");
    By screenSearchInput=By.id("header-big-screen-search-box");
    By screenSearchBtn=By.xpath("//*[@id=\"header-search-form\"]/button");

    //function choice livingRoom  in left side link
    public void leftLink()
    {
        driver.findElement(livingRoomLeftLink).click();
    }

    // function choice bedroom in top link
    public  void topLink()
    {
        driver.findElement(bedroomTopLink).click();
    }

    //function choice homeCategory by doubleClick from the banner
    public void homeCategoryDoubleClick()
    {
        Actions action=new Actions(driver);
        action.doubleClick(driver.findElement(homeCategoryLink)).perform();
    }
    //function change language to hebrew
    public void changeLanguage()
    {
        driver.findElement(languageBtn).click();
        GlobalFunctions.sleepWait();
        if(driver.findElement(hebrewBtn).isSelected()) {
            //change to english language
            driver.findElement(englishBtn).click();
        }
        else {
            //change to hebrew language
            driver.findElement(hebrewBtn).click();
        }
        driver.findElement(shopNowBtn).click();
    }
    //function search product in input search get product from file
    public void searchProduct(String searchProductName)
    {
         driver.findElement(screenSearchInput).sendKeys(searchProductName);
         driver.findElement(screenSearchBtn).click();
    }
}
