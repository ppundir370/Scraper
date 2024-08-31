package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;
import com.google.common.base.Equivalence.Wrapper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
  ChromeDriver driver;

  /*
   * TODO: Write your tests here with testng @Test annotation.
   * Follow `testCase01` `testCase02`... format or what is provided in
   * instructions
   */
  @Test
  public void testCase01() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    driver.get("https://www.scrapethissite.com/pages/");
    wait.until(ExpectedConditions.urlMatches("/pages/"));
    WebElement hockeyLink = driver.findElement(By.xpath("//a[text()='Hockey Teams: Forms, Searching and Pagination']"));
    Wrappers.click(hockeyLink);
    wait.until(ExpectedConditions.urlContains("/pages/forms/"));
    ArrayList<String> TableData = new ArrayList<>();
    // Get the current epoch time
    long epochTime = System.currentTimeMillis();
    String epoch = String.valueOf(epochTime);
    boolean hasNextPage = true;

    while (hasNextPage) {

      ArrayList<String> colvalues = new ArrayList<>();
      // New code added string builder
      StringBuilder sb = new StringBuilder();
      // sb.append("epochTime " + epoch).append(" | ");

      List<WebElement> tableRowsXpath = driver.findElements(By.xpath("//table//tbody//tr"));
      System.out.println("ALL rows integer number " + tableRowsXpath.size());
      for (int i = 2; i <= tableRowsXpath.size(); i++) {
        System.out.println("Rows printed after for loop " + i);

        List<WebElement> columnElements = driver.findElements(By.xpath("//table//tbody//tr[" + i + "]//td"));
        for (int j = 0; j < columnElements.size(); j++) {
          String column5th = columnElements.get(5).getText();
          Double winPercentage = Double.parseDouble(column5th);
          // colvalues.add(epoch);
          if (winPercentage < .40) {
            switch (j) {
              case 0:

                sb.append("epoch time : " + epoch).append(" | ").append("Team Name : ")
                    .append(columnElements.get(j).getText()).append(" ");
                // colvalues.add(columnElements.get(j).getText());
                System.out.println("When the value of j was 0 " + columnElements.get(j).getText());
                break;
              case 1:
                sb.append("Year : ").append(columnElements.get(j).getText()).append(" ");
                // colvalues.add(columnElements.get(j).getText());
                System.out.println("When the value of j was 1 " + columnElements.get(j).getText());
                break;
              case 5:
                sb.append("Win % : ").append(columnElements.get(j).getText()).append(" ");
                // colvalues.add(columnElements.get(j).getText());
                System.out.println("When the value of j was 5 " + columnElements.get(j).getText());
                break;
              default:
                break;
            }
          }
        }

      }
      colvalues.add(sb.toString());
      Thread.sleep(2000);
      TableData.addAll(colvalues);
      System.out.println("The structured data after epoch*************************" + colvalues.add(sb.toString()));
      WebElement nextPagination = driver.findElement(By.xpath("//a[@aria-label='Next']"));
      // wait.until(ExpectedConditions.elementToBeSelected(nextPagination));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].scrollIntoView(true);", nextPagination);
      Thread.sleep(3000);
      if (nextPagination != null && nextPagination.isDisplayed() && nextPagination.isEnabled()) {
        nextPagination.click();
      } else {
        hasNextPage = false;
        System.out.println("Next button is not available");
      }
      Thread.sleep(3000);
      WebElement tableUp = driver.findElement(By.xpath("//table//tbody//tr[2]"));
      js.executeScript("arguments[0].scrollIntoView(true);", tableUp);
      Thread.sleep(3000);
      driver.getCurrentUrl();
      System.out.println(driver.getCurrentUrl());
      System.out.println("The table data arraylist data " + TableData.toString());

      if (driver.getCurrentUrl().endsWith("pages/forms/?page_num=5"))
        break;
    }
    Wrappers.convertArrayListToJson(TableData);

  }

  @Test
  public void testCase02() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    driver.get("https://www.scrapethissite.com/pages/");
    wait.until(ExpectedConditions.urlMatches("/pages/"));
    WebElement OscarLink = driver.findElement(By.xpath("//a[text()='Oscar Winning Films: AJAX and Javascript']"));
    Wrappers.click(OscarLink);
    // Boolean value
    Boolean iSWinner = true;
    long epochTime = System.currentTimeMillis();
    String epoch = String.valueOf(epochTime);
    
    // Click Year on the years page
    List<WebElement> YearsElement = driver.findElements(By.xpath("//a[contains(@class,'year-link')]"));
    // while (hasYear)
    // {
    // Define String builder//
    StringBuilder sb = new StringBuilder();
    ArrayList<String> Data = new ArrayList<>(); // Define arraylist
    for (int i1 = 0; i1 < YearsElement.size(); i1++) {
     // sb.append("Year : " + YearsElement.get(i1).getText()).append(" | ");
      YearsElement.get(i1).click();
      wait.until(
          ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Year to View Films')]")));
      System.out.println(driver.getCurrentUrl());
      Thread.sleep(5000);

      // if(Year.getText().equals("2015"))
      // {

      // Year.click();
      // }
      // Fetching table data
      List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody//tr"));

      for (int i = 1; i <= tableRows.size(); i++) 
      {
        List<WebElement> columnDatOfRows = driver.findElements(By.xpath("//table/tbody//tr[" + i + "]//td"));
        System.out.println("Size of column " + columnDatOfRows.size());
        for (int j = 0; j < columnDatOfRows.size(); j++)
        {
          WebElement flagElement = driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-flag']"));
          // if (driver.getCurrentUrl().contains("2015") ||
          // driver.getCurrentUrl().contains("2014")
          // || driver.getCurrentUrl().contains("2013") ||
          // driver.getCurrentUrl().contains("2012")
          // || driver.getCurrentUrl().contains("2011") ||
          // driver.getCurrentUrl().contains("2010"))
          // ;
          // {
          switch (j) 
          {
            case 0:
              sb.append("epoch time : " + epoch).append(" | ").append("Year : " + YearsElement.get(i1).getText()).append(" | ").append("Title : " + columnDatOfRows.get(0).getText()).append(" | ");
              Thread.sleep(2000);
              break;

            case 1:
              sb.append("Nominations : " + columnDatOfRows.get(1).getText()).append(" | ");
              Thread.sleep(2000);
              break;

            case 2:
              sb.append("Awards : " + columnDatOfRows.get(2).getText()).append(" | ");
              Thread.sleep(2000);
              break;

            case 3:
              // System.out.println("Column Data " + columnDatOfRows.get(3)+"//i");
              if (columnDatOfRows.get(3).getText().trim().isEmpty()) {
                if (flagElement.isDisplayed()) {
                  iSWinner = false;
                  sb.append("Best Picture : " + iSWinner).append(" | ");

                }

              } else {
                sb.append("Best Picturee : " + iSWinner).append(" | ");

              }
              Thread.sleep(2000);
              break;

            default:
              break;

          }

          // }

        }
        if (i == 5)
          break;
      }
     // Data.add(sb.toString());
     
      Thread.sleep(2000);
      System.out.println("Priyanka is the best");

    }
    Data.add(sb.toString());
   // Wrappers.convertArrayListToJson(Data);
    System.out.println("Array list " + Data);

  }

  /*
   * Do not change the provided methods unless necessary, they will help in
   * automation and assessment
   */
  @BeforeTest
  public void startBrowser() {
    System.setProperty("java.util.logging.config.file", "logging.properties");

    // NOT NEEDED FOR SELENIUM MANAGER
    // WebDriverManager.chromedriver().timeout(30).setup();

    ChromeOptions options = new ChromeOptions();
    LoggingPreferences logs = new LoggingPreferences();

    logs.enable(LogType.BROWSER, Level.ALL);
    logs.enable(LogType.DRIVER, Level.ALL);
    options.setCapability("goog:loggingPrefs", logs);
    options.addArguments("--remote-allow-origins=*");

    System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

    driver = new ChromeDriver(options);

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  @AfterTest
  public void endTest() {
    driver.close();
    driver.quit();

  }
}