package signUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpFormTest extends BaseTest {

    @DataProvider
    public Object[][] requiredFieldsData() {
        return new Object[][]{
                {new SignUpForm("Test", "test@gmail.com", "12345", "12345")}
        };
    }

    @Test(dataProvider = "requiredFieldsData")
    public void signUpWithFilledOutRequiredFields(SignUpForm signUpForm){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(), "Check filled out data in the required fields.");
    }

    @DataProvider
    public Object[][] allFieldsData() {
        return new Object[][]{
                {new SignUpForm("Test", "Test","test@gmail.com", "12345", "12345")}
        };
    }

    @Test(dataProvider = "allFieldsData")
    public void signUpWithAllFilledOutFields(SignUpForm signUpForm){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(), "Check filled out data.");
    }

    @Test
    public void signUpWithAllEmptyFields(){
        boolean validationResult=false;
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "It's possible to register with all empty fields.");
    }

    @DataProvider
    public Object[][] firstNameValidData() {
        return new Object[][]{
                {new SignUpForm("test", "111@gmail.com", "12345")},
                {new SignUpForm("TEST", "222@gmail.com", "12345")},
                {new SignUpForm("First Name", "555@gmail.com", "12345")},
                {new SignUpForm("t", "666@gmail.com", "12345")}
        };
    }

    @Test(dataProvider = "firstNameValidData")
    public void firstNamePositiveTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(), "Check data in 'First name' field.");
    }

    @DataProvider
    public Object[][] firstNameInvalidData() {
        return new Object[][]{
                {new SignUpForm("test123", "789@gmail.com", "12345")},
                {new SignUpForm("*#!&_", "999@gmail.com", "12345")},
                {new SignUpForm("катя", "333@gmail.com", "12345")},
                {new SignUpForm("", "444@gmail.com", "12345")},
                {new SignUpForm(" ", "777@gmail.com", "12345")},
                {new SignUpForm(" test", "888@gmail.com", "12345")},
                {new SignUpForm("test ", "123@gmail.com", "12345")},
                {new SignUpForm("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest",
                        "456@gmail.com", "12345")}
        };
    }

    @Test(dataProvider = "firstNameInvalidData")
    public void firstNameNegativeTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "It's possible to register with invalid data in 'First name' field.");
    }

    @DataProvider
    public Object[][] lastNameValidData() {
        return new Object[][]{
                {new SignUpForm("test", "", "111@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "test", "222@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "TEST", "555@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "смирнова", "666@mail.ru", "12345", "12345"), false},
                {new SignUpForm("test", "last name", "777@mail.ru","12345", "12345")},
                {new SignUpForm("test","t","888@mail.ru" ,"12345", "12345")}
        };
    }

    @Test(dataProvider = "lastNameValidData")
    public void lastNamePositiveTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        //WebElement successMessage = driver.findElement(By.xpath("//span[(text)='Account is created!']"));
        Assert.assertTrue(successMessage.isDisplayed(), "Check data in 'Last name' field.");
    }

    @DataProvider
    public Object[][] lastNameInvalidData() {
        return new Object[][]{
                {new SignUpForm("test", "test123", "333@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "*!#&", "444@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "смирнова", "666@mail.ru", "12345", "12345")},
                {new SignUpForm("test", " ","999@mail.ru", "12345", "12345")},
                {new SignUpForm("test"," test", "123@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "test ", "456@mail.ru", "12345", "12345")},
                {new SignUpForm("test", "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest",
                        "789@mail.ru",  "12345", "12345")}
        };
    }

    @Test(dataProvider = "lastNameInvalidData")
    public void lastNameNegativeTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();

        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "It's possible to register with invalid data in 'Last name' field.");
    }


    @DataProvider
    public Object[][] emailValidData() {
        return new Object[][]{
                {new SignUpForm("test", "123@yahoo.com", "12345")}
        };
    }

    @Test(dataProvider = "emailValidData")
    public void emailPositiveTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(), "Check data in 'Email' field.");
    }

    @DataProvider
    public Object[][] emailInvalidData() {
        return new Object[][]{
                {new SignUpForm("test", "123@yahoo.com", "12345")}, //previously used email
                {new SignUpForm("test", "111yahoo.com", "12345")}, //without @
                {new SignUpForm("test", "111@yahoo", "12345")}, //without ".com"
                {new SignUpForm("test", "111@yahoo.", "12345")}, //without domain
                {new SignUpForm("test", "1234 @yahoo.com", "12345")}, //with space
                {new SignUpForm("test", "", "12345")},
                {new SignUpForm("test", " ", "12345")},
                {new SignUpForm("test", " 222@yahoo.com", "12345")},
                {new SignUpForm("test", "333@yahoo.com ", "12345")}
        };
    }

    @Test(dataProvider = "emailInvalidData")
    public void emailNegativeTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "It's possible to register with invalid data in 'Email' field");
    }


    @DataProvider
    public Object[][] passwordValidData() {
        return new Object[][]{
                {new SignUpForm("test", "222@yandex.ru", "1234")},
                {new SignUpForm("test", "333@yandex.ru", "12345")},
                {new SignUpForm("test", "444@yandex.ru", "bbbb")},
                {new SignUpForm("test", "456@yandex.ru", "*!#&")}
        };
    }

    @Test(dataProvider = "passwordValidData")
    public void passwordPositiveTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement successMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertTrue(successMessage.isDisplayed(), "Check data in 'Password' field.");
    }

    @DataProvider
    public Object[][] passwordInvalidData() {
        return new Object[][]{
                {new SignUpForm("test", "111@yandex.ru", "123")},
                {new SignUpForm("test", "555@yandex.ru", "")},
                {new SignUpForm("test", "666@yandex.ru", "12 34")}, //space in the middle
                {new SignUpForm("test", "777@yandex.ru", " 1234")}, //space before password
                {new SignUpForm("test", "888@yandex.ru", "1234 ")}, //space after password
                {new SignUpForm("test", "999@yandex.ru", "\"asd\"")},
                {new SignUpForm("test", "123@yandex.ru", "абвг")},
                {new SignUpForm("test", "789@yandex.ru", "1234", "12345")} //password confirmation
        };
    }

    @Test(dataProvider = "passwordInvalidData")
    public void passwordNegativeTest(SignUpForm signUpForm) {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(signUpForm.getFirstName());
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(signUpForm.getLastName());
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(signUpForm.getEmail());
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(signUpForm.getPassword());
        WebElement passwordConfirmationInput = driver.findElement(By.name("password2"));
        passwordConfirmationInput.sendKeys(signUpForm.getPasswordConfirmation());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "It's possible to register with invalid data in 'Password' field");
    }
}
