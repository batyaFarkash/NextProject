package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testCases.Constants;
import testCases.GlobalFunctions;

public class ProductPage {
    public WebDriver driver;
    //constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators-data members
    By CheckItem=By.xpath("//*[@id=\"platform_modernisation_product_summary_C24385\"]/div/div[1]/div[1]/div/div/div[1]/a");
    By colorInput=By.cssSelector("[id*='dk_container_Colour-']");
    By colorList=By.xpath("//*[@id=\"dk_container_Colour-128473\"]/div/ul/li[1]/a");
    By colorValue=By.xpath("//*[@id=\"dk_container_Colour-128473\"]/a");
    By sizeInput=By.cssSelector("[id*='dk_container_Size-']");
    By sizeList=By.xpath("//*[@id=\"dk_container_Size-C24-385\"]/div/ul/li[5]/a");
    By sizeValue=By.xpath("//*[@id=\"dk_container_Size-C24-385\"]/a");
    By addToBagBtn=By.xpath("//*[@id=\"Style128473\"]/section/div[4]/div[5]/div[4]/div/a[1]");
   By bagHeader=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[7]/div[2]/a/div");
    By checkOutBtn=By.linkText("CHECKOUT");

    //function choose item from the list
    public void chooseDetailsItem()
    {
        driver.findElement(CheckItem).click();
    }

    //function choose color for the item
    public String chooseColor()
    {
        driver.findElement(colorInput).click();
        GlobalFunctions.sleepWait();
        driver.findElement(colorList).click();
        GlobalFunctions.sleepWait();
       return driver.findElement(colorValue).getText();
    }
    //function choose size for the item
    public String chooseSize()
    {
        driver.findElement(sizeInput).click();
        GlobalFunctions.sleepWait();
        driver.findElement(sizeList).click();
        GlobalFunctions.sleepWait();
        return driver.findElement(sizeValue).getText();
    }
    public boolean addToBagItem()
    {
        String sumItemBagBeforeAdd=driver.findElement(bagHeader).getText();
        int numItemBagBeforeAdd=Integer.parseInt(sumItemBagBeforeAdd);
        //System.out.println(sumItemBagBeforeAdd+" sumItemBagBeforeAdd");
        driver.findElement(addToBagBtn).click();
        GlobalFunctions.sleepWait();
        String sumItemBagAfterAdd=driver.findElement(bagHeader).getText();
        int numItemBagAfterAdd=Integer.parseInt(sumItemBagAfterAdd);
        if(numItemBagAfterAdd-numItemBagBeforeAdd==1) {
            return true;
        }
        return false;
    }
    //function enter for payment
    public void clickForPayment()
    {
        driver.findElement(checkOutBtn).click();
    }

}
