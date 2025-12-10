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
protected void doPost(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}
}