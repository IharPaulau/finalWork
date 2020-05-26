package forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class RegistrationForm {
    @NotBlank(message = "{empty.name}")
    private String name;
    @NotBlank(message = "{empty.password}")
    private String password;
    @NotBlank(message = "{empty.confirmPassword}")
    private String confirmPassword;
    @NotBlank(message = "{empty.email}")
    @Email(message = "{invalid.email}")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
