package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    public WebDriver driver;
    //constructor
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }
    //locators-data member
    By paymentOfCardRadio=By.cssSelector("input[value='5']");
    By cardNumberInput=By.id("cardNumber");
    //text=Enter a valid card number
    By expiryMonthCardInput=By.id("expiryMonth");
    By expiryYearCardInput=By.id("expiryYear");
    By securityCodeInput=By.id("securityCode");

    By errorMsgCardNumber=By.id("cardNumber-hint");
    By errorMsgDate =By.id("expiryDate-hint");
    By errorMsgSecurityCode =By.id("securityCode-hint");

    By payNowBtn=By.id("submitButton");

    // function choose pay by card
    public boolean clickPaymentByCard()
    {
        WebElement rbCard=driver.findElement(paymentOfCardRadio);
        rbCard.click();
        if(rbCard.isSelected())
            return true;
       return false;
    }
    //function check the pay by card;
    public boolean checkPaymentByCard ()
    {
           return  driver.findElement(paymentOfCardRadio).isSelected();
    }
    //function enter the num card
    public void enterNumCard(String numCard)
    {

        driver.findElement(cardNumberInput).sendKeys(numCard);
    }
    //function enter expiry month card;
    public void enterMonth(String eMonth)
    {

        driver.findElement(expiryMonthCardInput).sendKeys(eMonth);
    }
    //function enter expiry year card
    public void enterYear(String eYear) {
        driver.findElement(expiryYearCardInput).sendKeys(eYear);
    }
    //function enter security-num card;
    public  void enterSecurityNum(String SecurityNum)
    {
        driver.findElement(securityCodeInput).sendKeys(SecurityNum);
    }
    // function click on button  pay now
    public void clickPayNowBtn()
    {
        driver.findElement(payNowBtn).click();
    }
    //function check the num card correct
    public String checkValidNumCard()
    {
        System.out.println(driver.findElement(errorMsgCardNumber).getText());
        return driver.findElement(errorMsgCardNumber).getText();


    }


}
