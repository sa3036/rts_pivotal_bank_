
package e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * End2EndTest
 */
public class End2EndTest extends E2EBase {

    // private String base_url = "http://10.100.244.109:8080";
    // private String base_url = "http://web-ui-service:8080/";

    


    private void login() {
        driver.get(base_url);
        // enter username
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(user);
        //driver.findElement(By.name("username")).sendKeys(user);

        // enter username
        driver.findElement(By.name("password")).clear();
        //driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("password")).sendKeys("test1");

        driver.findElement(By.name("password")).submit();
        //driver.findElement(By.tagName("button")).submit();
    }

    // # test case #1 Access Pivotal Bank web application root url, Login page loads
    @Test
    public void test_login() {
        System.out.println("driver: -> "+ driver + base_url);
        driver.get(base_url);
        System.out.println("It works till here");

        WebElement login_element = driver.findElement(By.tagName("button"));
        assertNotNull(login_element);
        System.out.println(login_element.getText());
        //assertEquals("Sign In", login_element.getText());
        

    }

    // # test case #2 Register a new user, returned back to Login page
    @Test
    public void test_user_registration() {

        // go to homepage
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

    // # test case #3 Submit login form with registered users credentials, Account
    // Home page loads
    @Test // {DependsOn = {"test_user_registration"}}
    public void test_user_login() {

        driver.get(base_url);
        // enter username
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(user);
        //driver.findElement(By.name("username")).sendKeys(user);

        // enter password
        driver.findElement(By.name("password")).clear();
        //driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("password")).sendKeys("test1");

        driver.findElement(By.name("password")).submit();

        // the current url should be home page url
        System.out.println(driver.getCurrentUrl());
        assertEquals(base_url+"/", driver.getCurrentUrl());

    }

    // # test case #4 Choose Create Account button, Create Account page loads
    @Test
    public void test_user_account_creation_initialization() {

        login();

        driver.findElement(By.linkText("Create Account")).click();
        assertEquals(base_url + "/openaccount", driver.getCurrentUrl());

    }

    // # test case #5 Submit Create Account form, Open Account page loads, showing
    // the account

    @Test
    public void test_user_account_creation_form_submission() {

        login();
        driver.findElement(By.linkText("Create Account")).click();
        
        driver.findElement(By.id("name-input")).clear();
        driver.findElement(By.id("name-input")).sendKeys(user_account_name);

        driver.findElement(By.id("accountCreateBtn")).submit();
        //driver.findElement(By.linkText("Create Acccount")).click();

        // url should be /oepnaccount
        System.out.println(driver.getCurrentUrl());

    }

    // # test case #6 Click the Trade link at the top, Trade page loads
    @Test
    public void test_user_trading_page() {

        login();
        driver.findElement(By.id("nb-trade")).click();
        System.out.println(driver.getCurrentUrl());
        assertEquals(base_url + "/trade", driver.getCurrentUrl());

    }

    // # test case #7 Enter stock ticker symbol and click the Get Quote button,
    // quote appears in table

    @Test
    public void test_get_quotes() {

        login();
        driver.findElement(By.id("nb-trade")).click();

        String facebook_symbol = "FB";
        driver.findElement(By.id("quote-input")).clear();
        driver.findElement(By.id("quote-input")).sendKeys(facebook_symbol);

        driver.findElement(By.id("quote-input")).submit();
        //driver.findElement(By.linkText("Get Quotes")).click();

        // should remain on the link but fetch details of quotes
        System.out.println(driver.getCurrentUrl());

        WebElement table = driver.findElement(By.id("list-of-quotes"));

        assertNotNull(table);

    }

    // # test case #8 Enter number of shares and click the Buy button, Order page
    // appers with Order Successful message
    // @Test
    // public void test_buy_shares() {

    //     login();
    //     driver.findElement(By.id("nb-trade")).click();
    //     String facebook_symbol = "FB";
    //     driver.findElement(By.id("quote-input")).clear();
    //     driver.findElement(By.id("quote-input")).sendKeys(facebook_symbol);

    //     driver.findElement(By.id("quote-input")).submit();

    //     driver.findElement(By.name("quantity")).clear();
    //     driver.findElement(By.name("quantity")).sendKeys("10");

    //     driver.findElement(By.id("buyBtn")).click();

    //     // should be redirected to the order page

    //     assertEquals(base_url + "/order", driver.getCurrentUrl());
    // }

    // # test case #9 Click Accounts link at top, Accounts page appears
    @Test
    public void test_user_accounts_details() {

        login();
        driver.findElement(By.linkText("Accounts")).click();

        assertEquals(base_url + "/accounts", driver.getCurrentUrl());
    }

    // # test case #10 Click Portfolio link at top, Portfolio page appears
    @Test
    public void test_user_portfolio_check() {

        login();
        driver.findElement(By.linkText("Portfolio")).click();

        assertEquals(base_url + "/portfolio", driver.getCurrentUrl());
    }

    // # test case #11 Click Home link at top, Home page appears
    @Test
    public void test_user_home_page() {

        login();
        driver.findElement(By.linkText("Portfolio")).click();

        driver.findElement(By.linkText("Home")).click();
        assertEquals(base_url +"/", driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}