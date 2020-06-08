package service;

import models.User;

/**
 * Service to provide operations for login {@link User}.
 */
public interface LoginService {

    /**
     * Create Authentication Token and set it to SecurityContext
     *
     * @param username user name
     * @param password user password
     */
    void autoLogin(String username, String password);
}
