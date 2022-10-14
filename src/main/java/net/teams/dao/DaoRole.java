package net.teams.dao;

import net.teams.model.Role;

public interface DaoRole {
    Role[] findUserRoles();
    boolean saveNewRole(Role role);
}
