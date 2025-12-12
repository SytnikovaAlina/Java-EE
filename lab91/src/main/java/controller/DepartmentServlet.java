package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import dao.ConnectionProperty;
import dao.DepartmentDbDAO;
import dao.RoleDbDAO;
import domain.Department;
import domain.Role;

import exception.DAOException;

/**
* Servlet implementation class PersonServlet
*/
@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	ConnectionProperty prop;

 public DepartmentServlet() throws FileNotFoundException, IOException {
 super();
 prop = new ConnectionProperty();
 }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String userPath;
		List<Department> deps;
		List<Role> roles;
		RoleDbDAO daoRole = new RoleDbDAO();
		DepartmentDbDAO dao = new DepartmentDbDAO();
		try {
			deps = dao.findAll();
			roles = daoRole.findAll();
			
			for (Department dep: deps) {
				dep.setRole(daoRole.findById(dep.getIdR()));
			}
			request.setAttribute("deps", deps);
			request.setAttribute("roles", roles);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 userPath = request.getServletPath();
		if("/department".equals(userPath)){
			request.getRequestDispatcher("/views/department.jsp").forward(request, response);
		}
	}
	/**
	* @see HttpServlet#doPost(HttpServletRequest request,
	HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    DepartmentDbDAO dao = new DepartmentDbDAO();
	    String name = request.getParameter("name");
	    String depName = request.getParameter("depname");  // Исправлено с "facname"
	    String phone = request.getParameter("phone");
	    String roleStr = request.getParameter("role");     // ID роли из select

	    System.out.println("role = '" + roleStr + "'");    // Для отладки

	    if (name != null && !name.trim().isEmpty() && 
	        depName != null && !depName.trim().isEmpty() &&
	        phone != null && roleStr != null && !roleStr.trim().isEmpty()) {
	        
	        try {
	            Long idRole = Long.parseLong(roleStr.trim());
	            Department newDepartment = new Department(name, depName, phone, idRole);
	            Long index = dao.insert(newDepartment);
	            System.out.println("Добавлено: ID=" + index);
	        } catch (NumberFormatException | DAOException e) {
	            System.err.println("Ошибка добавления: " + e.getMessage());
	            request.setAttribute("error", "Ошибка сохранения данных");
	        }
	    } else {
	        System.err.println("Недостаточно данных для добавления");
	        request.setAttribute("error", "Заполните все поля");
	    }
	    doGet(request, response);  // Перезагружаем список
	}

}