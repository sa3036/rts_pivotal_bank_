package e2e;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
// import com.jayway.restassured.RestAssured;
// import com.jayway.restassured.filter.log.ErrorLoggingFilter;
// import com.jayway.restassured.filter.log.RequestLoggingFilter;
// import com.jayway.restassured.filter.log.ResponseLoggingFilter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest()
// @SpringBootConfiguration
public class E2EBase {

    @Value("${application.url:http://localhost:32000}")
    static String applicationUrl;

    Faker faker = Faker.instance();

    static WebDriver driver;

    static String user = "test"+ new Random().nextInt();
    static String password = "test1";
    static String email = "test@gmail.com";
    static String address = "Vancouver, Canada";
    static String full_name = "user";

    static String user_account_name = "Account_1";

    static String base_url = "http://localhost:8080";

    @Before
    public void setUp() throws Exception {
        System.out.println("In Before Method setup()");
        // RestAssured.baseURI = applicationUrl;
        // RestAssured.useRelaxedHTTPSValidation();
        // RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());

        //set up for chrome driver 
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--disable-dev-shm-usage");
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    
    }


    @BeforeClass
    public static void setUpUserAccount() throws Exception {

        // LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(Logger.ROOT_LOGGER_NAME, LogLevel.INFO);

        System.out.println("In Before class setup()");
        //set up for chrome driver 
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--disable-dev-shm-usage");
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        //create an account 
        // driver.get("http://web-ui-service:8080");
        driver.get(base_url);

        WebElement register = driver.findElement(By.linkText("Create One"));
        register.click();
        System.out.println(driver.getCurrentUrl());
        // Wait for the new window or tab
        // wait.until(numberOfWindowsToBe(2));
        System.out.println(driver.getWindowHandles().size());

        // //fill all the reqinuired details
        driver.findElement(By.name("fullname")).clear();
        driver.findElement(By.name("fullname")).sendKeys(full_name);

        System.out.println(driver.findElement(By.name("fullname")).getText());

        driver.findElement(By.id("email-input")).clear();
        driver.findElement(By.id("email-input")).sendKeys(email);

        driver.findElement(By.id("reg-password-input")).clear();
        driver.findElement(By.id("reg-password-input")).sendKeys(password);

        driver.findElement(By.id("matchpasswd-input")).clear();
        driver.findElement(By.id("matchpasswd-input")).sendKeys(password);

        driver.findElement(By.id("reg-username-input")).clear();
        driver.findElement(By.id("reg-username-input")).sendKeys(user);

        driver.findElement(By.id("address-input")).clear();
        driver.findElement(By.id("address-input")).sendKeys(address);

        System.out.println("user:-> " + user + "  password:-> " + password);

        // click register buttontest_user_logintest_user_logintest_user_login
        driver.findElement(By.id("registrationBtn")).click();

    }

    @After
    public void tear_down(){
        driver.quit();
    }
}
