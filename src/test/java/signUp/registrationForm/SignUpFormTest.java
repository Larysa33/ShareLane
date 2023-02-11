package signUp.registrationForm;

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

public class SignUpFormTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @DataProvider
    public Object[][] registrationDataAndValidationResult() {
        return new Object[][]{
                {new SignUpForm("Tatyana", "123@tmmcv.com", "12345"), true}, //tests for First Name
                {new SignUpForm("Tatyana1", "124@tmmcv.com", "12345"), false},
                {new SignUpForm("", "125@tmmcv.com", "12345"), false},
                {new SignUpForm("Ab Vd", "126@tmmcv.com", "12345"), true},
                {new SignUpForm("A", "127@tmmcv.com", "12345"), true},
                {new SignUpForm(" ", "128@tmmcv.com", "12345"), false}, //fails - space as a name is a bug
                {new SignUpForm(" Ann", "129@tmmcv.com", "12345"), false}, //fails - name starting from whitespace is a bug
                {new SignUpForm("***", "1210@tmmcv.com", "12345"), false},
                {new SignUpForm("t_a", "1211@tmmcv.com", "12345"), false},
                {new SignUpForm("Татьяна", "1234@tmmcv.com", "12345"), true}, //fails
                {new SignUpForm("Tatyana", "1212@tmmcv.com", "12345"), true}, // tests for email
                {new SignUpForm("Tatyana", "1212@tmmcv.com", "12345"), false}, //fails - previously used email
                {new SignUpForm("Tatyana", "1213tmmcv.com", "12345"), false},
                {new SignUpForm("Tatyana", "1214@tmmcv", "12345"), false},
                {new SignUpForm("Tatyana", "1215@tmmcv.", "12345"), false}, //fails - email without top level domain
                {new SignUpForm("Tatyana", "1216 @tmmcv.com", "12345"), false}, //fails - email with whitespace
                {new SignUpForm("Tatyana", "", "12345"), false},
                {new SignUpForm("Tatyana", " ", "12345"), false},
                {new SignUpForm("Tatyana", " 1217@tmmcv.com", "12345"), false}, // fails - email starts with whitespace
                {new SignUpForm("Tatyana", "1218@tmmcv.com", "1"), false}, //tests for password
                {new SignUpForm("Tatyana", "1219@tmmcv.com", "1234"), true},
                {new SignUpForm("Tatyana", "1220@tmmcv.com", "bbbbb"), true},
                {new SignUpForm("Tatyana", "1221@tmmcv.com", ""), false},
                {new SignUpForm("Tatyana", "1222@tmmcv.com", "\"asd\""), true},
                {new SignUpForm("Tatyana", "1223@tmmcv.com", "    "), true}, // any symbols in password are accepted
                {new SignUpForm("Tatyana", "1224@tmmcv.com", "абвгд"), true},
                {new SignUpForm("Tatyana", "1225@tmmcv.com", "1234а"), true},
                {new SignUpForm("Tatyana", "1226@tmmcv.com", "aaaa*"), true},
                {new SignUpForm("Tatyana", "1227@tmmcv.com", "12345", "1234"), false}, //test for confirmation - fails
                {new SignUpForm("Tatyana", "", "1228@tmmcv.com", "12345", "12345"), true}, //tests for Last Name
                {new SignUpForm("Tatyana", "Tatyana", "1229@tmmcv.com", "12345", "12345"), true},
                {new SignUpForm("Tatyana", "Tat1", "1230@tmmcv.com", "12345", "12345"), false}, //fails - name with digits shouldn't be accepted
                {new SignUpForm("Tatyana", " ", "1231@tmmcv.com", "12345", "12345"), true},
                {new SignUpForm("Tatyana", "***", "1232@tmmcv.com", "12345", "12345"), false}, // fails - name with special chars shouldn't be accepted
                {new SignUpForm("Tatyana", "Абв", "1233@tmmcv.com", "12345", "12345"), true},
        };
    }

    @Test(dataProvider = "registrationDataAndValidationResult")
    public void testRegistrationForm(SignUpForm SignUpFormData, boolean validationResult) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(SignUpFormData.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(SignUpFormData.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(SignUpFormData.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(SignUpFormData.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(SignUpFormData.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.className("confirmation_message")), validationResult,
                "Input data is invalid");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
