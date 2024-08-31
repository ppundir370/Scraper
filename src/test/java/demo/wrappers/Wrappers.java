package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void click(WebElement e)
    {
        e.click();

    }
    public static void convertArrayListToJson(ArrayList<String> TableData)
    {
        // Create an ObjectMapper instance
        ObjectMapper objMap = new ObjectMapper();

        try {
            // Define the path to the JSON file
            File file = new File("src/test/resources/hockey-team-data.json");
           // File file = new File("src/test/resources/oscar-winner-data.json");

            // Write the ArrayList to the JSON file
            if(file.exists())
            {
                objMap.writeValue(file, TableData);
            }
            else
            {
                System.out.println("File not found!");
            }
            objMap.writeValue(file, TableData);

            System.out.println("Data has been written to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
