package service.impl;

import beans.Role;
import dao.RoleDao;
import service.RoleService;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao;


    @Override
    public Role getRoleById(int id) {

        return roleDao.getRoleById(id);
    }
}
