/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selenium;

import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author christop.hirschi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Authentification {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  static private String username = "";
  static private String password = "";

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:/temp/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://www.he-arc.ch/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @BeforeClass
  public static void setUpClass() {
    Random random = new Random();
    username = "selenium" + String.valueOf(random.nextInt());
    password = username;  
  }

  @Test
  public void A_newNonExistingUser() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.xpath("//form[@id='j_idt31']/a/i")).click();
    //driver.get("http://localhost:8080/PoetrySandwich/faces/register.xhtml");
    driver.findElement(By.id("j_idt46:username")).clear();
    driver.findElement(By.id("j_idt46:username")).sendKeys(username);
    driver.findElement(By.id("j_idt46:password")).clear();
    driver.findElement(By.id("j_idt46:password")).sendKeys(password);
    driver.findElement(By.id("j_idt46:mail")).clear();
    driver.findElement(By.id("j_idt46:mail")).sendKeys(username + "@selenium.com");
    driver.findElement(By.linkText("Register")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { 
            if ("User was successfully created.".equals(driver.findElement(By.xpath("//div[@id='messagePanel']/table/tbody/tr/td")).getText())) break; 
        } catch (Exception e) {
            
        }
    	Thread.sleep(1000);
    }
  }
  
  @Test
  public void B_newExistingUser() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.xpath("//form[@id='j_idt31']/a/i")).click();
    //driver.get("http://localhost:8080/PoetrySandwich/faces/register.xhtml");
    driver.findElement(By.id("j_idt46:username")).clear();
    driver.findElement(By.id("j_idt46:username")).sendKeys(username);
    driver.findElement(By.id("j_idt46:password")).clear();
    driver.findElement(By.id("j_idt46:password")).sendKeys("notthatnew1234");
    driver.findElement(By.id("j_idt46:mail")).clear();
    driver.findElement(By.id("j_idt46:mail")).sendKeys("notthatnew@selenium.com");
    driver.findElement(By.linkText("Register")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { 
            if (!"User was successfully created.".equals(driver.findElement(By.xpath("//div[@id='messagePanel']/table/tbody/tr/td")).getText())) break; 
        } catch (Exception e) {
            e.printStackTrace();
        }
    	Thread.sleep(1000);
    }
  }

  @Test
  public void C_loginCorrectUser() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    try {
      assertEquals("Welcome " + username + "!", driver.findElement(By.id("welcome_box")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void D_loginWrongUsername() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username + "abc");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    try {
      assertEquals("Sorry, you made an error. Please try again!", driver.findElement(By.cssSelector("div.alert.alert-danger")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void E_loginWrongPassword() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password + "1234");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    try {
      assertEquals("Sorry, you made an error. Please try again!", driver.findElement(By.cssSelector("div.alert.alert-danger")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
