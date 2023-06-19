package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignPage {
    public WebDriver driver;


    //locators-data members
    By accountLink= By.linkText("My Account");
    By EmailOrAccountNumberInput= By.id("EmailOrAccountNumber");
    By PasswordInput=By.id("Password");
    By SignInNowBtn=By.id("SignInNow");

    //constructor
    public SignPage(WebDriver driver) {
        this.driver = driver;
    }

    //function enter to  my account page
    public void openSignPage()
    {
        driver.findElement(accountLink).click();
    }

   //function fill email for account-get the email
    public void EnterEmailOrAccount(String emailAccount)
    {
       driver.findElement(EmailOrAccountNumberInput).sendKeys(emailAccount);
    }

    //function fill password for account-get the password
    public void EnterPassword (String password)
    {
        driver.findElement(PasswordInput).sendKeys(password);

    }

    //function click on button login
    public void SignPageLogin()
    {
        driver.findElement(SignInNowBtn).click();

    }

}
