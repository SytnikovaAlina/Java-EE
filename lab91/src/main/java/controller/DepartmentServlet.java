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
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
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
} catch (DAOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
 userPath = request.getServletPath();
if("/department".equals(userPath)){
request.getRequestDispatcher("/views/department.jsp").forward(request
, response);
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