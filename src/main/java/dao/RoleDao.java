package dao;


import models.Role;

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

}
