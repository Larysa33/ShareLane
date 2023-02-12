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
    public Object[][] requiredFieldsData() {
        return new Object[][]{
                {new SignUpForm("Test", "test@gmail.com", "12345", "12345"), true}
        };
    }

    @Test(dataProvider = "requiredFieldsData")
    public void signUpWithFilledOutRequiredFields(SignUpForm SignUpFormData, boolean validationResult){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(SignUpFormData.getFirstName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(SignUpFormData.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(SignUpFormData.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(SignUpFormData.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.className("confirmation_message")), validationResult,
                "All required fields must be filled out.");
    }

    @DataProvider
    public Object[][] allFieldsData() {
        return new Object[][]{
                {new SignUpForm("Test", "Test","test@gmail.com", "12345", "12345"), true}
        };
    }

    @Test(dataProvider = "allFieldsData")
    public void signUpWithAllFilledOutFields(SignUpForm SignUpFormData, boolean validationResult){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(SignUpFormData.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        firstNameInput.sendKeys(SignUpFormData.getFirstName());
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

    @Test
    public void signUpWithAllEmptyFields(){
        boolean validationResult=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        Assert.assertEquals(UtilMethods.isElementDisplayed(driver, By.className("confirmation_message")), validationResult,
                "All required fields must be filled out.");
    }

    @DataProvider
    public Object[][] firstNameFieldTestData() {
        return new Object[][]{
                {new SignUpForm("test", "111@gmail.com", "12345"), true},
                {new SignUpForm("TEST", "222@gmail.com", "12345"), true},
                {new SignUpForm("test123", "789@gmail.com", "12345"), false},
                {new SignUpForm("*#!&_", "999@gmail.com", "12345"), false},
                {new SignUpForm("катя", "333@gmail.com", "12345"), false},
                {new SignUpForm("", "444@gmail.com", "12345"), false},
                {new SignUpForm("First Name", "555@gmail.com", "12345"), true},
                {new SignUpForm("t", "666@gmail.com", "12345"), true},
                {new SignUpForm(" ", "777@gmail.com", "12345"), false},
                {new SignUpForm(" test", "888@gmail.com", "12345"), false},
                {new SignUpForm("test ", "123@gmail.com", "12345"), false},
                {new SignUpForm("test_test", "456@gmail.com", "12345"), false}
        };
    }

    @Test(dataProvider = "firstNameFieldTestData")
    public void firstNameFieldTest(SignUpForm SignUpFormData, boolean validationResult) {
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
                "Data in the field 'First Name' is invalid.");
    }

    @DataProvider
    public Object[][] lastNameFieldTestData() {
        return new Object[][]{
                {new SignUpForm("test", "", "111@mail.ru", "12345", "12345"), true},
                {new SignUpForm("test", "test", "222@mail.ru", "12345", "12345"), true},
                {new SignUpForm("test", "test123", "333@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test", "*!#&", "444@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test", "TEST", "555@mail.ru", "12345", "12345"), true},
                {new SignUpForm("test", "смирнова", "666@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test", "last name", "777@mail.ru","12345", "12345"), true},
                {new SignUpForm("test","t","888@mail.ru" ,"12345", "12345"), true},
                {new SignUpForm("test", " ","999@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test"," test", "123@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test", "test ", "456@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test", "test_test","789@mail.ru",  "12345", "12345"), false}
        };
    }

    @Test(dataProvider = "lastNameFieldTestData")
    public void lastNameFieldTest(SignUpForm SignUpFormData, boolean validationResult) {
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
                "Data in the field 'Last Name' is invalid.");
    }

    @DataProvider
    public Object[][] emailFieldTestData() {
        return new Object[][]{
                {new SignUpForm("test", "123@yahoo.com", "12345"), true},
                {new SignUpForm("test", "123@yahoo.com", "12345"), false}, //previously used email
                {new SignUpForm("test", "111yahoo.com", "12345"), false}, //without @
                {new SignUpForm("test", "111@yahoo", "12345"), false}, //without ".com"
                {new SignUpForm("test", "111@yahoo.", "12345"), false}, //without domain
                {new SignUpForm("test", "1234 @yahoo.com", "12345"), false}, //with space
                {new SignUpForm("test", "", "12345"), false},
                {new SignUpForm("test", " ", "12345"), false},
                {new SignUpForm("test", " 222@yahoo.com", "12345"), false},
                {new SignUpForm("test", "333@yahoo.com ", "12345"), false},
        };
    }

    @Test(dataProvider = "emailFieldTestData")
    public void emailFieldTest(SignUpForm SignUpFormData, boolean validationResult) {
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
                "Data in the field 'Email' is invalid.");
    }

    @DataProvider
    public Object[][] passwordFieldTestData() {
        return new Object[][]{
                {new SignUpForm("test", "111@yandex.ru", "123"), false},
                {new SignUpForm("test", "222@yandex.ru", "1234"), true},
                {new SignUpForm("test", "333@yandex.ru", "12345"), true},
                {new SignUpForm("test", "444@yandex.ru", "bbbb"), true},
                {new SignUpForm("test", "555@yandex.ru", ""), false},
                {new SignUpForm("test", "666@yandex.ru", "12 34"), false}, //space in the middle
                {new SignUpForm("test", "777@yandex.ru", " 1234"), false}, //space before password
                {new SignUpForm("test", "888@yandex.ru", "1234 "), false}, //space after password
                {new SignUpForm("test", "999@yandex.ru", "\"asd\""), false},
                {new SignUpForm("test", "123@yandex.ru", "абвг"), false},
                {new SignUpForm("test", "456@yandex.ru", "*!#&"), true},
                {new SignUpForm("test", "789@yandex.ru", "1234", "12345"), false}, //password confirmation
        };
    }

    @Test(dataProvider = "passwordFieldTestData")
    public void passwordFieldTest(SignUpForm SignUpFormData, boolean validationResult) {
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
                "Data in the field 'Password' is incorrect.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
