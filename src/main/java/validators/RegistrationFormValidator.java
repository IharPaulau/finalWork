package validators;

import forms.RegistrationForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import service.UserService;

public class RegistrationFormValidator implements Validator {

    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        RegistrationForm form = (RegistrationForm) object;
        String name = form.getName();
        if (!name.isEmpty() && userService.getUserByName(name) != null) {
            errors.rejectValue("name", "user.name.exist");
        }
        String email = form.getEmail();
        if (!email.isEmpty() && userService.getUserByEmail(email) != null) {
            errors.rejectValue("email", "user.email.exist");
        }
        if (!form.getConfirmPassword().equals(form.getPassword())) {
            errors.rejectValue("confirmPassword", "different.password");
        }
    }

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
