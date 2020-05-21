package service;

import beans.User;
import forms.RegistrationForm;

/**
 * Service to manage {@link User} objects.
 */
public interface UserService {

    /**
     * Save provided user and apply 'ROLE_USER' for saved user
     *
     * @param user user to save
     */
    void saveUserWithRole(User user);

    /**
     * Create User according registration form
     *
     * @param form registration form
     * @return new User
     */
    User createNewRegisteredUser(RegistrationForm form);

    /**
     * Get User by provided name
     *
     * @param name user name
     * @return User
     */
    User getUserByName(String name);
}
