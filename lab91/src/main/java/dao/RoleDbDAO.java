package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import domain.Role;
import exception.DAOException;

public class RoleDbDAO implements RepositoryDAO<Role> {

    private static final String select_all_role = "SELECT id, rolename FROM roles ORDER BY rolename ASC";
    private static final String insert_role = "INSERT INTO roles (rolename) VALUES (?)";
    private static final String edit_role = "UPDATE roles SET rolename = ? WHERE id = ?";
    private static final String delete_role = "DELETE FROM roles WHERE id = ?";

    
    private ConnectionBuilder builder = new DbConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Role role) throws DAOException {
        try (Connection con = getConnection(); 
             PreparedStatement pst = con.prepareStatement(insert_role, new String[] { "id" })) {
            pst.setString(1, role.getName());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                return gk.getLong("id");
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public void update(Role role) throws DAOException {
        try (Connection con = getConnection(); 
             PreparedStatement pst = con.prepareStatement(edit_role)) {
            pst.setString(1, role.getName());
            pst.setLong(2, role.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long Id) throws DAOException {
        try (Connection con = getConnection(); 
             PreparedStatement pst = con.prepareStatement(delete_role)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Role findById(Long Id) throws DAOException {
        Role role = null;
        try (Connection con = getConnection(); 
             PreparedStatement pst = con.prepareStatement("SELECT id, rolename FROM roles WHERE id = ?")) {
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                role = new Role();
                role.setId(rs.getLong("id"));
                role.setName(rs.getString("rolename"));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return role;
    }

    @Override
    public List<Role> findAll() throws DAOException {
        List<Role> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_all_role);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getLong("id"));
                role.setName(rs.getString("rolename"));
                list.add(role);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }


    public long countPersonsByRole(Long roleId) throws DAOException {
        
        try (Connection con = getConnection(); 
                PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM personsdep WHERE roleid = ?")) {
            
            pst.setLong(1, roleId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Ошибка подсчета сотрудников по роли", e);
        }
        return 0;
    }

}
