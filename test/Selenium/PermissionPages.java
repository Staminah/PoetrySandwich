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
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author christop.hirschi
 */
public class PermissionPages {
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
  public void adminToAdminPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/myAdmin/tag/List.xhtml");
    try {
      assertEquals("List", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void editorToAdminPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("anthony");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/myAdmin/tag/List.xhtml");
    try {
      assertEquals("HTTP Status 403 - Forbidden", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void authorToAdminPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("steve");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/myAdmin/tag/List.xhtml");
    try {
      assertEquals("HTTP Status 403 - Forbidden", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void adminToEditorPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/Editor/poem/List.xhtml");
    try {
      assertEquals("Poem Moderation", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void editorToEditorPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("anthony");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/Editor/poem/List.xhtml");
    try {
      assertEquals("Poem Moderation", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void authorToEditorPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("steve");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/Editor/poem/List.xhtml");
    try {
      assertEquals("HTTP Status 403 - Forbidden", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void adminToAuthorPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/Author/poem/Create.xhtml");
    try {
      assertEquals("Create New Poem", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void editorToAuthorPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("anthony");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/Author/poem/Create.xhtml");
    try {
      assertEquals("Create New Poem", driver.findElement(By.xpath("//h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  @Test
  public void authorToAuthorPage() throws Exception {
    driver.get("http://localhost:8080/PoetrySandwich/");
    driver.findElement(By.linkText("personLogin")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("steve");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("okok");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.get("http://localhost:8080/PoetrySandwich/faces/Author/poem/Create.xhtml");
    try {
      assertEquals("Create New Poem", driver.findElement(By.xpath("//h1")).getText());
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