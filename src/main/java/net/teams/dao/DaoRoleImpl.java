package net.teams.dao;

import lombok.extern.slf4j.Slf4j;
import net.teams.model.Role;
import net.teams.tools.DataSourceFactory;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class DaoRoleImpl implements DaoRole {

    @Override
    public Role[] findUserRoles() {
        List<Role> userRoleById = new ArrayList<>();
        try (Connection connection = DataSourceFactory.connection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM service.roles");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("r_id"), rs.getString("rolename"));
                userRoleById.add(role);
            }
        } catch (SQLException e) {
            log.error("Exception when trying found users roles. {}", e.toString());
        }
        return userRoleById.toArray(new Role[0]);
    }

    @Override
    public boolean saveNewRole(Role role) {
        boolean resultSave = false;
        try (Connection connection = DataSourceFactory.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO service.roles (rolename) " +
                    "VALUES (?)");
            preparedStatement.setString(1, role.getRoleName());
            resultSave = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Exception when trying create new role. {}", e.toString());
        }
        return resultSave;
    }
}
