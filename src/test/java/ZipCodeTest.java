import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZipCodeTest {

    @Test
    public void validZipCode(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver  = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();  //можно Continue оставить без кавычек
        /*альтернатива
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();*/

        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]")); //можно взять Register в одинарные кавычки
        Assert.assertTrue(registerButton.isDisplayed(),
                "Register form is not opened ('Register' button is not found)");
        driver.quit();
    }


}
