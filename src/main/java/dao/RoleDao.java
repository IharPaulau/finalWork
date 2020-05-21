package dao;


import beans.Role;

import java.util.List;

/**
 * Data Access Object interface to provide operations with {@link Role} objects.
 */
public interface RoleDao {

    /**
     * Get Role by provided role name
     *
     * @param roleName role name to search
     * @return Role object
     */
    Role getRoleByName(String roleName);

    /**
     * Get list of Roles related for User by provided user id
     *
     * @param userId user id to search
     * @return list of Role objects
     */
    List<Role> getUserRoles(int userId);
}
