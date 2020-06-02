package dao;

import beans.User;

/**
 * Data Access Object interface to provide operations with {@link User} objects.
 */
public interface UserDao {

    /**
     * Save current user
     *
     * @param user user to save
     * @return User id
     */
    int save(User user);

    /**
     * Get User by provided user name
     *
     * @param name user name to search
     * @return User
     */
    User getUserByName(String name);

    /**
     * Get User by provided user email
     *
     * @param email user email to search
     * @return User
     */
    User getUserByEmail(String email);


    /**
     * Get User With Roles by provided value
     *
     * @param value user name or email to search
     * @return User
     */
    User getUserWithRoles(String value);

    /**
     * Save mapping User with Role by provided userId and roleId
     *
     * @param userId userId to save
     * @param roleId roleId to save
     */
    void saveUserRole(int userId, int roleId);
}
