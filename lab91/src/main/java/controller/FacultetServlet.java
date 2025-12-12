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
import dao.FacultetDbDAO;
import dao.RoleDbDAO;
import domain.Department;
import domain.Facultet;
import domain.Role;

import exception.DAOException;

/**
* Servlet implementation class PersonServlet
*/
@WebServlet("/facultet")
public class FacultetServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
ConnectionProperty prop;

 public FacultetServlet() throws FileNotFoundException, IOException {
	 super();
	 prop = new ConnectionProperty();
 }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	response.setContentType("text/html");
	String userPath;
	List<Facultet> facs;
	List<Role> roles;
	RoleDbDAO daoRole = new RoleDbDAO();
	FacultetDbDAO dao = new FacultetDbDAO();
	try {
		facs = dao.findAll();
		roles = daoRole.findAll();
	for (Facultet fac: facs) {
		fac.setRole(daoRole.findById(fac.getIdR()));
	}	
	request.setAttribute("facs", facs);
	request.setAttribute("roles", roles);
	} catch (DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 userPath = request.getServletPath();
	if("/facultet".equals(userPath)){
		request.getRequestDispatcher("/views/facultet.jsp").forward(request, response);
	}
}
/**
* @see HttpServlet#doPost(HttpServletRequest request,
HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	FacultetDbDAO dao = new FacultetDbDAO();
    String name = request.getParameter("name");
    String facName = request.getParameter("facname");
    String phone = request.getParameter("phone");
    String roleStr = request.getParameter("role");     

    System.out.println("role = '" + roleStr + "'");    

    if (name != null && !name.trim().isEmpty() && 
        facName != null && !facName.trim().isEmpty() &&
        phone != null && roleStr != null && !roleStr.trim().isEmpty()) {
        
        try {
            Long idRole = Long.parseLong(roleStr.trim());
            Facultet newFacultet = new Facultet(name, facName, phone, idRole);
            Long index = dao.insert(newFacultet);
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