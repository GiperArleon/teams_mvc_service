package net.teams.dao;

import net.teams.model.User;

import java.util.List;

public interface DaoUser {
    List<User> findAll();
    List<User> findUsersByRole(String roleName);
    List<User> findUsersByGroup(String groupName);
    User findUserById(int id);
    User findUserByLogin(String login);
    User FindUserByTelegramId(String login);
    boolean save(User user);
    boolean update(User user);
    boolean deleteUserById(int id);
}
