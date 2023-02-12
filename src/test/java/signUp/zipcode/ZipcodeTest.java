package signUp.zipcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.UtilMethods;

public class ZipcodeTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");
        driver  = new ChromeDriver(options);
    }

    @Test
    public void validZipcodeTest(){
        boolean isValid=true;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();  //можно Continue оставить без кавычек
        /*альтернатива
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();*/

        //можно взять Register в одинарные кавычки
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @Test
    public void emptyZipcodeTest(){
        boolean isValid=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        //можно взять Register в одинарные кавычки
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @Test
    public void textInputZipcodeTest(){
        boolean isValid=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("abcde");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        //можно взять Register в одинарные кавычки
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @Test
    public void specialSymbolsInputZipcodeTest(){
        boolean isValid=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("*#!?*");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        //можно взять Register в одинарные кавычки
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @Test
    public void zipcodeBoundaryTestingNegative(){
        boolean isValid=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("1234");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        //можно взять Register в одинарные кавычки
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @Test
    public void zipcodeBoundaryTestingPositive(){
        boolean isValid=true;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123456");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @Test
    public void maxNumberOfEnteredDigitsTest(){
        boolean isValid=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345123451234512345123451234512345123451234512" +
                "34512345123451234512345123451234512345123451234512345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value=Register]")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @DataProvider
    public Object[][] zipcodeValuesWithSpaces() {
        return new Object[][]{
                {" 12345", false},
                {"123 45", false},
                {"12345 ", false},
        };
    }

    @Test(dataProvider = "zipcodeValuesWithSpaces")
    public void zipcodeWithSpacesTest(String zipCode, boolean isValid) {
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys(String.valueOf(zipCode));
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.cssSelector("input[value='Register']")), isValid,
                "Register form is not opened ('Register' button is not found)");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
