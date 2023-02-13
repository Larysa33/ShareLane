package signUp.zipcode;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ZipcodeTest extends BaseTest {

    @DataProvider
    public Object[][] validZipcodeValues() {
        return new Object[][]{
                {"12345"},
                {"123456"}
        };
    }

    @Test(dataProvider = "validZipcodeValues")
    public void validZipcodeTest(String zipCode) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys(String.valueOf(zipCode));
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        /* альтернатива
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        */
        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
        Assert.assertTrue(registerButton.isDisplayed(), "Register form is not opened ('Register' button is not found).");
    }

    @DataProvider
    public Object[][] invalidZipcodeValues() {
        return new Object[][]{
                {""},
                {"abcde"},
                {"*#!?."},
                {"1234"},
                {"1234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345"}, //fails, должно быть ограничение на ввод символов
                {" 12345"},
                {"123 45"},
                {"12345 "},
                {" "}
        };
    }

    @Test(dataProvider = "invalidZipcodeValues")
    public void zipcodeNegativeTest(String zipCode) {
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys(String.valueOf(zipCode));
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        WebElement errorMessage = driver.findElement(By.className("error_message")); //By.xpath("//*[text()='Oops, error on page. ZIP code should have 5 digits']"))
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not found."); //отваливается для теста на максимальное кол-во цифр
    }
}
