package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xml.sax.SAXException;
import pages.HomePage;
import pages.PaymentPage;
import pages.ProductPage;
import pages.SignPage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCase {

    //instance definition of the pages
    HomePage homePage=new HomePage(driver);
    SignPage signPage=new SignPage(driver);
    ProductPage productPage=new ProductPage(driver);
    PaymentPage paymentPage=new PaymentPage(driver);
    public  static ExtentSparkReporter spark=new ExtentSparkReporter(Constants.REPORT_URL);
    public static ExtentReports report=new ExtentReports();
    String currentTime = String.valueOf(System.currentTimeMillis());
    private static WebDriver driver;

    //connection to webdriver chrome-version 114
    @BeforeClass
    public static void beforeAll() throws ParserConfigurationException, IOException, SAXException {
        if(GlobalFunctions.getData("browser-type").equalsIgnoreCase(Constants.TYPE_DRIVER_CHROME)) {
            System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_URl);
            driver = new ChromeDriver();
        }
        else{
            //for other driver ff ,ie...
        }
        driver.get(GlobalFunctions.getData("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        report.attachReporter((spark));
        spark.config().setReportName("My Report");
    }

    @Before
        public  void beforeClass()
        {

        }

   //test - enter to NEXT website
    @Ignore
    @Test
        public void loginNext() throws IOException {
        ExtentTest loginNextTest = report.createTest("NextPageTest");
        loginNextTest.log(Status.INFO, "enter to next website test");
        try {
            Assert.assertEquals(Constants.EXPECT_URL_NEXT_EN, driver.getCurrentUrl());
            loginNextTest.pass("NextPageTest passed");
            }
        catch (AssertionError error) {
            loginNextTest.fail("homePageTest fail: get url "+driver.getCurrentUrl(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot( Constants.SCREENSHOT_URL+ currentTime+"homePageTest")).build());
        }
    }

    //test1-fill details in sign page
   //@Ignore
    @Test
      public  void signPage() throws ParserConfigurationException, IOException, SAXException {
        ExtentTest signPageTest = report.createTest("test1-signPageTest");
        signPageTest.log(Status.INFO, "fill detail in sign page and login");
        //open the login page
        signPage.openSignPage();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_URL_LOGIN_PAGE.equalsIgnoreCase(driver.getCurrentUrl()))
            signPageTest.pass("signPageTest succeeded to enter to login page");
        else signPageTest.fail("signPageTest succeeded to enter to login page", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"signPageTest1")).build());
        //function fill the emailaddress -detail send from xml file
        String EmailAddressXml= GlobalFunctions.getData("email-address");
        signPage.EnterEmailOrAccount(EmailAddressXml);
        GlobalFunctions.sleepWait();
        //function fill the password -detail send from xml file
        String PasswordXml= GlobalFunctions.getData("password");
        signPage.EnterPassword(PasswordXml);
        GlobalFunctions.sleepWait();
        //enter on login button
        signPage.SignPageLogin();
        try {
            Assert.assertEquals(Constants.EXPECT_URL_AFTER_LOGIN, driver.getCurrentUrl());
            signPageTest.pass("signPageTest succeeded to login with the user");
        } catch (AssertionError error) {
            signPageTest.fail("signPageTest didn't succeeded to login with the user", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"signPageTest2")).build());
        }
        //the next site doesn't work with automation code  so I need to do navigation
        driver.navigate().to(Constants.EXPECT_URL_NEXT_EN);
    }
    //test2- navigation in the website
    @Ignore
    @Test
    public void homePageTest() throws IOException {
        ExtentTest homePageTest = report.createTest("test2-homePageTest");
        homePageTest.log(Status.INFO, "navigation in the website");
        //enter to home page (the link in this page)
        driver.navigate().to(Constants.EXPECT_URL_HOME_PAGE);

        //step1 living  Room Left link
        homePage.leftLink();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_URL_LIVINGROOM.equalsIgnoreCase(driver.getCurrentUrl()))
            homePageTest.pass("step1: living  Room Left link is succeeded");
        else  homePageTest.fail("step1: living  Room Left link isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"homePageTest1")).build());
        driver.navigate().to(Constants.EXPECT_URL_HOME_PAGE);

         //step2 bedRoom top link
        homePage.topLink();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_URL_BEDROOM.equalsIgnoreCase(driver.getCurrentUrl()))
            homePageTest.pass("step2: bedRoom top link is succeeded");
        else  homePageTest.fail("step2: bedRoom top link isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"homePageTest2")).build());
        driver.navigate().to(Constants.EXPECT_URL_HOME_PAGE);

        //step3-home tab banner link
        homePage.homeCategoryDoubleClick();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_URL_HOME_PAGE.equalsIgnoreCase(driver.getCurrentUrl()))
            homePageTest.pass("step3: home tab banner link is succeeded");
        else  homePageTest.fail("step3: home tab banner link isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"homePageTest3")).build());
        driver.navigate().to(Constants.EXPECT_URL_HOME_PAGE);

        //step4-change language to hebrew
       homePage.changeLanguage();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_URL_NEXT_HE.equalsIgnoreCase(driver.getCurrentUrl()))
            homePageTest.pass("step4: change language to hebrew is succeeded");
        else  homePageTest.fail("step4: change language to hebrew isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"homePageTest4")).build());

        //change to english for the  step5
        driver.navigate().to(Constants.EXPECT_URL_NEXT_EN);
        GlobalFunctions.sleepWait();

        //step5-search product in input search get product from file
        homePage.searchProduct(Constants.SEARCH_PRODUCT);
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_PRODUCT_SHIRT_URL.equalsIgnoreCase(driver.getCurrentUrl()))
            homePageTest.pass("step5: search shirt  is succeeded");
        else  homePageTest.fail("step5: search shirt isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"homePageTest5")).build());

    }
    //test3-choose product change size and color+add to bag
    @Ignore
    @Test
    public void chooseProduct() throws IOException {
        ExtentTest productTest = report.createTest("test3-productTest");
        productTest.log(Status.INFO, "choose product and add from the list ");

        // step1- choose shirt of boys from the list
        driver.get(Constants.EXPECT_PRODUCT_SHIRT_URL);
        GlobalFunctions.sleepWait();
        productPage.chooseDetailsItem();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECT_SHIRT_BOY_URL.equalsIgnoreCase(driver.getCurrentUrl()))
            productTest.pass("step1: choose shirt of boys from the list is succeeded ");
        else  productTest.fail("step1: choose shirt of boys from the list isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"productTest1")).build());

        //step2 -choose size for the item
        String valSize=productPage.chooseSize();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECTED_VALUE_SIZE.equalsIgnoreCase(valSize))
            productTest.pass("step2: choose size 12-18 is succeeded ");
        else  productTest.fail("step2: choose size 12-18 isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"productTest2")).build());

        //step3=choose color for the item
        String valColor=productPage.chooseColor();
        GlobalFunctions.sleepWait();
        if(Constants.EXPECTED_VALUE_COLOR.equalsIgnoreCase(valColor))
            productTest.pass("step3: choose color pink is succeeded ");
        else  productTest.fail("step3: choose color pink isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"productTest3")).build());

        //add item to the bag
        boolean valItems=productPage.addToBagItem();
        GlobalFunctions.sleepWait();
        if(valItems)
            productTest.pass("step4: add item to the bag is succeeded ");
        else  productTest.fail("step4: add item to the bag isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"productTest4")).build());
        GlobalFunctions.sleepWait();

        //add item to the bag again
         valItems=productPage.addToBagItem();
        GlobalFunctions.sleepWait();
        if(valItems)
            productTest.pass("step5: add item to the bag again is succeeded ");
        else  productTest.fail("step5: add item to the bag again isn't succeeded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"productTest5")).build());
        GlobalFunctions.sleepWait();

        // click on payment button
        productPage.clickForPayment();
        GlobalFunctions.sleepWait();
        try {
            Assert.assertEquals(Constants.EXPECT_URL_CHECKOUT, driver.getCurrentUrl());
            productTest.pass("step6: open the page of payment is succeeded ");
        } catch (AssertionError error) {
            productTest.fail("step6: open the page of payment isn't succeeded ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"productTest6")).build());
        }
    }
    //test4-for payment
    @Ignore
    @Test
    public void paymentTest() throws ParserConfigurationException, IOException, SAXException {
        ExtentTest paymentTest=report.createTest("test4-paymentTest");
        paymentTest.info("enter details of card and pay now");
        String DetailsCard="";

        //check pay by card
        paymentPage.clickPaymentByCard();
        GlobalFunctions.sleepWait();
        if(paymentPage.checkPaymentByCard())
            paymentTest.pass("step1: payment by card is selected");
        else paymentTest.fail("step1: payment by card isn't selected ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"paymentTest1")).build());
        GlobalFunctions.sleepWait();
         //enter num of card from xml file
        DetailsCard = GlobalFunctions.getData("num-card");
        paymentPage.enterNumCard(DetailsCard);
        GlobalFunctions.sleepWait();

         //enter  month of card from xml file
        DetailsCard = GlobalFunctions.getData("month");
        paymentPage.enterMonth(DetailsCard);
        GlobalFunctions.sleepWait();

        //enter  year of card from xml file
        DetailsCard = GlobalFunctions.getData("year");
        paymentPage.enterYear(DetailsCard);
        GlobalFunctions.sleepWait();

        //enter  security num of card from xml file
        DetailsCard = GlobalFunctions.getData("security-num");
        paymentPage.enterSecurityNum(DetailsCard);
        GlobalFunctions.sleepWait();
        paymentPage.clickPayNowBtn();
        GlobalFunctions.sleepWait();

        //check validation of num card
        String errorMsgNumCard=paymentPage.checkValidNumCard();
        try {
            Assert.assertEquals(Constants.ERROR_MSG_NUM_CARD,errorMsgNumCard);
            paymentTest.pass("step2: the num of card is correct");
        } catch (AssertionError error) {
            paymentTest.fail("step2: the num of card isn't correct ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.SCREENSHOT_URL + currentTime+"paymentTest1")).build());
        }
    }

    @After
    public  void afterClass()
    {
    }
    @AfterClass
    public static void afterAll()
    {
        driver.quit();
        report.flush();
    }
//function for create the screenshot
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}
