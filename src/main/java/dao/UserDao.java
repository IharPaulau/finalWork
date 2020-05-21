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
     */
    void save(User user);

    /**
     * Get User by provided user name
     *
     * @param name user name to search
     * @return User
     */
    User getUserByName(String name);

    /**
     * Save mapping User with Role by provided userId and roleId
     *
     * @param userId userId to save
     * @param roleId roleId to save
     */
    void saveUserRole(int userId, int roleId);
}
