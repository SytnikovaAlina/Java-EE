package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Facultet;
import exception.DAOException;


public class FacultetDbDAO implements RepositoryDAO<Facultet>{

	public FacultetDbDAO() {
	// TODO Auto-generated constructor stub
	}
	
	// SQL для personsfac
    private static final String select_all_fac = 
        "SELECT id, roleid, fullname, facname, telephone FROM personsFac ORDER BY fullname ASC";
    private static final String select_fac_ById = 
        "SELECT id, roleid, fullname, facname, telephone FROM personsFac WHERE id = ?";
    private static final String insert_fac = 
        "INSERT INTO personsFac(roleid, fullname, facname, telephone) VALUES(?,?,?,?)";
    private static final String edit_fac = 
        "UPDATE personsFac SET roleid = ?, fullname = ?, facname = ?, telephone = ? WHERE id = ?";
    private static final String delete_fac = 
        "DELETE FROM personsFac WHERE id = ?";
	
	// Создание соединения с базой данных
	private ConnectionBuilder builder = new DbConnectionBuilder();
	
	private Connection getConnection() throws SQLException {
		return builder.getConnection();
	}
	
	// Добавление новой должности
	@Override
	public Long insert (Facultet fac) throws DAOException {
		try (Connection con = getConnection(); 
				PreparedStatement pst = con.prepareStatement(insert_fac, new String[] { "id" })) {
			Long Id = -1L;
			pst.setLong(1, fac.getIdR()); // roleid
	        pst.setString(2, fac.getName()); // fullname
	        pst.setString(3, fac.getFacname()); // facname
	        pst.setString(4, fac.getTel()); // telephone
			
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
	public void update(Facultet fac) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_fac)) {
			pst.setLong(1, fac.getIdR()); // roleid
	        pst.setString(2, fac.getName()); // fullname
	        pst.setString(3, fac.getFacname()); // facname
	        pst.setString(4, fac.getTel()); // telephone
	        pst.setLong(5, fac.getId()); // id-----------------------------------------------------------------??
			pst.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	// Удаление должности
	@Override
	public void delete(Long Id) throws DAOException {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_fac)) {
			pst.setLong(1, Id);
			pst.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	// Поиск должности по Id
	@Override
	public Facultet findById(Long Id) throws DAOException {
		Facultet fac = null;
		try (Connection con = getConnection()) {
			PreparedStatement pst =con.prepareStatement(select_fac_ById);
			pst.setLong(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				fac = fillFacs(rs);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return fac;
	}
	// Формирование списка всех должностей
	@Override
	public List<Facultet> findAll() throws DAOException {
		List<Facultet> list = new LinkedList<>();
		try (Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(select_all_fac);
			ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				list.add(fillFacs(rs));
			}
			rs.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return list;
	}
	// Формирование класса Должности по результатам запроса к БД
	private Facultet fillFacs(ResultSet rs) throws SQLException {
		Facultet fac = new Facultet();
		Long idRole = rs.getLong("roleid");
		
		fac.setId(rs.getLong("id"));
		fac.setName(rs.getString("fullname"));
		fac.setFacname(rs.getString("facname"));
		fac.setTel(rs.getString("telephone"));
		fac.setIdR(idRole);
		return fac;
	}
}
