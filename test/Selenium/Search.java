/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selenium;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author christop.hirschi
 */
public class Search {
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
    //pass
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  @Test
  public void titleSearchFromHome() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    new Select(driver.findElement(By.id("j_idt14:inputGroupSelect"))).selectByVisibleText("Title");
    driver.findElement(By.id("j_idt14:search_form")).clear();
    driver.findElement(By.id("j_idt14:search_form")).sendKeys("demo");
    driver.findElement(By.id("j_idt14:btn_search")).click();
    try {
      assertEquals("(No Poem Items Found)", driver.findElement(By.id("j_idt46")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void tagSearchFromHome() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    new Select(driver.findElement(By.id("j_idt14:inputGroupSelect"))).selectByVisibleText("Tag");
    driver.findElement(By.id("j_idt14:search_form")).clear();
    driver.findElement(By.id("j_idt14:search_form")).sendKeys("demo");
    driver.findElement(By.id("j_idt14:btn_search")).click();
    try {
      assertEquals("(No Poem Items Found)", driver.findElement(By.id("j_idt46")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void authorSearchFromHome() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    new Select(driver.findElement(By.id("j_idt14:inputGroupSelect"))).selectByVisibleText("Author");
    driver.findElement(By.id("j_idt14:search_form")).clear();
    driver.findElement(By.id("j_idt14:search_form")).sendKeys("demo");
    driver.findElement(By.id("j_idt14:btn_search")).click();
    try {
      assertEquals("(No Poem Items Found)", driver.findElement(By.id("j_idt46")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
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
