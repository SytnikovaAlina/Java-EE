package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Department;
import exception.DAOException;


public class DepartmentDbDAO implements RepositoryDAO<Department>{

	public DepartmentDbDAO() {
	// TODO Auto-generated constructor stub
	}
	
	// SQL для personsDep
    private static final String select_all_dep = 
        "SELECT id, roleid, fullname, depname, telephone FROM personsDep ORDER BY fullname ASC";
    private static final String select_dep_ById = 
        "SELECT id, roleid, fullname, depname, telephone FROM personsDep WHERE id = ?";
    private static final String insert_dep = 
        "INSERT INTO personsDep(roleid, fullname, depname, telephone) VALUES(?,?,?,?)";
    private static final String edit_dep = 
        "UPDATE personsDep SET roleid = ?, fullname = ?, depname = ?, telephone = ? WHERE id = ?";
    private static final String delete_dep = 
        "DELETE FROM personsDep WHERE id = ?";
	
	// Создание соединения с базой данных
	private ConnectionBuilder builder = new DbConnectionBuilder();
	
	private Connection getConnection() throws SQLException {
		return builder.getConnection();
	}
	
	// Добавление новой должности
	@Override
	public Long insert (Department dep) throws DAOException {
		try (Connection con = getConnection(); 
				PreparedStatement pst = con.prepareStatement(insert_dep, new String[] { "id" })) {
			Long Id = -1L;
			pst.setLong(1, dep.getIdR()); // roleid
	        pst.setString(2, dep.getName()); // fullname
	        pst.setString(3, dep.getDepname()); // depname
	        pst.setString(4, dep.getTel()); // telephone
			
			pst.executeUpdate();
			ResultSet gk = pst.getGeneratedKeys();
			if (gk.next()) {
				Id = gk.getLong("id");
			}
			gk.close();
			return Id;
		} catch (Exception e) {
		throw new DAOException(e);
		}
	}
	// Редактирование должности
	@Override
	public void update(Department dep) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_dep)) {
			pst.setLong(1, dep.getIdR()); // roleid
	        pst.setString(2, dep.getName()); // fullname
	        pst.setString(3, dep.getDepname()); // depname
	        pst.setString(4, dep.getTel()); // telephone
	        pst.setLong(5, dep.getId()); // id-----------------------------------------------------------------??
			pst.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	// Удаление должности
	@Override
	public void delete(Long Id) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_dep)) {
			pst.setLong(1, Id);
			pst.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	// Поиск должности по Id
	@Override
	public Department findById(Long Id) throws DAOException {
		Department dep = null;
		try (Connection con = getConnection()) {
			PreparedStatement pst =con.prepareStatement(select_dep_ById);
			pst.setLong(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				dep = fillDeps(rs);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return dep;
	}
	// Формирование списка всех должностей
	@Override
	public List<Department> findAll() throws DAOException {
		List<Department> list = new LinkedList<>();
		try (Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(select_all_dep);
			ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				list.add(fillDeps(rs));
			}
			rs.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return list;
	}
	// Формирование класса Должности по результатам запроса к БД
	private Department fillDeps(ResultSet rs) throws SQLException {
		Department dep = new Department();
		Long idRole = rs.getLong("roleid");
		
		dep.setId(rs.getLong("id"));
		dep.setName(rs.getString("fullname"));
		dep.setDepname(rs.getString("depname"));
		dep.setTel(rs.getString("telephone"));
		dep.setIdR(idRole);
		return dep;
	}
}
