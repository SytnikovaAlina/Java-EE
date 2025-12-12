package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import dao.ConnectionProperty;
import dao.DepartmentDbDAO;
import exception.DAOException;



@WebServlet( "/deletedep")
public class DeleteDepartmentServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
ConnectionProperty prop;
	public DeleteDepartmentServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartmentDbDAO dao = new DepartmentDbDAO();
		
		String strId = request.getParameter("id");
		
		Long deleteid = null;
		
		if (strId != null && !strId.trim().isEmpty()) {
            try {
                Long deleteId = Long.parseLong(strId);
                dao.delete(deleteId);
            } catch (NumberFormatException | DAOException e) {
                System.err.println("Ошибка удаления роли: " + e.getMessage());
               
            }
		}
		response.sendRedirect(request.getContextPath() + "/department");
	}
}

