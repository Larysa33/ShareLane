package signUp.registrationForm;

public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;

    public SignUpForm(String firstName, String lastName, String email, String password, String passwordConfirmation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public SignUpForm(String firstName, String email, String password, String passwordConfirmation) {
        this.firstName = firstName;
        this.lastName = "";
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public SignUpForm(String firstName, String email, String password) {
        this.firstName = firstName;
        this.lastName = "";
        this.email = email;
        this.password = password;
        this.passwordConfirmation = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
}
