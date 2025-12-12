package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import domain.Role;
import exception.DAOException;
import dao.RoleDbDAO;
import dao.ConnectionProperty;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RoleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        List<Role> roles = null;
        try {
            RoleDbDAO dao = new RoleDbDAO();
            roles = dao.findAll();
            request.setAttribute("roles", roles);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки ролей: " + e.getMessage());
        }

        request.getRequestDispatcher("/views/role.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    			RoleDbDAO dao = new RoleDbDAO();
    			String name = request.getParameter("inputRole");
    			Role newRole = new Role(name);
    			try {
		    		Long index = dao.insert(newRole);
		    		System.out.println("Adding result: " + index );
    			} catch (DAOException e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
    		}
    			doGet(request, response);
	}

}
